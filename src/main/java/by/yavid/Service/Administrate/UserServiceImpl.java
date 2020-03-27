package by.yavid.Service.Administrate;

import by.yavid.Entity.Workbase.Administrate.User;
import by.yavid.Repository.Workbase.Administrate.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;


    @Override
    @Transactional(transactionManager="workbaseTransactionManager")
    public Optional<User> getUser(Integer id) {
        return  userRepository.findById(id);
    }

    @Override
    @Transactional(transactionManager="workbaseTransactionManager")
    public User getUserByUserName(String Username) {
        return userRepository.findFirstByUserName(Username);
    }

    @Override
    @Transactional(transactionManager="workbaseTransactionManager")
    public List<User> getAllUser() {
        return  StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}
