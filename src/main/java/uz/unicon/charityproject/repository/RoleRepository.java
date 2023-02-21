package uz.unicon.charityproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.unicon.charityproject.entity.Role;
import uz.unicon.charityproject.entity.enums.RoleName;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {

    Optional<Role> findByRoleName(RoleName roleName);

}
