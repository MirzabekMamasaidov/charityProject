package uz.unicon.charityproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.unicon.charityproject.payload.ApiResponse;
import uz.unicon.charityproject.service.RegionService;

@RestController
@RequestMapping("/regions")
public class RegionController {

    @Autowired
    RegionService regionService;

    @GetMapping
    public HttpEntity<?> getAll(){
        ApiResponse response = regionService.getAll();
        return ResponseEntity.status(response.isSuccess()? HttpStatus.OK:HttpStatus.NOT_FOUND).body(response);
    }
}
