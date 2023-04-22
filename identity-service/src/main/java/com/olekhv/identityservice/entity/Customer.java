package com.olekhv.identityservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @Column(name = "identity_number", length = 9, nullable = false)
    private Long identityNumber;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", length = 40, nullable = false)
    private String name;

    @Column(name = "surname", length = 40, nullable = false)
    private String surname;
}