package com.app.Walletapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Field cannot be blank")
    private String name;
    private String accountNumber;
    private String description;
    @Min(1)
    @Max(3)
    private int priority;// 1 high,2 med, 3 low
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "wallet")
    @JsonIgnore
    private List<Transaction> transactions;

    private double currentBalance;

    @PrePersist
    public void setBalance(){
        this.currentBalance = 0.0;
    }

}
