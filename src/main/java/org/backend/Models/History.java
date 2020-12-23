package org.backend.Models;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class History {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Long amount;
    @Column
    private Long crediting;
    @Column
    private Long transferredAmount;
    @Column
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
    private LocalDateTime dateTime;
    @ManyToOne(fetch = FetchType.EAGER)
    Account account;




    public Long getId() {
        return id;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }


    public Long getCrediting() {
        return crediting;
    }

    public void setCrediting(Long crediting) {
        this.crediting = crediting;
    }

    public Long getTransferredAmount() {
        return transferredAmount;
    }

    public void setTransferredAmount(Long transferredAmount) {
        this.transferredAmount = transferredAmount;
    }


    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }


}
