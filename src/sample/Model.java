package sample;

/**
 * @author Valentyn Kofanov (https://github.com/avmepy)
 */
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;


public class Model {


    public IOResult<TextFile> open(Path file) {
        try {
            List<String> lines = Files.readAllLines(file);
            return new IOResult<>(true, new TextFile(file, lines));
        } catch (IOException e) {
            e.printStackTrace();
            return new IOResult<>(false, null);
        }
    }

    public void save(TextFile textFile) {
        try {
            Files.write(textFile.getFile(), textFile.getContent(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        System.exit(0);
    }

    public void search() {
        System.exit(0);
    }
}