package uz.unicon.charityproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.unicon.charityproject.entity.User;
import uz.unicon.charityproject.payload.ApiResponse;
import uz.unicon.charityproject.payload.RegisterDto;
import uz.unicon.charityproject.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
    final
    UserRepository userRepository;

    final
    PasswordEncoder passwordEncoder;

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
        userRepository.save(user);
        return new ApiResponse("Code is sent to your email. Please verify!",true);




      /*  //4 xonali
        String code = UUID.randomUUID().toString().substring(0,5);//.concat(UUID.randomUUID().toString().substring(0,5));
        user.setCode(code);
        //mail chaqirib xabar jo'natish kerak
        SimpleMailMessage message = new SimpleMailMessage();
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.addHeader("content-type", "html/text");
        message.setFrom("pdp@gmail.com");
        message.setTo(dto.getNumberOrEmail());
        message.setSubject("Confirmation code");
        message.setText(code);
        message.setSentDate(new Date());
        mailSender.getEmail().send(message);*/



/*        userRepository.save(user);
        return new ApiResponse("Code is sent to your email. Please verify!",true);
    }
    public ApiResponse verify(String email, String password) {
        Optional<User> byUserName = userRepository.findByUsername(email);
        if (!byUserName.isPresent()) return new ApiResponse("Error",false);

        if (!byUserName.get().getPassword().equals(passwordEncoder.encode(password)))
            return new ApiResponse("Confirmation code is wrong",false);
        return new ApiResponse("It's a good",true);
    }

    public ApiResponse verifyCode(String code,String username) {

        Optional<User> byUsername = userRepository.findByUsername(username);
        if (!byUsername.isPresent()) {
            return new ApiResponse("User not found",false);
        }
        if (byUsername.get().getCode().equals(code)) {
            return new ApiResponse("Success",true);
        }
        return new ApiResponse("Confirmation code is wrong",false);*/

}}


