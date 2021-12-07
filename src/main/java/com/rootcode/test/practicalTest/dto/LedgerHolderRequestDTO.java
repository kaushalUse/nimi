package com.rootcode.test.practicalTest.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class LedgerHolderRequestDTO implements Serializable {

    private long id;
    private String name;
    private String email;
    private String comment;

}
