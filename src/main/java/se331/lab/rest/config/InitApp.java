package se331.lab.rest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import se331.lab.rest.entity.Admin;
import se331.lab.rest.entity.Doctor;
import se331.lab.rest.entity.Patient;
import se331.lab.rest.entity.Vaccine;
import se331.lab.rest.repository.AdminRepository;
import se331.lab.rest.repository.PatientRepository;
import se331.lab.rest.repository.DoctorRepository;
import se331.lab.rest.repository.VaccineRepository;
import se331.lab.rest.security.entity.Authority;
import se331.lab.rest.security.entity.AuthorityName;
import se331.lab.rest.security.entity.User;
import se331.lab.rest.security.repository.AuthorityRepository;
import se331.lab.rest.security.repository.UserRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    AuthorityRepository authorityRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    VaccineRepository vaccineRepository;
    @Autowired
    AdminRepository adminRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        addUser();
        Admin admin1;
        admin1 = adminRepository.save(Admin.builder()
                .user(user1)
                .build());
        admin1.setUser(user1);
        user1.setAdmin(admin1);

        Doctor doc1;
        doc1 = doctorRepository.save(Doctor.builder()
                .name("boonlert")
                .build());
        doc1.setUser(user3);
        user3.setDoctor(doc1);

        Vaccine vac1, vac2;
        vac1 = vaccineRepository.save(Vaccine.builder()
                .name("Tood")
                .type("1")
                .build());
        vac2 = vaccineRepository.save(Vaccine.builder()
                .name("Tood")
                .type("2")
                .build());

        Patient pat1;
        pat1 = patientRepository.save(Patient.builder()
                .name("Peter")
                .surename("Parkcar")
                .age(30L)
                .gender("Male")
                .hometown("Road")
                .status("")
                .build());
        pat1.setUser(user2);
        user2.setPatient(pat1);
        pat1.getVaccinelist().add(vac1);
        pat1.getVaccinelist().add(vac2);


    }

    User user1, user2, user3;

    private void addUser() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        Authority authUser = Authority.builder().name(AuthorityName.ROLE_USER).build();
        Authority authAdmin = Authority.builder().name(AuthorityName.ROLE_ADMIN).build();
        Authority authDoctor = Authority.builder().name(AuthorityName.ROLE_DOCTOR).build();
        user1 = User.builder()
                .username("admin")
                .password(encoder.encode("admin"))
                .firstname("admin")
                .lastname("admin")
                .gender("Male")
                .hometown("Chaing Mai")
                .birthdate("18 October 2014")
                .age(26L)
                .vaccinehistory("2 times")
                .dose("Second dose")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();

        user2 = User.builder()
                .username("user")
                .password(encoder.encode("user"))
                .firstname("user")
                .lastname("user")
                .gender("Male")
                .hometown("Chaing Mai")
                .birthdate("18 October 2014")
                .age(26L)
                .vaccinehistory("2 times")
                .dose("Second dose")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();

        user3 = User.builder()
                .username("doctor")
                .password(encoder.encode("doctor"))
                .firstname("doctor")
                .lastname("doctor")
                .gender("Male")
                .hometown("Chaing Mai")
                .birthdate("18 October 2014")
                .age(26L)
                .vaccinehistory("2 times")
                .dose("Second dose")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();
        authorityRepository.save(authUser);
        authorityRepository.save(authAdmin);
        authorityRepository.save(authDoctor);
        user1.getAuthorities().add(authAdmin);
        user1.getAuthorities().add(authUser);
        user2.getAuthorities().add(authUser);
        user3.getAuthorities().add(authUser);
        user3.getAuthorities().add(authDoctor);
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

    }
}
