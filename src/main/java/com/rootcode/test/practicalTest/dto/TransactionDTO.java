package com.rootcode.test.practicalTest.dto;

import com.rootcode.test.practicalTest.entity.Ledger;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO implements Serializable {

    private long id;
    private Type type;
    private BigDecimal amount;
    private String currency;
    private Ledger fromLedger;
    private Ledger toLedger;
    private Date createdDate;

    public static enum Type {
        PURCHASE,
        REVERT

    }
}
