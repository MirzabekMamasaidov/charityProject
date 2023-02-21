package uz.unicon.charityproject.payload;

import lombok.*;
import uz.unicon.charityproject.entity.Children;
import uz.unicon.charityproject.entity.HelpType;
import uz.unicon.charityproject.entity.Role;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String username;

    private String name;

    private String password;

    private String breadWinner;

    private String birthOfYear;

    private String idNumber;

    private String phone;

    private String email;

    private String region;

    private String county;

    private String address;

    private String location;

    private String prayer;

    private String documentOfDeath;

    private Boolean deed;

    private Integer numberOfChild;

    private String otherInformation;

    private Integer organizationId;

    private Boolean isAdmin = false;

    private Boolean isModerator = false;

    private List<Integer> childrenIds;

    private List<Integer> helpTypeIds;

    private List<Integer> roleIds;

}
