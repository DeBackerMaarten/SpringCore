package be.vdab.taken;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;

@Component

public class MyRunner implements CommandLineRunner {
    @Override
    public void run (String... args) {
        System.out.println("Welkom in de school");
    }
}
