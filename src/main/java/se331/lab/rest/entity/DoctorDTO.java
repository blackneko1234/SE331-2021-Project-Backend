package se331.lab.rest.entity;

import lombok.*;
import se331.lab.rest.security.entity.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTO {
    Long id;
    String name;
    List<PatientDTO> patientlist;
    User user;
    String image;

}
