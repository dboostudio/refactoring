package com.example.refactoring_ex.ch1_first_ex;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Builder
public class Invoice {
    private String customer;
    private List<Perf> performances;


}
