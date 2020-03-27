package by.yavid.Service.Administrate;

import by.yavid.Entity.Workbase.Administrate.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public Optional<User> getUser(Integer id);
    public User getUserByUserName(String Username);
    public List<User> getAllUser();
}
