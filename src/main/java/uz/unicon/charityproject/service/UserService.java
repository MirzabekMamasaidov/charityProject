package uz.unicon.charityproject.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.unicon.charityproject.entity.HelpType;
import uz.unicon.charityproject.entity.Organization;
import uz.unicon.charityproject.entity.Role;
import uz.unicon.charityproject.entity.User;
import uz.unicon.charityproject.entity.enums.RoleName;
import uz.unicon.charityproject.payload.ApiResponse;
import uz.unicon.charityproject.payload.UserDto;
import uz.unicon.charityproject.repository.HelpTypeRepository;
import uz.unicon.charityproject.repository.RoleRepository;
import uz.unicon.charityproject.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    final
    UserRepository userRepository;

    final
    RoleRepository roleRepository;

    final
    HelpTypeRepository helpTypeRepository;


    public UserService(UserRepository userRepository, RoleRepository roleRepository, HelpTypeRepository helpTypeRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.helpTypeRepository = helpTypeRepository;
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
                return new ApiResponse("Muvaffaqiyatli qo'shildi", true, savedUser);
            }
            if (userDto.getIsModerator()) {
                user.setUsername(user.getUsername());
                user.setPassword(user.getPassword());
                user.setName(user.getName());
                user.setRoles(Set.of(roleRepository.findByRoleName(RoleName.MODERATOR).get()));
                User savedUser = userRepository.save(user);
                return new ApiResponse("Muvaffaqiyatli qo'shildi", true, savedUser);
            }

            user.setName(userDto.getName());
            user.setUsername(userDto.getUsername());
            user.setPassword(userDto.getPassword());
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
            user.setRoles(Set.of(roleRepository.findByRoleName(RoleName.ROLE_USER).get()));
            // user.setOrganizationId();
            User savedUser = userRepository.save(user);
            return new ApiResponse("Muvaffaqiyatli qo'shildi", true, savedUser);

        }
        return new ApiResponse("Bunday usernameli foydalanuvchi mavjud", false);

    }

    public ApiResponse getAll() {
        List<User> userList = userRepository.findAll();
        List<User> citizens = new ArrayList<>();
        /*for (User user : userList) {
            roleRepository.findByRoleName(RoleName.ROLE_USER).get().equals(user.getRoles());
        }*/
        //return new ApiResponse("Foydalanuvchilar royhati",true,citizens);
        return new ApiResponse("Foydalanuvchilar royhati", true, userList);
    }

    public ApiResponse getById(String id) {
        Optional<User> byIdNumber = userRepository.findByIdNumber(id);
        if (byIdNumber.isEmpty()) {
            return new ApiResponse("Foydalanuvchi topilmadi", false);
        }
        User user = byIdNumber.get();
        return new ApiResponse("Ushbu fuqaro", true, user);
    }

    public ApiResponse edit(Integer id, UserDto userDto) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return new ApiResponse("Foydalanuvchi topilmadi", false);
        }
        User user = optionalUser.get();
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
        user.setRoles(Set.of(roleRepository.findByRoleName(RoleName.ROLE_USER).get()));
        User editedUser = userRepository.save(user);

        return new ApiResponse("Muvaffaqiyatli o'zgartirildi", true, editedUser);
    }

    public ApiResponse addUser(User currentUser, UserDto userDto) {
        Optional<User> byUsername = userRepository.findByUsername(userDto.getUsername());
        if (byUsername.isEmpty()) {


            User user = new User();

            user.setUsername(userDto.getUsername());
            user.setPassword(userDto.getPassword());
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
            user.setRoles(Set.of(roleRepository.findByRoleName(RoleName.ROLE_USER).get()));
            user.setOrganizationId(currentUser.getOrganizationId());
            User savedUser = userRepository.save(user);
            return new ApiResponse("Muvaffaqiyatli qo'shildi", true, savedUser);

        } else
            return new ApiResponse("Muvaffaqiyatli qo'shilmadi", false);
    }

    public ApiResponse getUserById(Integer id) {

        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return new ApiResponse("Foydalanuvchi topilmadi", false);
        }
        User user = optionalUser.get();
        return new ApiResponse("So'ralgan foydalanuvchi", true);
    }

    public ApiResponse addHelpUser(Integer id, UserDto userDto) {

        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return new ApiResponse("Foydalanuvchi topilmadi", false);
        }
        User user = optionalUser.get();

        List<HelpType> helpTypeList = helpTypes(userDto.getHelpTypeIds());
        user.setHelpTypes(helpTypeList);
        User savedUser = userRepository.save(user);
        return new ApiResponse("Foydalanuvchiga yordam berildi", true, savedUser);
    }


    public List<HelpType> helpTypes(List<Integer> helpTypeId) {
        List<HelpType> helpTypeList = new ArrayList<>();
        for (Integer id : helpTypeId) {
            Optional<HelpType> byId = helpTypeRepository.findById(id);
            byId.ifPresent(helpTypeList::add);
        }

        return helpTypeList;
    }
}
