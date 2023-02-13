package uz.unicon.charityproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.unicon.charityproject.entity.template.AbsNameEntity;

import javax.persistence.Entity;

//bolalar
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Children extends AbsNameEntity {

    private String dateOfBirth; //tug'ilgan sanasi

    private String IdNumber; //JSHSHIR(PINFL)

    private String numberCertificateOfBirth; //guvohnoma nomeri(metrka №)


}
