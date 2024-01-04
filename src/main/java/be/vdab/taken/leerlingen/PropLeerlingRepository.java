package be.vdab.taken.leerlingen;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component
@Primary
public class PropLeerlingRepository implements LeerlingRepository{
    private final String pad;
    public PropLeerlingRepository(@Value("${leerlingenPropPad}") String pad) {
        this.pad = pad;
    }
    public List<Leerling> findAll() {
        try (var stream = Files.lines(Path.of(pad))) {
            return stream
                    .map(regel -> regel.split("[:,]"))
                    .map(onderdelen ->
                            new Leerling (Long.parseLong(onderdelen[0]),
                                    onderdelen[1],onderdelen[2]) )
                    .toList();
        }catch (IOException | ArrayIndexOutOfBoundsException | NumberFormatException ex) {
            throw new IllegalArgumentException("leerlingenbestand bevat verkeerde data", ex);
        }
    }
}
