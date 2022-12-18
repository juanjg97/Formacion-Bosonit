package com.bosonit.springdatavalidation.domain.entities;


import com.bosonit.springdatavalidation.controllers.dtos.outputs.ProfesorOutput;
import com.bosonit.springdatavalidation.controllers.dtos.outputs.StudentOutput;
import com.bosonit.springdatavalidation.controllers.dtos.outputs_full.PersonaFullOutput;
import com.bosonit.springdatavalidation.mappers.ProfesorMapper;
import com.bosonit.springdatavalidation.mappers.StudentMapper;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

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

    public PersonaFullOutput personaToPersonaFullOutput() {
        ProfesorOutput pO = (Objects.equals(this.profesor, null)) ? null : (ProfesorMapper.pMapper.profesorToProfesorOutput(this.profesor));
        StudentOutput sO = (Objects.equals(this.student,null)) ? null: (StudentMapper.sMapper.studentToStudentOutput(this.student));

        PersonaFullOutput personaFullOutput = new PersonaFullOutput();

        personaFullOutput.setProfesorOutput(pO);
        personaFullOutput.setStudentOutput(sO);
        personaFullOutput.setId_usuario(this.id_usuario);
        personaFullOutput.setCity(this.city);
        personaFullOutput.setActive(this.active);
        personaFullOutput.setCity(this.city);
        personaFullOutput.setName(this.name);
        personaFullOutput.setUsuario(this.usuario);
        personaFullOutput.setPassword(this.password);
        personaFullOutput.setSurname(this.surname);
        personaFullOutput.setCreated_date(this.created_date);
        personaFullOutput.setCompany_email(this.company_email);
        personaFullOutput.setImage_url(this.image_url);
        personaFullOutput.setTermination_date(this.termination_date);

        return personaFullOutput;
    }

}
