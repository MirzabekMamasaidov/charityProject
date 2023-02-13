package uz.unicon.charityproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.unicon.charityproject.entity.Children;

public interface ChildrenRepository extends JpaRepository<Children,Integer> {
}
