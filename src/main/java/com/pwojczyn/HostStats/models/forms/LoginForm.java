package com.pwojczyn.HostStats.models.forms;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

@Data
public class LoginForm {
    @NotBlank
    @Size(min = 5, max = 30)
    private String email;
    @NotBlank
    @Size(min = 8, max = 30)
    private String password;

    public LoginForm(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public LoginForm() {
    }
}
