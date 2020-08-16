package com.alex.txjob;

import com.alex.txjob.context.ContextInitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TxjobApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(TxjobApplication.class);
        app.addListeners(new ContextInitListener());
        app.run(args);
    }

}
