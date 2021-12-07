package com.rootcode.test.practicalTest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LedgerHolderRequestDTO implements Serializable {

    private long id;
    private String name;
    private String email;
    private String comment;

}
