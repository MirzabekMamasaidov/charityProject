package uz.unicon.charityproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.unicon.charityproject.payload.ApiResponse;
import uz.unicon.charityproject.payload.OrganizationDto;
import uz.unicon.charityproject.service.OrganizationService;

@RestController
@RequestMapping("/organization")
public class OrganizationController {

    @Autowired
    OrganizationService organizationService;


   // @PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
    @PostMapping
    public HttpEntity<?> add(@RequestBody OrganizationDto organizationDto){
        ApiResponse response = organizationService.add(organizationDto);
        return ResponseEntity.status(response.isSuccess()? HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(response);
    }

    @PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
    @GetMapping
    public HttpEntity<?> getAll(){
        ApiResponse response = organizationService.getAll();
        return ResponseEntity.status(response.isSuccess()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(response);
    }

    //@PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id){
        ApiResponse response = organizationService.getOne(id);
        return ResponseEntity.status(response.isSuccess()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(response);
    }

    //@PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, OrganizationDto organizationDto){
        ApiResponse response = organizationService.edit(id, organizationDto);
        return ResponseEntity.status(response.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(response);
    }

    //@PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        ApiResponse response = organizationService.delete(id);
        return ResponseEntity.status(response.isSuccess()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(response);
    }
}
