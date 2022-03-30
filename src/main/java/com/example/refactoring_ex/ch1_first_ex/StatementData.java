package com.example.refactoring_ex.ch1_first_ex;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatementData {

    private String customer;
    private List<Perf> performances;

}
