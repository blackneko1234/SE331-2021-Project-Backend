package se331.lab.rest.security.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import se331.lab.rest.entity.Admin;
import se331.lab.rest.entity.Doctor;
import se331.lab.rest.entity.Patient;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "USERNAME", length = 50, unique = true)
    @NotNull
    private String username;

    @Column(name = "PASSWORD", length = 100)
    @NotNull
    private String password;

    @Column(name = "FIRSTNAME", length = 50)
    @NotNull
    private String firstname;

    @Column(name = "LASTNAME", length = 50)
    @NotNull
    private String lastname;

    @Column(name = "GENDER", length = 50)
    @NotNull
    private String gender;

    @Column(name = "HOMETOWN", length = 50)
    @NotNull
    private String hometown;

    @Column(name = "BIRTHDATE", length = 50)
    @NotNull
    private String birthdate;

    @Column(name = "AGE")
    @NotNull
    private Long age;

    @Column(name = "VACCINEHISTORY", length = 50)
    @NotNull
    private String vaccinehistory;

    @Column(name = "DOSE", length = 50)
    @NotNull
    private String dose;

    @Column(name = "IMAGE")
    @NotNull
    String image;

    @Column(name = "ENABLED")
    @NotNull
    private Boolean enabled;

    @Builder.Default
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Authority> authorities = new ArrayList<>();

    @Column(name = "LASTPASSWORDRESETDATE")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date lastPasswordResetDate;

    @OneToOne
    Admin admin;
    @OneToOne
    Doctor doctor;
    @OneToOne
    Patient patient;
}