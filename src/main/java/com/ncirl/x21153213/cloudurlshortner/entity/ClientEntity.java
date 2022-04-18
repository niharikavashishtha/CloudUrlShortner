package com.ncirl.x21153213.cloudurlshortner.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;



@Table(name = "Client")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long clientId;
    private String clientName;
    private String clientEmail;
    private String clientAccountNumber;
    private long totalApiHits;
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "apiKey", unique = true, updatable = false)
    private String apiKey;
}
