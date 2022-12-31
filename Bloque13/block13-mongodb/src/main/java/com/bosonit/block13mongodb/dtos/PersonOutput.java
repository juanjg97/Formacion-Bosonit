package com.bosonit.block13mongodb.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class PersonOutput {
    private String user;
    private String name;
    private String surname;
    private String company_email;
    private String personal_email;
}
