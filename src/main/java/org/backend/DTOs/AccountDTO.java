package org.backend.DTOs;

import org.backend.Models.PostAddress;
import org.springframework.stereotype.Component;

@Component
public class AccountDTO {
    private String name;
    private PostAddress postAddress;
    private String IBAN;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PostAddress getPostAddress() {
        return postAddress;
    }

    public void setPostAddress(PostAddress postAddress) {
        this.postAddress = postAddress;
    }

    public String getIban() {
        return IBAN;
    }

    public void setIban(String iban) {
        this.IBAN = iban;
    }
}
