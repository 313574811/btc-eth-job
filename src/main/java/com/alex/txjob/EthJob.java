package com.alex.txjob;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class EthJob {

    @Value("${call.cron}")
    private String ethCron;

//    @Scheduled(fixedDelay = 1000)
    private void ethJob(){
        System.out.println(ethCron);
    }

    public static void main(String[] args) {

    }
}
