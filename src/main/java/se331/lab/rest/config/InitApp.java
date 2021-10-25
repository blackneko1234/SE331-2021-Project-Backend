package se331.lab.rest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import se331.lab.rest.entity.*;
import se331.lab.rest.repository.*;
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
    @Autowired
    CommentRepository commentRepository;

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
                .name("MorPeeh")
                .user(user3)
                .build());
        doc1.setUser(user3);
        user3.setDoctor(doc1);

        Vaccine vac1, vac2;
        vac1 = vaccineRepository.save(Vaccine.builder()
                .name("AstraZeneca")
                .type("1")
                .build());
        vac2 = vaccineRepository.save(Vaccine.builder()
                .name("Pfizer")
                .type("2")
                .build());

        Patient pat1;
        pat1 = patientRepository.save(Patient.builder()
                .name("Dang")
                .surname("Guitar")
                .gender("Male")
                .hometown("Everywhere")
                .age(36L)
                .status("Im fine")
                .build());
        pat1.setUser(user2);
        user2.setPatient(pat1);

        pat1.getVaccinelist().add(vac1);
        pat1.getVaccinelist().add(vac2);
        vac1.getPatient().add(pat1);
        vac2.getPatient().add(pat1);

        Comment ment1;
        ment1 = commentRepository.save(Comment.builder()
                .description("Go die")
                .commentDate("12 September 2021")
                .build());
        pat1.getCommentList().add(ment1);
        doc1.getCommentList().add(ment1);
        ment1.setSendComment(doc1);
        ment1.setReceiveComment(pat1);
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
        user2.getAuthorities().add(authUser);
        user3.getAuthorities().add(authDoctor);
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

    }
}
