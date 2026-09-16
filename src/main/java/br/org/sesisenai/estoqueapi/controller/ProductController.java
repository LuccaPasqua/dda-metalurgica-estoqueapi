package br.org.sesisenai.estoqueapi.controller;

import br.org.sesisenai.estoqueapi.dto.ProductRequestDTO;
import br.org.sesisenai.estoqueapi.dto.ProductResponseDTO;
import br.org.sesisenai.estoqueapi.entity.Product;
import br.org.sesisenai.estoqueapi.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    final private ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> create(@RequestBody ProductRequestDTO dto) {
        Product createProduct = productService.create(dto);
        ProductResponseDTO response = toResponseDTO(createProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> findAll(){
        List<Product> products = productService.findAll();

        List<ProductResponseDTO> responseList = products.stream()
                .map(this::toResponseDTO)
                .toList();

        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> findById(@PathVariable Long id){
        Product product = productService.findById(id);
        ProductResponseDTO response = toResponseDTO(product);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> update(
            @PathVariable Long id,
            @RequestBody ProductRequestDTO dto)
    {
        Product updateProduct = productService.update(id, dto);
        ProductResponseDTO response = toResponseDTO(updateProduct);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }


    private ProductResponseDTO toResponseDTO(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getCode(),
                product.getUnitOfMeasurement(),
                product.getUnitCost(),
                product.getMinStockLevel(),
                product.getManufacturer() != null ? product.getManufacturer().getName() : null,
                product.getCreatedAt()
        );
    }
}
