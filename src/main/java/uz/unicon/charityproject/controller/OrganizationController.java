package uz.unicon.charityproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.unicon.charityproject.payload.ApiResponse;
import uz.unicon.charityproject.payload.OrganizationDto;
import uz.unicon.charityproject.service.OrganizationService;

@RestController
@RequestMapping("/organization")
public class OrganizationController {

    @Autowired
    OrganizationService organizationService;

    @PostMapping
    public HttpEntity<?> add(@RequestBody OrganizationDto organizationDto){
        ApiResponse response = organizationService.add(organizationDto);
        return ResponseEntity.status(response.isSuccess()? HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(response);
    }

    @GetMapping
    public HttpEntity<?> getAll(){
        ApiResponse response = organizationService.getAll();
        return ResponseEntity.status(response.isSuccess()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(response);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id){
        ApiResponse response = organizationService.getOne(id);
        return ResponseEntity.status(response.isSuccess()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(response);
    }
}
