package com.bosonit.block13mongodb.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class PersonInput {
    private String id;
    private String name;
    private String surname;
    private String user;
    private String password;
    private String company_email;
    private String personal_email;
}
