package com.sc.domain.DTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class MailDTO implements Serializable {

    @NotEmpty(message = "Obrigatory field: e-mail.")
    @Email(message = "Invalid e-mail.")
    private String mail;

    public MailDTO() {}

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

}
