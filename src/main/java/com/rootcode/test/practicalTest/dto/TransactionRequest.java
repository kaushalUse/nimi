package com.rootcode.test.practicalTest.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class TransactionRequest {

    private Long amount;
    private String currency;
    private Long fromLedger;
    private Long toLedger;
    private Date createdDate;
    private String type;
}
