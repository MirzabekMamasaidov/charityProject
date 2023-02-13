package uz.unicon.charityproject.entity;

import lombok.*;
import uz.unicon.charityproject.entity.template.AbsEntity;
import uz.unicon.charityproject.entity.template.AbsNameEntity;

import javax.persistence.Entity;

//yordam turlari (ro'zg'or, dori darmon, maishiy texnika)
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class HelpType extends AbsNameEntity {

    private String name;

    private boolean isActive;

}
