package uz.unicon.charityproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.unicon.charityproject.entity.District;
import uz.unicon.charityproject.payload.ApiResponse;
import uz.unicon.charityproject.repository.DistrictRepository;

import java.util.List;

@Service
public class DistrictService {

    @Autowired
    DistrictRepository districtRepository;


    public ApiResponse getByRegionId(Integer regionId) {

        List<District> districtList = districtRepository.getDistrictsByRegionId(regionId);
        return new ApiResponse("Tumanlar ro'yhati",true,districtList);
    }
}
