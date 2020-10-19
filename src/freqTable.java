import java.util.HashMap;

public class freqTable {
    private HashMap<String, Double> freqTable = new HashMap<>();

    public HashMap<String, Double> getFreqTable() {
        return freqTable;
    }

    public freqTable(String text) {
        createFreqTable(text);
    }

    private void createFreqTable(String text) {
        int counter;
        int amountOfRussianLetters = 0;
        int alphabetIndex;
        Double value;

        char charVar;
        String[] alphabet = new String[32];
        for (int i = 0; i < 32; i++) {
            alphabet[i] = "";
            alphabet[i] += (char) ('А' + i);
            alphabet[i] += (char) ('а' + i);
        }

        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) >= 1040 && text.charAt(i) <= 1103) amountOfRussianLetters++;
        }


        for (alphabetIndex = 0; alphabetIndex < alphabet.length; alphabetIndex++) {
            counter = 0;

            for (int j = 0; j < text.length(); j++) {
                charVar = text.charAt(j);
                if (charVar >= 1040 && charVar <= 1103) {
                    if (alphabet[alphabetIndex].charAt(0) == charVar || alphabet[alphabetIndex].charAt(1) == charVar)
                        counter++;
                }
            }

            value = (double) counter / amountOfRussianLetters;
            freqTable.put(alphabet[alphabetIndex], value);
        }
    }

    public void dispayFreqTable() {
        String keyToDisplay;
        for (int i = 0; i < freqTable.size(); i++) {
            keyToDisplay = "";
            keyToDisplay += (char) ('А' + i);
            keyToDisplay += (char) ('а' + i);
            System.out.print( keyToDisplay + " : ");
            System.out.println(freqTable.get(keyToDisplay));
        }
    }

    public double commonFreq() {
        String key;
        double res = 0;
        for (int i = 0; i < freqTable.size(); i++) {
            key = "";
            key += (char) ('А' + i);
            key += (char) ('а' + i);
            res += freqTable.get(key);
        }
        return res;
    }
}
