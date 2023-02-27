package uz.unicon.charityproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.unicon.charityproject.payload.ApiResponse;
import uz.unicon.charityproject.service.DistrictService;

@RestController
@RequestMapping("/districts")
public class DistrictController {


    @Autowired
    DistrictService districtService;

    @GetMapping("/{regionId}")
    public HttpEntity<?> get(@PathVariable Integer regionId){
        ApiResponse response = districtService.getByRegionId(regionId);

        return ResponseEntity.status(response.isSuccess()? HttpStatus.OK:HttpStatus.NOT_FOUND).body(response);
    }
}
