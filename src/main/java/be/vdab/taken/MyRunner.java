package be.vdab.taken;

import be.vdab.taken.leerlingen.LeerlingRepository;
import be.vdab.taken.lessen.LesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;

@Component

public class MyRunner implements CommandLineRunner {
    private final LeerlingRepository leerlingRepository;
    private final LesRepository lesRepository;
    public MyRunner (LeerlingRepository leerlingRepository, LesRepository lesRepository) {
        this.leerlingRepository = leerlingRepository;
        this.lesRepository = lesRepository;
    }
    @Override
    public void run (String... args) {
        try {
            leerlingRepository.findAll()
                    .forEach(leerling -> System.out.println(leerling.getVoornaam()));
            lesRepository.findAll()
                    .forEach(les -> System.out.println(les.getNaam()));
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace(System.err);
        }
    }
}
