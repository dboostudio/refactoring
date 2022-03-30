package com.example.refactoring_ex.ch1_first_ex;

import org.springframework.stereotype.Component;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Component
public class OutsourcingCompany {

    public String statement(Invoice invoice, List<Play> plays) throws Exception{
        Locale locale = new Locale("en", "US");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();


        Integer totalAmount = 0;
        Integer volumeCredits = 0;

        String result = "청구내역(고객명: "+invoice.getCustomer()+")\n";

        for(Perf perf : invoice.getPerformances()){


            Optional<Play> optionalPlay = plays.stream().filter(p -> p.getName().equals(perf.getPlayID())).findFirst();
            Play play = optionalPlay.orElseThrow(() -> new Exception("playID에 해당하는 perf가 존재하지 않습니다."));

            Integer thisAmount = amountFor(perf, play);

            volumeCredits += Math.max(perf.getAudience() - 30, 0);
            if(play.getType() == "comedy")
                volumeCredits += (int) Math.floor(perf.getAudience() / 5);

            result += " - " + play.getName() + ": " + numberFormat.format(thisAmount/100) + " " + perf.getAudience() + "석\n";
            totalAmount += thisAmount;
        }

        result += "총액 : " + numberFormat.format(totalAmount/100) + "\n";
        result += "적립 포인트 : " + volumeCredits + "점\n";

        return result;

    }

    private Integer amountFor(Perf perf, Play play) throws Exception {
        Integer result = 0;
        switch (play.getType()){
            case "tragedy":
                result = 40000;
                if(perf.getAudience() > 30)
                    result += 1000 * (perf.getAudience() - 30);
                break;
            case "comedy":
                result = 30000;
                if(perf.getAudience() > 20)
                    result += 10000 + 500 * (perf.getAudience() - 20);
                result += 300 * perf.getAudience();
                break;
            default:
                throw new Exception("알 수 없는 type의 play입니다. : " + play.getType());
        }
        return result;
    }
}
