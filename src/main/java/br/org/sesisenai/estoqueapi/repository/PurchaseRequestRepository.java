package br.org.sesisenai.estoqueapi.repository;

import br.org.sesisenai.estoqueapi.entity.PurchaseRequest;
import br.org.sesisenai.estoqueapi.enums.StatusPurchaseRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface PurchaseRequestRepository extends JpaRepository<PurchaseRequest, Long> {

    List<PurchaseRequest> findByProductIdOrderByCreatedAtDesc(Long productId);

    List<PurchaseRequest> findByRequestedByUserIdOrderByCreatedAtDesc(Long createdById);

    List<PurchaseRequest> findByStatusOrderByCreatedAtDesc(StatusPurchaseRequest status);

    List<PurchaseRequest> findByRequestedQuantityGreaterThan(BigDecimal value);

    Optional<PurchaseRequest> findByAlertId(Long alertId);
    boolean existsByAlertIdAndStatus(Long alertId, StatusPurchaseRequest status);

    boolean existsByProductIdAndStatus(Long productId, StatusPurchaseRequest status);

}
