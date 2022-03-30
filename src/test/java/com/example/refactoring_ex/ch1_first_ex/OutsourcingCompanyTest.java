package com.example.refactoring_ex.ch1_first_ex;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class OutsourcingCompanyTest {

    @Autowired
    OutsourcingCompany company;

    @Test
    public void doStatement() throws Exception {

        List<Play> playList = new ArrayList<>();
        Play hamletPlay = Play.builder().name("hamlet").type("tragedy").build();
        Play asLikePlay = Play.builder().name("As You Like It").type("comedy").build();
        Play othelloPlay = Play.builder().name("Othello").type("tragedy").build();

        playList.add(hamletPlay);
        playList.add(asLikePlay);
        playList.add(othelloPlay);

        List<Perf> perfList = new ArrayList<>();
        Perf hamletPerf = Perf.builder().playID("hamlet").audience(55).build();
        Perf asLikePerf = Perf.builder().playID("As You Like It").audience(35).build();
        Perf othelloPerf = Perf.builder().playID("Othello").audience(40).build();
        perfList.add(hamletPerf);
        perfList.add(asLikePerf);
        perfList.add(othelloPerf);

        Invoice invoice = Invoice.builder()
                .customer("BigCo")
                .performances(perfList)
                .build();

        String result = company.statement(invoice, playList);

        System.out.println(result);
    }

}