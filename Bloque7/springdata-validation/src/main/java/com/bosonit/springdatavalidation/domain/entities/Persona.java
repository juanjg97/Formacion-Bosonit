package com.bosonit.springdatavalidation.domain.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "persona")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Persona {
    @Id
    @GeneratedValue
    @Column(name = "id_usuario")
    int id_usuario;
    @Column(name = "usuario")
    String usuario;
    @Column(name = "password")
    String password;
    @Column(name = "name")
    String name;
    @Column(name = "surname")
    String surname;
    @Column(name = "company_email")
    String company_email;
    @Column(name = "personal_email")
    String personal_email;
    @Column(name = "city")
    String city;
    @Column(name = "active")
    boolean active;
    @Column(name = "created_date")
    Date created_date;
    @Column(name = "image_url")
    String image_url;
    @Column(name = "termination_date")
    Date termination_date;

    //Una persona puede ser un estidante
    @OneToOne(cascade = CascadeType.ALL)
    private Student student;

    //Una persona puede ser un profesor
    @OneToOne(cascade = CascadeType.ALL)
    private Profesor profesor;


}
