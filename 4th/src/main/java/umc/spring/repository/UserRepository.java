package umc.spring.repository;

import org.springframework.data.repository.CrudRepository;
import umc.spring.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    // Additional query methods can be defined here
}