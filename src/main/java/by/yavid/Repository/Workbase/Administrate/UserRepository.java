package by.yavid.Repository.Workbase.Administrate;

import by.yavid.Entity.Workbase.Administrate.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findFirstByUserName(String username);
}
