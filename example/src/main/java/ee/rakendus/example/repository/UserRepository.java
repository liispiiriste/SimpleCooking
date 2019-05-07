package ee.rakendus.example.repository;

import ee.rakendus.example.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);
    User findById(long id);

    User findByUsername(String username);
}
