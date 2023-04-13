package uz.unicon.charityproject.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.unicon.charityproject.entity.*;
import uz.unicon.charityproject.entity.enums.RoleName;
import uz.unicon.charityproject.payload.ApiResponse;
import uz.unicon.charityproject.payload.ChildrenDto;
import uz.unicon.charityproject.payload.UserDto;
import uz.unicon.charityproject.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final HelpTypeRepository helpTypeRepository;

    private final ChildrenRepository childrenRepository;

    private final RegionRepository regionRepository;

    private final DistrictRepository districtRepository;

    private final OrganizationRepository organizationRepository;


    public ApiResponse add(User currentUser, UserDto userDto) {
        List<ChildrenDto> childrenDtoList = userDto.getChildrenDtoList();
        Optional<User> byUsername = userRepository.findByUsername(userDto.getUsername());
        if (byUsername.isEmpty()) {
            User user = new User();

            Optional<Organization> optionalOrganization = organizationRepository.findById(userDto.getOrganizationId());
            if (optionalOrganization.isEmpty()) {
                return new ApiResponse("tashkilot id si berilmagan",false);
            }
            Organization organization = optionalOrganization.get();

            if (userDto.getIsAdmin()) {
                user.setUsername(userDto.getUsername());
                user.setPassword(userDto.getPassword());
                user.setName(userDto.getName());
                user.setRoles(Set.of(roleRepository.findByRoleName(RoleName.ROLE_ADMIN).get()));
                user.setOrganizationId(organization);
                User savedUser = userRepository.save(user);
                return new ApiResponse("Muvaffaqiyatli qo'shildi", true, savedUser);
            }
            if (userDto.getIsModerator()) {
                user.setUsername(userDto.getUsername());
                user.setPassword(userDto.getPassword());
                user.setName(userDto.getName());
                user.setRoles(Set.of(roleRepository.findByRoleName(RoleName.MODERATOR).get()));
                user.setOrganizationId(organization);
                User savedUser = userRepository.save(user);
                return new ApiResponse("Muvaffaqiyatli qo'shildi", true, savedUser);
            }

            List<Children> childrenList = new ArrayList<>();

            for (ChildrenDto childrenDto : childrenDtoList) {
                Optional<Children> byNumberCertificateOfBirth =
                        childrenRepository.findByNumberCertificateOfBirth(childrenDto.getNumberCertificateOfBirth());

                if (byNumberCertificateOfBirth.isPresent()) {
                    Children children = byNumberCertificateOfBirth.get();
                    Boolean aBoolean = userRepository.existsUserByChildrenId(children.getId());
                    System.out.println("aBoolean = " + aBoolean);
//                    Optional<User> userByChildrenId = userRepository.getUserByChildrenId(children.getId());
                    if (aBoolean)
                        return new ApiResponse("Bu children biriktirilgan", false);
                    else
                        childrenList.add(byNumberCertificateOfBirth.get());

                } else {
                    Children children = new Children();
                    children.setName(childrenDto.getName());
                    children.setDateOfBirth(childrenDto.getDateOfBirth());
                    children.setNumberCertificateOfBirth(childrenDto.getNumberCertificateOfBirth());
                    children.setIdNumber(childrenDto.getIdNumber());
                    Children save = childrenRepository.save(children);
                    childrenList.add(save);
                }
            }

            Optional<Region> optionalRegion = regionRepository.findById(userDto.getRegionId());
            if (optionalRegion.isEmpty()) {
                return new ApiResponse("Viloyat tanlang",false);
            }

            Region region = optionalRegion.get();

         /*   Optional<District> optionalDistrict = districtRepository.getDistrictByRegionId(userDto.getRegionId());
            if (optionalDistrict.isEmpty()) {
                return new ApiResponse("Tuman tanlang",false);
            }
            District district = optionalDistrict.get();*/
            Optional<District> optionalDistrict = districtRepository.findById(userDto.getCountyId());
            if (optionalDistrict.isEmpty()) {
                return new ApiResponse("Bunday tuman topilmadi",false);
            }
            District district = optionalDistrict.get();




            user.setName(userDto.getName());
            user.setUsername(userDto.getUsername());
            user.setPassword(userDto.getPassword());
            user.setBreadWinner(userDto.getBreadWinner());
            user.setBirthOfYear(userDto.getBirthOfYear());
            user.setIdNumber(userDto.getIdNumber());
            user.setPhone(userDto.getPhone());
            user.setEmail(userDto.getEmail());
            user.setRegion(region.getNameUz());
            user.setCounty(district.getNameUz());
            user.setAddress(userDto.getAddress());
            user.setLocation(userDto.getLocation());
            user.setPrayer(userDto.getPrayer());
            user.setDocumentOfDeath(userDto.getDocumentOfDeath());
            user.setDeed(userDto.getDeed());
            user.setNumberOfChild(userDto.getNumberOfChild());
            user.setOtherInformation(userDto.getOtherInformation());
            user.setRoles(Set.of(roleRepository.findByRoleName(RoleName.ROLE_USER).get()));
            user.setOrganizationId(organization);
            user.setChildren(childrenList);
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
        //user.setRegion(userDto.getRegion());
        //user.setCounty(userDto.getCounty());
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

    /*public ApiResponse addUser(User currentUser, UserDto userDto) {
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
            // user.setRegion(userDto.getRegion());
            //user.setCounty(userDto.getCounty());
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
*/
    public ApiResponse getUserById(Integer id) {

        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return new ApiResponse("Foydalanuvchi topilmadi", false);
        }
        User user = optionalUser.get();
        return new ApiResponse("So'ralgan foydalanuvchi", true,user);
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

    public ApiResponse delete(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return new ApiResponse("Foydalanuvchi topilmadi",false);
        }
        User user = optionalUser.get();
        user.setActive(false);
        return new ApiResponse("Foydalanuvchi o'chirildi",true);
    }
}
