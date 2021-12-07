package com.rootcode.test.practicalTest.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "ledger")
public class Ledger  implements Serializable {

    @Id
    @Column(name = "id")
    private  long id;

    private Date createdDate;
    private long balance;
    private  String currency;

    @Enumerated(EnumType.ORDINAL)
    private Type type;


    public static enum Type {
        SAVING,
        CURRENT
    }
}
