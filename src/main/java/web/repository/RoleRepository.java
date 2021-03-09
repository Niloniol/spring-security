package web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import web.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByusername(String role);
}
