package com.bosonit.springdatavalidation.controllers.dtos.outputs;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonaOutput {
    int id_usuario;
    String usuario;
    String password;
    String name;
    String surname;
    String company_email;
    String personal_email;
    String city;
    boolean active;
    Date created_date;
    @JsonProperty("imagen_url")
    String image_url;
    Date termination_date;

    @Override
    public String toString() {
        return "PersonaOutput{" +
                "id_usuario=" + id_usuario +
                ", usuario='" + usuario + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", company_email='" + company_email + '\'' +
                ", personal_email='" + personal_email + '\'' +
                ", city='" + city + '\'' +
                ", active=" + active +
                ", created_date=" + created_date +
                ", image_url='" + image_url + '\'' +
                ", termination_date=" + termination_date +
                '}';
    }
}
