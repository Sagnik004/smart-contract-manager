package in.sagnikchakraborty.repository;

import in.sagnikchakraborty.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, String> {

    // Custom Finder methods

    Optional<User> findByEmail(String email);
}
