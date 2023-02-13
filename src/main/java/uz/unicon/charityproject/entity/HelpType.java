package uz.unicon.charityproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.unicon.charityproject.entity.template.AbsEntity;
import uz.unicon.charityproject.entity.template.AbsNameEntity;

import javax.persistence.Entity;

//yordam turlari (ro'zg'or, dori darmon, maishiy texnika)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class HelpType extends AbsNameEntity {

    private String name;

    private boolean isActive;

}
