package uz.unicon.charityproject.service;

import lombok.RequiredArgsConstructor;
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

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
    final
    UserRepository userRepository;

    final
    PasswordEncoder passwordEncoder;

    final JwtProvider jwtProvider;

    final AuthenticationManager authenticationManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) userRepository.findByUsername(username).get();
    }

    /**
     *
     * @param dto
     * @return
     */
    public ApiResponse register(RegisterDto dto) {
        boolean byUsername = userRepository.existsByUsername(dto.getUsername());
        if (byUsername) {
            return new ApiResponse("This username is already exist", false);
        }
        User user = new User();
        user.setUserName(dto.getUsername());
        user.setName(dto.getFullName());
        user.setPassword(dto.getPassword());
        user.setActive(true);
        if (user.getIsOrganization()){
            user.setIsOrganization(true);
        }
        userRepository.save(user);
        return new ApiResponse("Code is sent to your email. Please verify!",true);
    }

    public ApiResponse login(LoginDto loginDto) {
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                    (loginDto.getUserName(), loginDto.getPassword()));
            User user = (User) authenticate.getPrincipal();
            String token = jwtProvider.generateToken(loginDto.getUserName(), user.getRoles());
            return new ApiResponse("Mana token",true,token);
        }catch (BadCredentialsException badCredentialsException){
            return new ApiResponse("Username or password is wrong",false);
        }
    }


}


