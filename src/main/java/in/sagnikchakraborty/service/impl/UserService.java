package in.sagnikchakraborty.service.impl;

import in.sagnikchakraborty.dto.UserSignupForm;
import in.sagnikchakraborty.entities.User;
import in.sagnikchakraborty.helpers.ResourceNotFoundException;
import in.sagnikchakraborty.repository.IUserRepository;
import in.sagnikchakraborty.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public User saveUser(User user) {
        // Generate user_id
        user.setUserId(UUID.randomUUID().toString());

        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {
        User dbUser = userRepository
                .findById(user.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        dbUser.setName(user.getName());
        dbUser.setEmail(user.getEmail());
        dbUser.setPassword(user.getPassword());
        dbUser.setPhoneNumber(user.getPhoneNumber());
        dbUser.setAbout(user.getAbout());
        dbUser.setProfilePic(user.getProfilePic());
        dbUser.setEnabled(user.isEnabled());
        dbUser.setEmailVerified(user.isEmailVerified());
        dbUser.setPhoneVerified(user.isPhoneVerified());
        dbUser.setProvider(user.getProvider());
        dbUser.setProviderId(user.getProviderId());

        User savedUser = userRepository.save(dbUser);

        return Optional.of(savedUser);
    }

    @Override
    public void deleteUser(String id) {
        User dbUser = userRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepository.delete(dbUser);
    }

    @Override
    public boolean isUserIdExist(String id) {
        User dbUser = userRepository.findById(id).orElse(null);
        return dbUser != null;
    }

    @Override
    public boolean isUserEmailExist(String email) {
        User dbUser = userRepository.findByEmail(email).orElse(null);
        return dbUser != null;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
