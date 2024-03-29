package uz.unicon.charityproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.unicon.charityproject.entity.District;
import uz.unicon.charityproject.entity.Region;

import java.util.List;
import java.util.Optional;

public interface DistrictRepository extends JpaRepository<District,Integer> {

  //  @Query(value ="select districts from districts where region_id=:id ",nativeQuery = true)
    //List<District> getDistrictByRegionId(@Param("id")Integer id);
  List<District> getDistrictsByRegionId(Integer region_id);



}
