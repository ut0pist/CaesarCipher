import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class mainClass {
    public static void main(String[] args) throws IOException {
        //TODO: поля для теста
        Path draftPath = Path.of("G:\\учеба\\Защита информации\\laba_1\\draft.txt");
        //

        Path path = Path.of("G:\\учеба\\Защита информации\\laba_1\\randomChapter.txt");
        String randomChapterText = Files.readString(path);

        cipheredText obj = new cipheredText();
        obj.cipherText(randomChapterText, 2);

        Path cipheredRandomChapter = Path.of("G:\\учеба\\Защита информации\\laba_1\\cipheredRandomChapter.txt");
        if (!Files.exists(cipheredRandomChapter)) {
            Files.createFile(cipheredRandomChapter);
        }
        else {
            Files.delete(cipheredRandomChapter);
            Files.createFile(cipheredRandomChapter);
            obj.putIntoFile(cipheredRandomChapter);
        }


        freqTable randomChapterTable = new freqTable(Files.readString(cipheredRandomChapter));
        System.out.println("Random chapter frequency table");
        randomChapterTable.dispayFreqTable();
        System.out.println("Common freq : " + randomChapterTable.commonFreq());
        System.out.println();
        System.out.println();


        Path warAndPeacePath = Path.of("G:\\учеба\\Защита информации\\laba_1\\WarAndPeace.txt");
        String warAndPeaceText = Files.readString(warAndPeacePath);
        freqTable warAndPeaceTable = new freqTable(warAndPeaceText);
        System.out.println("The whole literary work");
        warAndPeaceTable.dispayFreqTable();

        System.out.println("Common freq : " + warAndPeaceTable.commonFreq());

        String textToDecipherPath = "G:\\учеба\\Защита информации\\laba_1\\cipheredRandomChapter.txt";
        decipheredText textToDecipher = new decipheredText(textToDecipherPath);
        textToDecipher.decipherText(warAndPeaceTable, randomChapterTable);
        System.out.println(textToDecipher.getDecipheredText());
    }
}
