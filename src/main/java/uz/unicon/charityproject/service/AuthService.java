package uz.unicon.charityproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.unicon.charityproject.entity.User;
import uz.unicon.charityproject.payload.ApiResponse;
import uz.unicon.charityproject.payload.LoginDto;
import uz.unicon.charityproject.payload.RegisterDto;
import uz.unicon.charityproject.repository.UserRepository;
import uz.unicon.charityproject.security.JwtProvider;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
    final UserRepository userRepository;
    final PasswordEncoder passwordEncoder;

    final JwtProvider jwtProvider;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).get();
    }

    public ApiResponse register(RegisterDto dto) {
        boolean byUsername = userRepository.existsByUsername(dto.getUsername());
        if (byUsername) {
            return new ApiResponse("This username is already exist", false);
        }
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setName(dto.getFullName());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setActive(true);
        /*if (user.getIsOrganization()){
            user.setIsOrganization(true);
        }*/
        userRepository.save(user);
        return new ApiResponse("Code is sent to your email. Please verify!",true);
    }

    public ApiResponse login(LoginDto dto) {
        Optional<User> byUsername = userRepository.findByUsername(dto.getUsername());
        if (byUsername.isPresent()) {
            User user = byUsername.get();
            if (passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
                String token = jwtProvider.generateToken(dto.getUsername());
                return new ApiResponse("Succes", true, token);
            } else {
                return new ApiResponse("Password is failed", false);
            }
        }
        return new ApiResponse("User not found", false);
    }

}


