package uz.unicon.charityproject.payload;


import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class RegisterDto {

    private String numberOrEmail;
    private String fullName;
    private String username;
    private String password;
}
