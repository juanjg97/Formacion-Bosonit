package com.bosonit.block11uploaddownloadfiles.exceptions;

import jakarta.persistence.Entity;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class CustomError{
    Date timestamp;
    int HttpCode;
    String mensaje;
}
