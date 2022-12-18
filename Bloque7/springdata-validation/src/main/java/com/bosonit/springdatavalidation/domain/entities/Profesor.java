package com.bosonit.springdatavalidation.domain.entities;

import com.bosonit.springdatavalidation.controllers.dtos.outputs.PersonaOutput;
import com.bosonit.springdatavalidation.controllers.dtos.outputs.StudentOutput;
import com.bosonit.springdatavalidation.controllers.dtos.outputs_full.ProfesorFullOutput;
import com.bosonit.springdatavalidation.mappers.PersonaMapper;
import com.bosonit.springdatavalidation.mappers.StudentMapper;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

@Entity
@Table(name = "profesor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Profesor {
    @Id
    @GeneratedValue()
    @Column(name = "id_profesor")
    private int id_profesor;

    //Un profesor puede ser sólo 1 persona
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_persona")
    private Persona persona;

    //Un profesor  puede tener muchos estudiantes
    @OneToMany(mappedBy = "profesor")
    private List<Student> studentList = new ArrayList<>();

    @Column(name = "comentarios")
    private String comentarios;

    @Column(nullable = false, name = "rama")
    private String rama;

    public ProfesorFullOutput profesorToProfesorFullOutput(){



        PersonaOutput personaOutput = PersonaMapper.pMapper.personaToPersonaOutput(this.persona);
        List<StudentOutput> studentOutputList = this.studentList.stream().map(student-> StudentMapper.sMapper.studentToStudentOutput(student)).toList();

        ProfesorFullOutput profesorFullOutput = new ProfesorFullOutput();

        profesorFullOutput.setPersonaOutput(personaOutput);
        profesorFullOutput.setStudents(studentOutputList);
        profesorFullOutput.setId_profesor(this.id_profesor);
        profesorFullOutput.setRama(this.rama);
        profesorFullOutput.setComentarios(this.comentarios);

        return profesorFullOutput;
    }

}
