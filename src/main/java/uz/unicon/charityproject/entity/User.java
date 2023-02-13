package uz.unicon.charityproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.unicon.charityproject.entity.template.AbsNameEntity;

import javax.persistence.Entity;
import java.util.Collection;

//qarovchi kishi
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User  extends AbsNameEntity  implements UserDetails {

private String userName;//tizimga kirish uchun username

private String password;//tizimga kirish uchun parol

private String breadWinner; //qarovchi(ona, buvi)

private String birthOfYear; //tugilgan yili

private String idNumber; //JSHSHIR(PINFL)

private String phone; //telefon raqami

private String email; //elektron pochta

private String region; //viloyat

private String county; //tuman

private String address; //adres(mahalla, uy)

private String location; //lokatsiya(https://maps.google.com/maps?q=40.29,69.184005&ll=40.298244,69)

private String prayer; //ro'mol/ibodat

private String documentOfDeath; //er o'lim hujjati

private Boolean deed;//dalolatnoma(bor, yo'q)

private Integer numberOfChild; //bolalari soni

private String otherInformation; //boshqa ma'lumotlar

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
