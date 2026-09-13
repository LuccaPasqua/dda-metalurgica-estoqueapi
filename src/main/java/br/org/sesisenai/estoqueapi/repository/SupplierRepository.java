package br.org.sesisenai.estoqueapi.repository;

import br.org.sesisenai.estoqueapi.entity.Product;
import br.org.sesisenai.estoqueapi.entity.Supplier;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;


@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    // Buscas por documento
    Optional<Supplier> findSupplierByDocument(String document);
    boolean existsByDocument(String document);
    boolean existsByDocumentAndIdNot(String document, Long id);

    // Buscas para Interface/Filtros
    List<Supplier> findAllByOrderByNameAsc();
    List<Supplier> findByNameContainingIgnoreCase(String name);

    // Buscas por data
    List<Supplier> findByCreatedAtAfter(LocalDateTime createdAt);

}
