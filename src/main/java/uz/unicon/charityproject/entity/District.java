package uz.unicon.charityproject.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "districts")
public class District {
    @Id
    private Integer id;



    private String nameUz;


    private String nameRu;

    @ManyToOne
    private Region region;




}
