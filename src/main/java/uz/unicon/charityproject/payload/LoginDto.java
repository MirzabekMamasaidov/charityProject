package uz.unicon.charityproject.payload;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    private String userName;
    private String password;
}
