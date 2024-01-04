package be.vdab.taken.leerlingen;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component
public class CsvLeerlingRepository implements LeerlingRepository {
    private final String pad;
    public CsvLeerlingRepository(@Value("${leerlingenCsvPad}") String pad) {
        this.pad = pad;
    }
    @Override
    public List<Leerling> findAll() {
        try (var stream = Files.lines(Path.of(pad))) {
            return stream
                    .map(regel -> regel.split(","))          //regel splitsen in zijn onderdelen
                    .map(regelOnderdelen ->
                            new Leerling(Integer.parseInt(regelOnderdelen[0]),
                                    regelOnderdelen[1],
                                    regelOnderdelen[2]))
                    .toList();
        }catch (IOException | ArrayIndexOutOfBoundsException | NumberFormatException ex) {
            throw new IllegalArgumentException("landbestand bevat verkeerde data" , ex);
        }
    }
}
