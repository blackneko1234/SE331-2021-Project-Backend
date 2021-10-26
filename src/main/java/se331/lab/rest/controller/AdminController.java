package se331.lab.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se331.lab.rest.entity.*;
import se331.lab.rest.repository.DoctorRepository;
import se331.lab.rest.repository.PatientRepository;
import se331.lab.rest.repository.VaccineRepository;
import se331.lab.rest.security.entity.Authority;
import se331.lab.rest.security.entity.AuthorityName;
import se331.lab.rest.security.entity.User;
import se331.lab.rest.security.entity.UserAuthDTO;
import se331.lab.rest.security.repository.AuthorityRepository;
import se331.lab.rest.security.repository.UserRepository;
import se331.lab.rest.service.AdminService;
import se331.lab.rest.service.PatientService;
import se331.lab.rest.service.UserService;
import se331.lab.rest.util.LabMapper;

import javax.transaction.Transactional;

@CrossOrigin(maxAge = 3600)
@RestController
public class AdminController {
    @Autowired
    AdminService adminService;

    @Autowired
    UserService userService;

    @Autowired
    PatientService patientService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    VaccineRepository vaccineRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    @GetMapping("/admins")
    ResponseEntity<?> getAdmins() {
        return ResponseEntity.ok(LabMapper.INSTANCE.getAdminDTO(adminService.getAllAdmin()));
    }

    @GetMapping("/admins/console")
    public ResponseEntity<?> getPatientLists(@RequestParam(value = "_limit", required = false) Integer perPage
            , @RequestParam(value = "_page", required = false) Integer page, @RequestParam(value = "title", required = false) String title) {
        perPage = perPage == null ? userService.getUserSize() : perPage;
        page = page == null ? 1 : page;
        Page<User> pageOutput;
        pageOutput = userService.getUsers(perPage, page);
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("x-total-count", String.valueOf(pageOutput.getTotalElements()));
        return new ResponseEntity<>(LabMapper.INSTANCE.getUserDTO(pageOutput.getContent()), responseHeader, HttpStatus.OK);
    }

    @Transactional
    @PatchMapping("/changeRole/{id}")
    public ResponseEntity<UserAuthDTO> updateUser(@PathVariable("id") Long id, @RequestBody UserAuthDTO user) {
        User tempUser = userRepository.findById(id).get();
        tempUser.setEnabled(true);
        if (user.getAuthorities().get(0).equals("ROLE_USER")) {
            Authority a = authorityRepository.findByName(AuthorityName.ROLE_USER);
            tempUser.getAuthorities().clear();
            tempUser.getAuthorities().add(a);
        } else if (user.getAuthorities().get(0).equals("ROLE_DOCTOR")) {
            Authority a = authorityRepository.findByName(AuthorityName.ROLE_DOCTOR);
            tempUser.getAuthorities().clear();
            tempUser.getAuthorities().add(a);
        }

        if (tempUser.getAuthorities().get(0).getName().equals(AuthorityName.ROLE_USER)) {
            Patient patient = patientRepository.save(Patient.builder()
                    .user(tempUser)
                    .build());
            patient.setUser(tempUser);
            tempUser.setPatient(patient);

        } else if (tempUser.getAuthorities().get(0).getName().equals(AuthorityName.ROLE_DOCTOR)) {
            Doctor doctor = doctorRepository.save(Doctor.builder()
                    .user(tempUser)
                    .build());
            doctor.setUser(tempUser);
            tempUser.setDoctor(doctor);
        }
        return ResponseEntity.ok(LabMapper.INSTANCE.getUserAuthDTO(tempUser));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UserAuthDTO> deleteUser(@PathVariable("id") Long id) {
        User tempUser = userRepository.findById(id).get();
        userRepository.delete(tempUser);
        return ResponseEntity.ok(LabMapper.INSTANCE.getUserAuthDTO(tempUser));
    }

    /* @PostMapping("/AddVaccineToPatient/{id}")
     public ResponseEntity<VaccineDTO> addVaccine(@PathVariable("id") Long id)*{
         Patient output = patientRepository.findById(id).get();
         Vaccine vaccine = vaccineRepository.findAll().get(0);
         User user = userRepository.findById(id).get();
         if (vaccine.getName().equals("AstraZeneca")) {
             Vaccine vac = vaccineRepository.findAll().get(0);
             user.setDose(user.getDose() + 1);
             output.getVaccinelist().add(vac);
         } else if (vaccine.getName().equals("Pfizer")) {
             Vaccine vac = vaccineRepository.findAll().get(0);
             user.setDose(user.getDose() + 1);
             output.getVaccinelist().add(vac);
         } else if (vaccine.getName().equals("Moderna")) {
             Vaccine vac = vaccineRepository.findAll().get(0);
             output.getVaccinelist().add(vac);
         } else if (vaccine.getName().equals("Sinovac")) {
             Vaccine vac = vaccineRepository.findAll().get(0);
             user.setDose(user.getDose() + 1);
             output.getVaccinelist().add(vac);
         }
         return ResponseEntity.ok(LabMapper.INSTANCE.getVaccineDTO(output));
     }*/

    @PostMapping("/AddVaccineToPatient/{id}")
    public ResponseEntity<VaccineUserDTO> addVaccine(@PathVariable("id") Long id, @RequestBody VaccineUser vaccineUser) {
        Patient patient = patientRepository.findById(id).get();
        vaccineUser.setPatient(patient);
        VaccineUser output = patientService.save(vaccineUser);
        return ResponseEntity.ok(LabMapper.INSTANCE.getVaccineUserDTO(output));
    }
}