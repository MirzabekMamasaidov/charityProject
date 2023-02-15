package uz.unicon.charityproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.unicon.charityproject.entity.Organization;

public interface OrganizationRepository extends JpaRepository<Organization,Integer> {

}
