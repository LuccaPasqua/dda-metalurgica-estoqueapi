package br.org.sesisenai.estoqueapi.service;

import br.org.sesisenai.estoqueapi.dto.ManufacturerRequestDTO;
import br.org.sesisenai.estoqueapi.dto.ProductRequestDTO;
import br.org.sesisenai.estoqueapi.entity.Manufacturer;
import br.org.sesisenai.estoqueapi.entity.Product;
import br.org.sesisenai.estoqueapi.repository.ManufacturerRepository;
import br.org.sesisenai.estoqueapi.repository.ProductRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.*;

import java.util.List;

@Service
public class ManufacturerService {

    private ManufacturerRepository manufacturerRepository;
    private ProductRepository productRepository;

    public ManufacturerService(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public List<Manufacturer> findAll(){
        List<Manufacturer> manufacturerList = manufacturerRepository.findAll();

        return manufacturerList;
    }

    @Transactional
    public Manufacturer create(ManufacturerRequestDTO dto){
        if(manufacturerRepository.existsByDocument(dto.document())){
            throw new IllegalArgumentException("Já existe um fabricante(manufacturer) com esse documento: " + dto.document());
        }

        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName(dto.name());
        manufacturer.setDocument(dto.document());
        manufacturer.setContact(dto.contact());

        Manufacturer savedManufacturer = manufacturerRepository.save(manufacturer);

        return savedManufacturer;
    }

    @Transactional(readOnly = true)
    public Manufacturer findById(Long id){
        Manufacturer manufacturer = manufacturerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Fabricante(manufacturer) não encontrado com o ID: "+ id));

        return manufacturer;
    }

    @Transactional
    public Manufacturer update(Long id, ManufacturerRequestDTO dto){
        Manufacturer manufacturer = manufacturerRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Fabricante(manufacturer) não encontrado com o id: "+ id));

        // Se o id do "novo manufacturer" não for igual ao do antigo E existir outro manufacturer com o mesmo id:
        if(!manufacturer.getDocument().equals(dto.document()) && manufacturerRepository.existsByDocumentAndIdNot(dto.document(), id)){
            throw new IllegalArgumentException("Já existe outro fabricante(manufcturer) cadastrado com o documento: "+ dto.document());
        }

        manufacturer.setName(dto.name());
        manufacturer.setContact(dto.contact());
        manufacturer.setDocument(dto.document());

        return manufacturerRepository.save(manufacturer);
    }

    @Transactional
    public void delete(Long id){
        Manufacturer manufacturer = manufacturerRepository.findById(id).orElseThrow(() ->
            new IllegalArgumentException("Não existe nenhum fabricante(manufacturer) com id: " + id)
        );

        if(!productRepository.findAllByManufacturerId(id).isEmpty()){
            throw new IllegalArgumentException("Não é possível deletar o fabricante(manufacturer) pois existem produtos desse fabricante");
        }

        manufacturerRepository.delete(manufacturer);
    }
}
