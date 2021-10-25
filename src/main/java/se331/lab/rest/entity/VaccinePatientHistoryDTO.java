package se331.lab.rest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VaccinePatientHistoryDTO {
    Long id;
    String username;
    String name;
    String surname;
    Long age;
    String gender;
    String hometown;
    String status;
}