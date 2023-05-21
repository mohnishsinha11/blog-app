package com.camigos.blog.payloads;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private int id;

    @NotEmpty(message = "Username cannot be empty")
    @Size(min=4, message = "Username must be min of 4 characters !!")
    private String name;

    @Email(message = "Email address is Invalid !!")
    private String email;

    @NotEmpty
    @Size(min=3, max=10, message = "Password must be min of 3 chars and max of 10 chars !!")
    //@Pattern(regexp = "")   we can also use regexp pattern here
    private String password;

    @NotEmpty(message = "About field cannot be empty !!")
    private String about;
}
