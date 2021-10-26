package se331.lab.rest.security.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import se331.lab.rest.entity.VaccineDTO;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    Long id;
    String username;
    String firstname;
    String lastname;
    String birthdate;
    String gender;
    String hometown;
    Long age;
    String status;
    List<VaccineDTO> vaccineList;
}