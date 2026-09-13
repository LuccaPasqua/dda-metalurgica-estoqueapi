package br.org.sesisenai.estoqueapi.repository;

import br.org.sesisenai.estoqueapi.entity.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {

    // Busca por documento
    Optional<Manufacturer> findByDocument(String document);
    boolean existsByDocument(String document);
    boolean existsByDocumentAndIdNot(String document, Long id);

    // Busca por filtros
    List<Manufacturer> findAllByOrderByNameAsc();
    List<Manufacturer> findAllByOrderByContactAsc();
    // Exemplo: buscar "dell" encontra "Dell Technologies Ltda"
    List<Manufacturer> findByNameContainingIgnoreCase(String name);
}
