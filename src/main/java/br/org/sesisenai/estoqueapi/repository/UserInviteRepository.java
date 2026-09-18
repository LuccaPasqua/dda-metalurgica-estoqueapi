package br.org.sesisenai.estoqueapi.repository;

import br.org.sesisenai.estoqueapi.entity.UserInvite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserInviteRepository extends JpaRepository<UserInvite, Long> {

    List<UserInvite> findByEmail(String email);

    List<UserInvite> findAllByOrderByCreatedAt();

    List<UserInvite> findAllByEmailContainingIgnoreCase();

    Optional<UserInvite> findByToken(String token);
}
