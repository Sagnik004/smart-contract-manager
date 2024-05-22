package in.sagnikchakraborty.service;

import in.sagnikchakraborty.dto.UserSignupForm;
import in.sagnikchakraborty.entities.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    User saveUser(User user);

    Optional<User> getUserById(String id);

    Optional<User> updateUser(User user);

    void deleteUser(String id);

    boolean isUserIdExist(String id);

    boolean isUserEmailExist(String email);

    List<User> getAllUsers();
}
