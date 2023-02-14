package uz.unicon.charityproject.entity;

import lombok.*;
import uz.unicon.charityproject.entity.template.AbsNameEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Organization extends AbsNameEntity {

    @OneToMany()
    private List<User> users;
}


