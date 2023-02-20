package uz.unicon.charityproject.entity;

import lombok.*;
import uz.unicon.charityproject.entity.template.AbsEntity;
import uz.unicon.charityproject.entity.template.AbsNameEntity;

import javax.persistence.Entity;
import java.sql.Date;
import java.sql.Timestamp;

//yordam turlari (ro'zg'or, dori darmon, maishiy texnika)
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class HelpType extends AbsNameEntity {

    private String name;

    private Date date;

    private boolean isActive;

}
