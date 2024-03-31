package com.system.bank.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    @Column(nullable = false)
    private Double amount;
    @Column(nullable = false)
    private String notes;
    private Date timestamp;
    @ManyToOne
    @JoinColumn(name = "account_id" , referencedColumnName = "id")
    private Account account;

    public Transaction(double amount, Account account, TransactionType transactionType) {
        this.amount = amount;
        this.account = account;
        this.type = transactionType;
    }
}
