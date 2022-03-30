package com.example.refactoring_ex.ch1_first_ex;

import lombok.Setter;
import org.springframework.stereotype.Component;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Component
@Setter
public class OutsourcingCompany {

    private Invoice invoice;
    private List<Play> playList;

    Locale locale = new Locale("en", "US");
    NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);

    public String statement() throws Exception{

        Integer totalAmount = 0;
        Integer volumeCredits = 0;

        String result = "청구내역(고객명: "+invoice.getCustomer()+")\n";

        for(Perf perf : invoice.getPerformances()){

            // 청구 내역을 출력한다.
            result += " - " + forPlay(perf).getName() + ": " + format(amountFor(perf)) + " " + perf.getAudience() + "석\n";
            totalAmount += amountFor(perf);
        }

        for(Perf perf : invoice.getPerformances()) {
            volumeCredits += volumeCreditsFor(perf);
        }

        result += "총액 : " + format(totalAmount) + "\n";
        result += "적립 포인트 : " + volumeCredits + "점\n";

        return result;
    }

    private Integer volumeCreditsFor(Perf perf) throws Exception {
        // 포인트를 적립한다.
        Integer result = Math.max(perf.getAudience() - 30, 0);

        // 희극 관객 5명마다 추가 포인트를 제공한다.
        if (forPlay(perf).getType() == "comedy")
            result += (int) Math.floor(perf.getAudience() / 5);
        return result;
    }

    private String format(Integer number){
        return numberFormat.format(number/100);
    }

    private Play forPlay(Perf perf) throws Exception {
        Optional<Play> optionalPlay = playList.stream().filter(p -> p.getName().equals(perf.getPlayID())).findFirst();
        Play play = optionalPlay.orElseThrow(() -> new Exception("playID에 해당하는 perf가 존재하지 않습니다."));
        return play;
    }

    private Integer amountFor(Perf perf) throws Exception {
        Integer result = 0;
        switch (forPlay(perf).getType()){
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
                throw new Exception("알 수 없는 type의 Play입니다. : " + forPlay(perf).getType());
        }
        return result;
    }
}
