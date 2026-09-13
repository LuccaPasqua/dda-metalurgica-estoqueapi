package br.org.sesisenai.estoqueapi.repository;

import br.org.sesisenai.estoqueapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByCode(String code);

    List<Product> findAllByOrderByNameAsc();

    List<Product> findAllByManufacturerId(Long manufacturerId);

    boolean existsByCode(String code);

    // Busca produtos cujo nome contenha o termo digitado, ignorando maiúsculas e minúsculas
    // Exemplo: buscar "parafuso" encontra "Parafuso Sextavado M8"
    List<Product> findByNameContainingIgnoreCase(String name);

    // Retorna true se houver qualquer produto associado a este fabricante
    boolean existsByManufacturerId(Long manufacturerId);

    // Verifica se existe outro produto com o mesmo código, exceto o produto atual que está sendo editado
    boolean existsByCodeAndIdNot(String code, Long id);
}
