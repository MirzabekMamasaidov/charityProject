package uz.unicon.charityproject.payload;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class HelpTypeDto {

    private String name;

    private Date date;

    private Boolean isActive;
}
