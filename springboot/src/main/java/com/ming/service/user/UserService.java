package com.ming.service.user;

import com.ming.entity.user.User;
import com.ming.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        userRepository.save(user);
        return user;
    }

    public List<User> findUsers() {
        return userRepository.findListByNativeSql("select * from user", User.class);
    }
}
