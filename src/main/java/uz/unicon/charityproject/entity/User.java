package uz.unicon.charityproject.entity;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.unicon.charityproject.entity.template.AbsNameEntity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

//qarovchi kishi
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User  extends AbsNameEntity  implements UserDetails {

    private String username;//tizimga kirish uchun username

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

    private Boolean isAdmin;//admin yoki yo'qligini bilish uchun

    private Boolean isModerator;//moderator yoki yoqligini bilish uchun



    @OneToMany()
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Children> children;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<HelpType> helpTypes;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @ManyToOne(fetch = FetchType.EAGER)
    private Organization organizationId;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
