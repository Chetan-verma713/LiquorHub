package com.example.liquorhub.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserDto extends BaseDto {
    private String email;
    private String password;
    private String address;
    private String phoneNumber;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
}
