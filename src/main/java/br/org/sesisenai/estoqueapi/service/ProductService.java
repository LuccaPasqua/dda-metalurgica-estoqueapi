package br.org.sesisenai.estoqueapi.service;

import br.org.sesisenai.estoqueapi.dto.ProductRequestDTO;
import br.org.sesisenai.estoqueapi.dto.ProductResponseDTO;
import br.org.sesisenai.estoqueapi.entity.Manufacturer;
import br.org.sesisenai.estoqueapi.entity.Product;
import br.org.sesisenai.estoqueapi.entity.StockMovement;
import br.org.sesisenai.estoqueapi.repository.ManufacturerRepository;
import br.org.sesisenai.estoqueapi.repository.ProductRepository;
import br.org.sesisenai.estoqueapi.repository.PurchaseRequestRepository;
import br.org.sesisenai.estoqueapi.repository.StockMovementRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.*;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final PurchaseRequestRepository purchaseRequestRepository;
    private final StockMovementRepository stockMovementRepository;

    public ProductService(ProductRepository productRepository, ManufacturerRepository manufacturerRepository, PurchaseRequestRepository purchaseRequestRepository, StockMovementRepository stockMovementRepository){
        this.productRepository = productRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.purchaseRequestRepository = purchaseRequestRepository;
        this.stockMovementRepository = stockMovementRepository;
    }


    @Transactional
    public Product create(ProductRequestDTO dto){
        // Validar se já existe um produto com esse código
        if(productRepository.existsByCode(dto.code())){
            throw new IllegalArgumentException("Já existe um produto com o código: " + dto.code());
        }

        // Validar se Manufacturer existe
        Manufacturer manufacturer = manufacturerRepository.findById(dto.manufacturerId()).orElseThrow(() -> new IllegalArgumentException("Fabricante não encontrado com o ID: " + dto.manufacturerId()));

        Product product = new Product();
        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setCode(dto.code());
        product.setUnitOfMeasurement(dto.unitOfMeasurement());
        product.setUnitCost(dto.unitCost());
        product.setMinStockLevel(dto.minStockLevel());
        product.setManufacturer(manufacturer);

        Product savedProduct = productRepository.save(product);

        return savedProduct;
    }

    @Transactional(readOnly = true)
    public List<Product> findAll(){
        List<Product> productList = productRepository.findAllByOrderByCreatedAd();

        return productList;
    }

    @Transactional(readOnly = true)
    public Product findById(Long id){
        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Produto não encontrado com o ID:"+ id ));

        return product;
    }

    @Transactional(readOnly = true)
    public Product findByCode(String code){
        Product product = productRepository.findByCode(code).orElseThrow(() -> new IllegalArgumentException("Produto não encontrado com o código: "+ code));

        return product;
    }

    @Transactional(readOnly = true)
    public List<Product> findAllByName(String name){
        List<Product> productList = productRepository.findByNameContainingIgnoreCase(name);

        return productList;
    }

    @Transactional
    public Product update(Long id, ProductRequestDTO dto){
        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Produto não encontrado com o ID: " + id));

        //Validação: se alterou o código, garantir que outro produto não usa o mesmo código
        if(!product.getCode().equals(dto.code()) && productRepository.existsByCodeAndIdNot(dto.code(), id)) {
            throw new IllegalArgumentException("Já existe outro produto cadastrado com o código: "+ dto.code());
        }

        Manufacturer manufacturer = manufacturerRepository.findById(dto.manufacturerId()).orElseThrow(() -> new IllegalArgumentException("Manufacturer não encontrado com ID: "+ dto.manufacturerId()));

        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setCode(dto.code());
        product.setUnitOfMeasurement(dto.unitOfMeasurement());
        product.setUnitCost(dto.unitCost());
        product.setMinStockLevel(dto.minStockLevel());

        return productRepository.save(product);
    }

    @Transactional
    public void delete(Long id){
        // Validar se produto existe
        Product product = productRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Produto não encontrado com ID: "+ id));

        // Validar se não tem nenhum purchase request com esse product
        if(!purchaseRequestRepository.findByProductIdOrderByCreatedAtDesc(id).isEmpty()){
            throw new IllegalArgumentException("Não é possível excluir o produto id: "+ id +". Existe uma requisição de compra com esse produto");
        }

        if(!stockMovementRepository.findByProductIdOrderByCreatedAtDesc(id).isEmpty()){
            throw new IllegalArgumentException("Não é possível excluir o produto id: "+ id +". Existe um movimento no estoque com esse produto");
        }

    }




//    private ProductResponseDTO toResponseDTO(Product product){
//        return new ProductResponseDTO(
//            product.getId(),
//            product.getName(),
//            product.getDescription(),
//            product.getCode(),
//            product.getUnitOfMeasurement(),
//            product.getUnitCost(),
//            product.getMinStockLevel(),
//            product.getManufacturer() != null ? product.getManufacturer().getName() : null,
//            product.getCreatedAt()
//        );
//    }

}
