package org.backend.DTOs;

import org.backend.Models.PostAddress;
import org.springframework.stereotype.Component;

@Component
public class UserDTO {
    private String name;
    private PostAddress address;
    private String IBAN;
    private String message;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PostAddress getAddress() {
        return address;
    }

    public void setAddress(PostAddress address) {
        this.address = address;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
