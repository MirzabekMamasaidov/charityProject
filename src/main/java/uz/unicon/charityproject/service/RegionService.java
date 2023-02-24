package uz.unicon.charityproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.unicon.charityproject.entity.Region;
import uz.unicon.charityproject.payload.ApiResponse;
import uz.unicon.charityproject.repository.RegionRepository;

import java.util.List;

@Service
public class RegionService {

    @Autowired
    RegionRepository regionRepository;

    public ApiResponse getAll() {
        List<Region> all = regionRepository.findAll();
        return new ApiResponse("Viloyatlar",true,all);
    }
}
