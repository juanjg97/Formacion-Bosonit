package com.bosonit.springdatavalidation.controllers.dtos.outputs_full;

import com.bosonit.springdatavalidation.controllers.dtos.outputs.PersonaOutput;
import com.bosonit.springdatavalidation.domain.entities.Persona;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudentOutputFull {
    PersonaOutput personaOutput;
}

