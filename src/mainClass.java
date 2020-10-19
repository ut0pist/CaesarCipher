import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class mainClass {
    public static void main(String[] args) throws IOException {
        //TODO: поля для теста
        Path draftPath = Path.of("G:\\учеба\\Защита информации\\draft.txt");
        //

        Path path = Path.of("G:\\учеба\\Защита информации\\randomChapter.txt");
        String randomChapterText = Files.readString(path);

        cipheredText obj = new cipheredText();
        obj.cipherText(randomChapterText, 2);

        Path cipheredRandomChapter = Path.of("G:\\учеба\\Защита информации\\cipheredRandomChapter.txt");
        if (!Files.exists(cipheredRandomChapter)) {
            Files.createFile(cipheredRandomChapter);
            obj.putIntoFile(cipheredRandomChapter);
        }
        freqTable randomChapterTable = new freqTable(Files.readString(cipheredRandomChapter));
        randomChapterTable.dispayFreqTable();
        System.out.println();
        System.out.println();


        Path warAndPeacePath = Path.of("G:\\учеба\\Защита информации\\WarAndPeace.txt");
        String warAndPeaceText = Files.readString(warAndPeacePath);
        freqTable warAndPeaceTable = new freqTable(warAndPeaceText);
        warAndPeaceTable.dispayFreqTable();

        System.out.println("Common freq : " + warAndPeaceTable.commonFreq());
    }
}
