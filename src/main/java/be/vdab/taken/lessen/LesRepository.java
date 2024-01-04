package be.vdab.taken.lessen;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component

public class LesRepository {
    public List<Les> findAll() {
        try (var stream = Files.lines(Path.of("C:/data/lessen.csv"))) {
            return stream
                    .map(regel-> regel.split(","))
                    .map(regelOnderdelen ->
                            new Les(regelOnderdelen[0],
                                    regelOnderdelen[1]))
                    .toList();
        }catch (IOException | ArrayIndexOutOfBoundsException | NumberFormatException ex) {
            throw new IllegalArgumentException("Lessenbestand bevat verkeerde data" , ex);
        }
    }
}
