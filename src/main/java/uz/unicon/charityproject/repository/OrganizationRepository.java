package uz.unicon.charityproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.unicon.charityproject.entity.Organization;

import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization,Integer> {

    Optional<Organization> findByName(String name);
}
