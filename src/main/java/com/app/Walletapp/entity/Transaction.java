package com.app.Walletapp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Min(1)
    @NotNull(message = "This field cannot be blank")
    private Double amount;

    private String description;
    @Min(1)
    @Max(3)
    private int type; //1 income 2 expense 3 transfer
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date transactionDate;
    //with eager if transaction is fetched then wallet will also be fetched
    @ManyToOne
    @JoinColumn(name="wallet_id",nullable = false)
    @JsonIgnore
    //to avoid the infinite two way loop
    private Wallet wallet;

    @PrePersist
    public void date(){
        this.transactionDate = new Date();
    }
}
