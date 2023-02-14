package uz.unicon.charityproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.unicon.charityproject.payload.ApiResponse;
import uz.unicon.charityproject.payload.UserDto;
import uz.unicon.charityproject.service.UserService;

@RestController
@RequestMapping("")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping
    public HttpEntity<?> add(@RequestBody UserDto userDto){
        ApiResponse response = userService.add(userDto);
        return ResponseEntity.status(response.isSuccess()? HttpStatus.OK:HttpStatus.CONFLICT).body(response);
    }


}
