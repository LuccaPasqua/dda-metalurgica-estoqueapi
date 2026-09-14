package br.org.sesisenai.estoqueapi.repository;

import br.org.sesisenai.estoqueapi.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {

    List<Alert> findByProductId(Long productId);

    List<Alert> findByResolved(boolean resolved);

    List<Alert> findAllByOrderByTriggeredAtDesc();

    boolean existsByProductIdAndResolvedFalse(Long productId);

    Optional<Alert> findByProductIdAndResolvedFalse(Long productId);

    long countByResolvedFalse();
}
