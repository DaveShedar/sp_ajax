package ddd.Spring_Boot_CRUD.repository;

import ddd.Spring_Boot_CRUD.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
