package uz.unicon.charityproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.unicon.charityproject.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    Optional<User> findByIdNumber(String idNumber);


}
