package uz.unicon.charityproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.unicon.charityproject.entity.Children;

import java.util.Optional;

public interface ChildrenRepository extends JpaRepository<Children,Integer> {

    Optional<Children> findByNumberCertificateOfBirth(String certificateNumber);

    @Query(value = "select user from children join users_children uc on children.id = uc.children_id where children.number_certificate_of_birth = 'adasdsad'",nativeQuery = true)
    Optional<Children> getChildrenInfoByCertificateNumber(@Param("certificateNumber") String certificateNumber);


}
