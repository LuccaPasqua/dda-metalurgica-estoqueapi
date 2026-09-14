package br.org.sesisenai.estoqueapi.repository;

import br.org.sesisenai.estoqueapi.entity.StockMovement;
import br.org.sesisenai.estoqueapi.enums.MotiveStockMovement;
import br.org.sesisenai.estoqueapi.enums.TypeStockMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StockMovementRepository extends JpaRepository<StockMovement, Long> {

    List<StockMovement> findByProductIdOrderByCreatedAtDesc(Long productId);
    List<StockMovement> findBySupplierIdOrderByCreatedAtDesc(Long supplierId);
    List<StockMovement> findByUserIdOrderByCreatedAtDesc(Long userId);

    List<StockMovement> findByPurchaseRequestIdOrderByCreatedAtDesc(Long purchaseRequestId);
    List<StockMovement> findByTypeOrderByCreatedAtDesc(TypeStockMovement type);
    List<StockMovement> findByMotiveOrderByCreatedAtDesc(MotiveStockMovement motive);

    List<StockMovement> findByQuantityGreaterThan(BigDecimal quantity);

    List<StockMovement> findByProductIdAndCreatedAtBetweenOrderByCreatedAtDesc(
            Long productId,
            LocalDateTime start,
            LocalDateTime end
    );

    @Query("""
    SELECT COALESCE(SUM(sm.quantity), 0) 
    FROM StockMovement sm 
    WHERE sm.product.id = :productId AND sm.type = :type
""")
    BigDecimal sumQuantityByProductIdAndType(Long productId, TypeStockMovement type);
}
