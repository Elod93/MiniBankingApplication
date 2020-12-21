package org.backend.DTOs;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;

@Component
public class TransferDTO {
    @NotEmpty
    private String IBAN;
    @NotEmpty
    private Long bill;

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public Long getBill() {
        return bill;
    }

    public void setBill(Long bill) {
        this.bill = bill;
    }
}
