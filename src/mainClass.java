import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class mainClass {
    public static void main(String[] args) throws IOException {
        Path path = Path.of("G:\\учеба\\Защита информации\\randomChapter.txt");
        List<String> randomChapter = Files.readAllLines(path);

        cipheredText obj = new cipheredText();
        obj.cipherText(randomChapter, 2);

        Path cipheredRandomChapter = Path.of("G:\\учеба\\Защита информации\\cipheredRandomChapter.txt");
        if(!Files.exists(cipheredRandomChapter)) {
            Files.createFile(cipheredRandomChapter);
            obj.putIntoFile(cipheredRandomChapter);
        }


        freqTable table = new freqTable(Files.readString(cipheredRandomChapter));
        System.out.println();
    }
}
