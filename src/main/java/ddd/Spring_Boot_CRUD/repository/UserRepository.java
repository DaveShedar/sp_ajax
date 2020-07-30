package ddd.Spring_Boot_CRUD.repository;


import ddd.Spring_Boot_CRUD.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository< User, Long> {
    User findByEmail(String email);
}
