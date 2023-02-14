package uz.unicon.charityproject.payload;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    private String username;
    private String password;
}
