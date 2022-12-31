package com.bosonit.block13mongodb.domain.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Document
public class Person {
    @Id
    private String id;
    @Indexed(unique = true)
    private String user;
    private String password;
    private String name;
    private String surname;
    private String company_email;
    private String personal_email;

    public Person(String user, String password, String name, String surname, String company_email, String personal_email) {
        this.user = user;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.company_email = company_email;
        this.personal_email = personal_email;
    }
}
