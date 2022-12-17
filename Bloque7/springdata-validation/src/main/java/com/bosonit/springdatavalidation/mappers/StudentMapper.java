package com.bosonit.springdatavalidation.mappers;

import com.bosonit.springdatavalidation.controllers.dtos.inputs.StudentInput;
import com.bosonit.springdatavalidation.controllers.dtos.outputs.StudentOutput;
import com.bosonit.springdatavalidation.domain.entities.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper {
    StudentMapper sMapper = Mappers.getMapper(StudentMapper.class);

    Student studentInputToStudent (StudentInput studentInput);
    StudentOutput studentToStudentOutput (Student student);

}
