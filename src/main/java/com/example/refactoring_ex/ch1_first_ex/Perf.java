package com.example.refactoring_ex.ch1_first_ex;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
public class Perf {
    private String playID; //play.getName()
    private Integer audience;
}
