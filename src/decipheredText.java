import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;


public class decipheredText {
    private String textToDecipher;
    private String decipheredText;
    private final int upperCaseBeginning = 1040;
    private final int lowerCaseBeginning = 1072; //граница между заглавными и строчными буквами. 1072 - начало строчных букв

    public String getDecipheredText() {
        return decipheredText;
    }

    public decipheredText(String pathTextToDecipher) throws IOException {
        Path textToDecipherPath = Path.of(pathTextToDecipher);
        textToDecipher = Files.readString(textToDecipherPath);

        Path decipheredTextPath = Path.of("G:\\учеба\\Защита информации\\laba_1\\decipheredText.txt");
        if (Files.notExists(decipheredTextPath)) {
            //Files.createFile(decipheredTextPath);
        }
        Files.copy(textToDecipherPath, decipheredTextPath, StandardCopyOption.REPLACE_EXISTING);
        decipheredText = Files.readString(decipheredTextPath);
    }

    public void decipherText(freqTable sourceTextTable, freqTable cipheredTextTable) {

        //Копируем таблицы частот
        HashMap<String, Integer> sourcePositionTable = createPositionsTable(sourceTextTable.getFreqTable());
        HashMap<String, Integer> targetPositionTable = createPositionsTable(cipheredTextTable.getFreqTable());
        String resultText = "";

        char currentChar;
        for (int i = 0; i < decipheredText.length(); i++) {
            currentChar = decipheredText.charAt(i);
            if (charIsValid(currentChar)) {
                String key;
                //key = currentChar >= caseBorder ? getKey((int) currentChar - caseBorder) : getKey((int) currentChar - upperCaseBeginning); //вычисление буквы, которую нужно будет заменить
                resultText += findLetterUsingPosition(currentChar, sourcePositionTable, targetPositionTable);
            }
            else resultText += currentChar;
        }
        decipheredText = resultText;
        //todo добавить перенос расшифрованного текста в файл decipheredText
    }

    private String getKey(int offset) {
        String key;

        key = "";
        key += (char) ('А' + offset);
        key += (char) ('а' + offset);

        return key;
    }

    private boolean charIsValid(char Char) {
        if (Char >= 1040 && Char <= 1103) return true;
        else return false;
    }
    /*
    Метод увеличивает значение counter до тех пор, пока частота переданной буквы (key) не будет максимальной.
    Это необходимо, чтобы сопоставить порядок частот исходного текста (по которому расшифровываем) порядку частот зашифрованного текста.
    Когда counter подсчитался, нужно вернуть значение getKey(counter), которое и будет буквой, которая текущую букву (key)
    */
   /* private String maxFreqLetter(HashMap<String, Double> sourceFeqTable, HashMap<String, Double> targetFreqTable, String key) {
        Double frequency = 0.0;
        int counter = 0;

        for (int i = 0; getKey(i) != key; i++) {
            if (frequency < sourceFeqTable.get(getKey(i))) {
                frequency = sourceFeqTable.get(getKey(i));
                sourceFeqTable.remove(getKey(i));
            }
            counter++;
        }

        return getKey();
    }*/

    private HashMap<String, Integer> createPositionsTable(HashMap<String, Double> freqTable) {
        HashMap<String, Double> copy = new HashMap<>(freqTable);
        HashMap<String, Integer> positionsTable = new HashMap<>();
        Double frequency;
        int counter = 0;
        int position;

        for (position = 0; position < freqTable.size(); position++) {
            frequency = 0.0;
            for (int i = 0; i < freqTable.size(); i++) {
                if (copy.containsKey(getKey(i))) {
                    if (frequency < copy.get(getKey(i))) {
                        frequency = copy.get(getKey(i));
                        counter = i;
                    }
                }
            }
            positionsTable.put(getKey(counter), position);
            copy.remove(getKey(counter));
        }

        return positionsTable;
    }

    private char findLetterUsingPosition (char currentChar, HashMap<String, Integer> sourcePositionTable, HashMap<String, Integer> targetPositionTable) {
        String key = currentChar >= lowerCaseBeginning ? getKey((int) currentChar - lowerCaseBeginning) : getKey((int) currentChar - upperCaseBeginning);
        int targetPosition = targetPositionTable.get(key);
        int sourcePosition;
        char letter = 'А';

        for (int i = 0; i < sourcePositionTable.size(); i++) {
            sourcePosition = sourcePositionTable.get(getKey(i));
            if (sourcePosition == targetPosition) {
                if (currentChar >= lowerCaseBeginning) letter = getKey(i).charAt(1);
                else letter = getKey(i).charAt(0);
                break;
            }
        }

        return letter;
    }
}