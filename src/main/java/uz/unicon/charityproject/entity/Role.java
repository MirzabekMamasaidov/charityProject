package uz.unicon.charityproject.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import uz.unicon.charityproject.entity.enums.RoleName;

import javax.persistence.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    @Override
    public String getAuthority() {
        return String.valueOf(this.roleName);
    }
}
