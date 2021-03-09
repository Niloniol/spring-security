package web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import web.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "from User where username=:username")
    User findByName(@Param("username")String username);
}