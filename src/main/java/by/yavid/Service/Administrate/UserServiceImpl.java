package by.yavid.Service.Administrate;

import by.yavid.Exception.ThereIsNoSuchUserException;
import by.yavid.Entity.Workbase.Administrate.User;
import by.yavid.Repository.Workbase.Administrate.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
@Service
public class UserServiceImpl implements UserService {


    private  UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(transactionManager="workbaseTransactionManager")
    public User getUser(Integer id) {
        return  userRepository.findById(id).orElseThrow(() -> new ThereIsNoSuchUserException());
    }

    @Override
    @Transactional(transactionManager="workbaseTransactionManager")
    public User getUserByUserName(String Username) {
        return userRepository.findFirstByUserName(Username).orElse(new User(-1,"Not FIO","'Not Username"));
    }

    @Override
    @Transactional(transactionManager="workbaseTransactionManager")
    public List<User> getAllUser() {
        return  StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}
