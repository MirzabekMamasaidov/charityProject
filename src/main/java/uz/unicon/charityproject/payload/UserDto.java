package uz.unicon.charityproject.payload;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String userName;

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

    private Boolean isOrganization;



}
