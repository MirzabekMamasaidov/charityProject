package uz.unicon.charityproject.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.unicon.charityproject.entity.User;
import uz.unicon.charityproject.entity.enums.RoleName;
import uz.unicon.charityproject.payload.ApiResponse;
import uz.unicon.charityproject.payload.UserDto;
import uz.unicon.charityproject.repository.RoleRepository;
import uz.unicon.charityproject.repository.UserRepository;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    final
    UserRepository userRepository;

    final
    RoleRepository roleRepository;


    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    public ApiResponse add(UserDto userDto) {
        Optional<User> byUsername = userRepository.findByUsername(userDto.getUsername());
        if (byUsername.isEmpty()) {


            User user = new User();
            if (userDto.getIsAdmin()) {
                user.setUsername(user.getUsername());
                user.setPassword(user.getPassword());
                user.setName(user.getName());
                user.setRoles(Set.of(roleRepository.findByRoleName(RoleName.ROLE_ADMIN).get()));
                User savedUser = userRepository.save(user);
                return new ApiResponse("Muvaffaqiyatli qo'shildi",true,savedUser);
            }
            if (userDto.getIsModerator()){
                user.setUsername(user.getUsername());
                user.setPassword(user.getPassword());
                user.setName(user.getName());
                user.setRoles(Set.of(roleRepository.findByRoleName(RoleName.MODERATOR).get()));
                User savedUser = userRepository.save(user);
                return new ApiResponse("Muvaffaqiyatli qo'shildi",true,savedUser);
            }

            user.setUsername(userDto.getUsername());
            user.setUsername(userDto.getUsername());
            user.setBreadWinner(userDto.getBreadWinner());
            user.setBirthOfYear(userDto.getBirthOfYear());
            user.setIdNumber(userDto.getIdNumber());
            user.setPhone(userDto.getPhone());
            user.setEmail(userDto.getEmail());
            user.setRegion(userDto.getRegion());
            user.setCounty(userDto.getCounty());
            user.setAddress(userDto.getAddress());
            user.setLocation(userDto.getLocation());
            user.setPrayer(userDto.getPrayer());
            user.setDocumentOfDeath(userDto.getDocumentOfDeath());
            user.setDeed(userDto.getDeed());
            user.setNumberOfChild(userDto.getNumberOfChild());
            user.setOtherInformation(userDto.getOtherInformation());
           // user.setOrganizationId();
            User savedUser = userRepository.save(user);
            return new ApiResponse("Muvaffaqiyatli qo'shildi",true,savedUser);

        }
        return new ApiResponse("Bunday usernameli foydalanuvchi mavjud",false);

    }
}
