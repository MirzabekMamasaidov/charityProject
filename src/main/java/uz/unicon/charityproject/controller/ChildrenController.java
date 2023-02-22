package uz.unicon.charityproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.unicon.charityproject.payload.ApiResponse;
import uz.unicon.charityproject.service.ChildrenService;

@RestController
@RequestMapping("/children")
public class ChildrenController {

    @Autowired
    ChildrenService childrenService;

   @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN','ROLE_ADMIN','MODERATOR')")
    @GetMapping("/{certificateNumber}")
    public HttpEntity<?> getChildByCertificateNumber(@PathVariable  String certificateNumber){
        ApiResponse response = childrenService.getChild(certificateNumber);
        return ResponseEntity.status(response.isSuccess()? HttpStatus.OK:HttpStatus.NOT_FOUND).body(response);
    }
}
