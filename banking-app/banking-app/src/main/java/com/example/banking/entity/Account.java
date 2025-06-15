package com.example.banking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity   // Makes this class As a JPA Entity Using @Entity
@Table(name="accounts") // Created For The Table name

public class Account {
    @Id // For PK in DB
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // It will Automatically increment PK
    private Long id;     // Fileds are Created

    @Column(name="Account_Holder_Name")
    private String accountholdername;

    @Column(name="Balance")
    private double balance;

}
