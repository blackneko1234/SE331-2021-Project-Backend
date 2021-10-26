package se331.lab.rest.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import se331.lab.rest.entity.*;
import se331.lab.rest.security.entity.User;
import se331.lab.rest.security.entity.UserAuthDTO;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(imports = Collectors.class)
public interface LabMapper {
    LabMapper INSTANCE = Mappers.getMapper(LabMapper.class);

    PatientDTO getPatientDto(Patient patient);

    List<PatientDTO> getPatientDto(List<Patient> patients);

    List<DoctorDTO> getDoctorDTO(List<Doctor> doctors);

    @Mapping(target = "authorities", expression = "java(user.getAuthorities().stream().map(auth -> auth.getName().name()).collect(Collectors.toList()))")
    UserAuthDTO getUserAuthDTO(User user);

    AuthorityDTO getRegisterDTO(User user);

    List<VaccineDTO> getVaccineDTO(List<Vaccine> vaccines);

    List<AdminDTO> getAdminDTO(List<Admin> admins);

    List<UserAuthDTO> getUserDTO(List<User> users);
}
