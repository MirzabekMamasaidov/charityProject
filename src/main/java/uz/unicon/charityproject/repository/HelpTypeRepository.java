package uz.unicon.charityproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.unicon.charityproject.entity.HelpType;

public interface HelpTypeRepository extends JpaRepository<HelpType,Integer> {

    boolean existsByName(String name);
}
