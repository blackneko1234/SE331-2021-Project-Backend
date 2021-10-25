package se331.lab.rest.security.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthDTO {
    long id;
    String username;
    String firstname;
    String lastname;
    String birthdate;
    String gender;
    String hometown;
    Long age;
    String dose;
    String vaccinehistory;
    String image;
    Boolean enabled;
    List<String> authorities;

}
