package uz.unicon.charityproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.unicon.charityproject.entity.Children;
import uz.unicon.charityproject.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    Optional<User> findByIdNumber(String idNumber);

    @Query(value = "select user from children join users_children uc on children.id = uc.children_id where children.number_certificate_of_birth = 'adasdsad'",nativeQuery = true)
    Optional<User> getChildrenInfoByCertificateNumber(@Param("certificateNumber") String certificateNumber);


}
