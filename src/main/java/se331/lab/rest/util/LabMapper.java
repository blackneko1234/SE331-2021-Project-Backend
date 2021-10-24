package se331.lab.rest.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import se331.lab.rest.entity.*;
import se331.lab.rest.security.entity.User;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(imports = Collectors.class)
public interface LabMapper {
    LabMapper INSTANCE = Mappers.getMapper(LabMapper.class);

    PatientDTO getPatientDto(Patient patient);

    List<PatientDTO> getPatientDto(List<Patient> patients);

    DoctorDTO getDoctorDTO(Doctor doctor);

    List<DoctorDTO> getDoctorDTO(List<Doctor> doctors);

    @Mapping(target = "authorities", expression = "java(patient.getUser().getAuthorities().stream().map(auth -> auth.getName().name()).collect(Collectors.toList()))")
    PatientAuthDTO getPatientAuthDTO(Patient patient);

    AuthorityDTO getRegisterDTO(User user);

    List<VaccineDTO> getVaccineDTO(List<Vaccine> vaccines);

}
