package pl.jarczi.carapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jarczi.carapp.dao.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long > {

    User findByUsername(String username);
}
