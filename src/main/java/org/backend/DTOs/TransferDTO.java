package org.backend.DTOs;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Component
public class TransferDTO {
    @NotEmpty(message = "Please enter your Account IBAN")
    private String myIBAN;
    @NotEmpty(message = "Please enter  IBAN")
    private String IBAN;
    @NotNull(message = "Please enter bill")
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

    public String getMyIBAN() {
        return myIBAN;
    }

    public void setMyIBAN(String myIBAN) {
        this.myIBAN = myIBAN;
    }
}
