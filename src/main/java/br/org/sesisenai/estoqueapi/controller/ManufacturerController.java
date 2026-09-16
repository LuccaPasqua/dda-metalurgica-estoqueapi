package br.org.sesisenai.estoqueapi.controller;

import br.org.sesisenai.estoqueapi.dto.ManufacturerRequestDTO;
import br.org.sesisenai.estoqueapi.dto.ManufacturerResponseDTO;
import br.org.sesisenai.estoqueapi.dto.ProductRequestDTO;
import br.org.sesisenai.estoqueapi.dto.ProductResponseDTO;
import br.org.sesisenai.estoqueapi.entity.Manufacturer;
import br.org.sesisenai.estoqueapi.entity.Product;
import br.org.sesisenai.estoqueapi.service.ManufacturerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/manufacturer")
public class ManufacturerController {
    final private ManufacturerService manufacturerService;

    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public ResponseEntity<List<ManufacturerResponseDTO>> findAll(){
        List<Manufacturer> manufacturerList = manufacturerService.findAll();

        List<ManufacturerResponseDTO> responseList = manufacturerList.stream()
            .map(this::toResponseDTO)
            .toList();

        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManufacturerResponseDTO> findById(@PathVariable Long id){
        Manufacturer manufacturer = manufacturerService.findById(id);
        ManufacturerResponseDTO response = toResponseDTO(manufacturer);
        return ResponseEntity.ok(response);
    }


    @PostMapping
    public ResponseEntity<ManufacturerResponseDTO> create(@RequestBody ManufacturerRequestDTO dto)
    {
        Manufacturer createManufacturer = manufacturerService.create(dto);
        ManufacturerResponseDTO response = toResponseDTO(createManufacturer);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    public ResponseEntity<ManufacturerResponseDTO> update(
            @PathVariable Long id,
            @RequestBody ManufacturerRequestDTO dto
    ){
        Manufacturer updateManufacturer = manufacturerService.update(id, dto);
        ManufacturerResponseDTO response = toResponseDTO(updateManufacturer);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ManufacturerResponseDTO> delet(@PathVariable Long id){
        manufacturerService.delete(id);
        return ResponseEntity.noContent().build();
    }


    private ManufacturerResponseDTO toResponseDTO(Manufacturer manufacturer) {
        return new ManufacturerResponseDTO(
                manufacturer.getId(),
                manufacturer.getName(),
                manufacturer.getDocument(),
                manufacturer.getContact(),
                manufacturer.getCreatedAt()
        );
    }
}
