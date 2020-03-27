package by.yavid.Repository.Workbase.Administrate;

import by.yavid.Entity.Workbase.Administrate.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findFirstByUserName(String username);
}
