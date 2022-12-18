package com.bosonit.springdatavalidation.domain.entities.feign;

import com.bosonit.springdatavalidation.controllers.dtos.outputs.ProfesorOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "http://localhost:8080", name = "profesorFeign")
public interface ProfesorFeign {
    @GetMapping("profesor/{id}")
    ProfesorOutput getProfesorById(@PathVariable String id, @RequestParam(value = "outputType", defaultValue = "simple") String outputType);
}
