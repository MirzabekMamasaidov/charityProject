package uz.unicon.charityproject.entity;

import lombok.*;
import uz.unicon.charityproject.entity.template.AbsNameEntity;

import javax.persistence.Entity;

//bolalar
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Children extends AbsNameEntity {

    private String dateOfBirth; //tug'ilgan sanasi

    private String IdNumber; //JSHSHIR(PINFL)

    private String numberCertificateOfBirth; //guvohnoma nomeri(metrka №)


}
