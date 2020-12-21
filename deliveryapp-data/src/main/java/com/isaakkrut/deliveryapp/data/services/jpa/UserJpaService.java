package com.isaakkrut.deliveryapp.data.services.jpa;

import com.isaakkrut.deliveryapp.data.domain.Login;
import com.isaakkrut.deliveryapp.data.domain.User;
import com.isaakkrut.deliveryapp.data.repository.UserRepository;
import com.isaakkrut.deliveryapp.data.services.UserService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserJpaService implements UserService{
    private final UserRepository userRepository;

    public UserJpaService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }


    @Override
    public void deleteUserByEmail(String username) {
        userRepository.deleteByEmail(username);
    }

    @Override
    public Set<User> findAll() {
        Set<User> users = new HashSet<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public User findById(Long aLong) {
        return userRepository.findById(aLong).orElse(null);
    }

    @Override
    public User save(User object) {
        Optional<User> user = userRepository.findByEmail(object.getEmail());
        if (user.isPresent()){
            User updatedUser = user.get();
            updatedUser.setFirstName(object.getFirstName());
            updatedUser.setLastName(object.getLastName());
            updatedUser.setBirthDate(object.getBirthDate());
            return userRepository.save(updatedUser);
        } else return userRepository.save(object);
    }

    @Override
    public void delete(User object) {
        userRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        userRepository.deleteById(aLong);
    }
}
