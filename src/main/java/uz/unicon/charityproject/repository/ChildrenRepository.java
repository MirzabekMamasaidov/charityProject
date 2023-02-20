package uz.unicon.charityproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.unicon.charityproject.entity.Children;

import java.util.Optional;

public interface ChildrenRepository extends JpaRepository<Children,Integer> {

    Optional<Children> findByNumberCertificateOfBirth(String certificateNumber);
}
