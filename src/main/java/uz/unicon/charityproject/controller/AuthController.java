package uz.unicon.charityproject.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import uz.unicon.charityproject.entity.User;
import uz.unicon.charityproject.payload.ApiResponse;
import uz.unicon.charityproject.payload.LoginDTO;
import uz.unicon.charityproject.payload.RegisterDto;
import uz.unicon.charityproject.security.CurrentUser;
import uz.unicon.charityproject.security.JwtProvider;
import uz.unicon.charityproject.service.AuthService;



@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AuthService authService;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody LoginDTO loginDTO){
        String token=jwtProvider.generateToken(loginDTO.getUserName());
        return ResponseEntity.ok().body(token);
    }
    @GetMapping("/me")
    public HttpEntity<?> getMe(@CurrentUser User user) { //Parametr
        return ResponseEntity.ok().body("Mana" + user);
    }


    @PostMapping("/register")
    public HttpEntity<?> register(@RequestBody RegisterDto dto)  {
        ApiResponse response = authService.register(dto);
        return ResponseEntity.status(response.isSuccess()?
                HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(response);
    }
   /* @GetMapping("/verifyEmail")
    public HttpEntity<?> verify(@RequestParam String email, @RequestParam String code, @RequestBody String password) {

        ApiResponse response = authService.verify(email, password);
        return ResponseEntity.status(response.isSuccess()?
                HttpStatus.OK:HttpStatus.CONFLICT).body(response);
    }*/

  /*  @GetMapping("/verifyCode")
    public HttpEntity<?> verifyCode(@RequestParam("code") String code,@RequestParam("username") String username) {

        *//*ApiResponse response = authService.verify(email, password);
        return ResponseEntity.status(response.isSuccess()?
                HttpStatus.OK:HttpStatus.CONFLICT).body(response);*//*
       *//* @CurrentUser
        User user = null;
        if(user.getCode().equals(code)){
            user.setEnabled(true);
        }else {
            user.setEnabled(false);
        }*//*
        ApiResponse response = authService.verifyCode(code, username);
        return ResponseEntity.status(response.isSuccess()?200:409).body(response);

    }*/


}
