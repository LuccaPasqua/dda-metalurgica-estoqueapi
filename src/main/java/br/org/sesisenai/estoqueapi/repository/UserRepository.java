package br.org.sesisenai.estoqueapi.repository;

import br.org.sesisenai.estoqueapi.entity.User;
import br.org.sesisenai.estoqueapi.enums.RoleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmailC(String email);

    List<User> findByNameContainingIgnoreCase(String name);
    List<User> findByRole(RoleUser role);

    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, Long id);
}
