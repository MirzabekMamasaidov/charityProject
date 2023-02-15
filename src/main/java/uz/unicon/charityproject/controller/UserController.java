package uz.unicon.charityproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.unicon.charityproject.entity.User;
import uz.unicon.charityproject.payload.ApiResponse;
import uz.unicon.charityproject.payload.UserDto;
import uz.unicon.charityproject.security.CurrentUser;
import uz.unicon.charityproject.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping
    public HttpEntity<?> add(@RequestBody UserDto userDto){
        ApiResponse response = userService.add(userDto);
        return ResponseEntity.status(response.isSuccess()? HttpStatus.OK:HttpStatus.CONFLICT).body(response);
    }

    @GetMapping
    public HttpEntity<?> getAll(){
        ApiResponse response = userService.getAll();
        return ResponseEntity.status(response.isSuccess()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(response);
    }

    @GetMapping("/searchById/{id}")
    public HttpEntity<?> getById(@PathVariable String id){
        ApiResponse response = userService.getById(id);
        return ResponseEntity.status(response.isSuccess()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(response);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody UserDto userDto){
        ApiResponse response = userService.edit(id, userDto);
        return ResponseEntity.status(response.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(response);
    }


    @PostMapping("/addUser")
    @PreAuthorize(value = "hasAnyRole('MODERATOR', 'ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
    public HttpEntity<?>  addUser(@CurrentUser User currentUser, @RequestBody UserDto userDto){
     ApiResponse response = userService.addUser(currentUser, userDto);
        return ResponseEntity.status(response.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(response);
    }

}
