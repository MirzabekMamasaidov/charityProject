package uz.unicon.charityproject.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import uz.unicon.charityproject.entity.User;
import uz.unicon.charityproject.payload.ApiResponse;
import uz.unicon.charityproject.payload.LoginDto;
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

    public AuthController(AuthenticationManager authenticationManager, AuthService authService, JwtProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.authService = authService;
        this.jwtProvider = jwtProvider;
    }
    @Autowired

    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody LoginDto dto){
        ApiResponse response = authService.login(dto);
        return ResponseEntity.status(response.isSuccess()?200:401).body(response);
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



}
