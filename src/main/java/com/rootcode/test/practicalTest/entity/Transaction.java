package com.rootcode.test.practicalTest.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "transaction")
public class Transaction implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_seq_gen")
    @SequenceGenerator(name = "transaction_seq_gen", sequenceName = "transaction_id_seq")
    private  long id;

    @Enumerated(EnumType.ORDINAL)
    private Type type;

    private BigDecimal amount;

    private String currency;

    @ManyToOne
    @JoinColumn(name = "from_ledger_id")
    private Ledger fromLedger;

    @ManyToOne
    @JoinColumn(name = "to_ledger_id")
    private Ledger toLedger;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    public static enum Type {
        PURCHASE,
        REVERT

    }
}
