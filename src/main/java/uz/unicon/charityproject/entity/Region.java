package uz.unicon.charityproject.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "regions")
public class Region {
    @Id
    private Integer id;

    private String nameUz;


    private String nameRu;



}
