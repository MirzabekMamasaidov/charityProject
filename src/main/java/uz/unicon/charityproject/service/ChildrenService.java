package uz.unicon.charityproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.unicon.charityproject.entity.Children;
import uz.unicon.charityproject.entity.User;
import uz.unicon.charityproject.payload.ApiResponse;
import uz.unicon.charityproject.repository.ChildrenRepository;
import uz.unicon.charityproject.repository.UserRepository;

import java.util.Optional;

@Service
public class ChildrenService {

    @Autowired
    ChildrenRepository childrenRepository;

    @Autowired
    UserRepository userRepository;


    public ApiResponse getChild(String certificateNumber) {
        Optional<User> optionalUser = userRepository.getChildrenInfoByCertificateNumber(certificateNumber);
        if (optionalUser.isEmpty()) {
            return new ApiResponse("Bunday foydalanuvchi topilmadi",false);
        }
        User user = optionalUser.get();
        return new ApiResponse("Foydalanuvchi",true,user);
    }

}
