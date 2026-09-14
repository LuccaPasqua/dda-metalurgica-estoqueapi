package br.org.sesisenai.estoqueapi.repository;

import br.org.sesisenai.estoqueapi.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByUserIdOrderBySentAtDesc(Long userId);

    List<Notification> findByUserIdAndReadFalse(Long userId);

    long countByUserIdAndReadFalse(Long userId);

    List<Notification> findBySentAtAfter(LocalDateTime sentAt);
}
