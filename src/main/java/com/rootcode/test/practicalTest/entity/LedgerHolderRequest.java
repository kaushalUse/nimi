package com.rootcode.test.practicalTest.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "ledger_holder_request")
public class LedgerHolderRequest implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ledger_holder_request_seq_gen")
    @SequenceGenerator(name = "ledger_holder_request_seq_gen", sequenceName = "ledger_holder_request_id_seq")
    private  long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "comment")
    private String comment;
}
