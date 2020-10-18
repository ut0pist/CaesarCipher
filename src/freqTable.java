import java.util.HashMap;

public class freqTable {
    private HashMap<Character, Double> freqTable = new HashMap<>();

    public freqTable(String text) {
        //init();
        createFreqTable(text);
    }

    /*private void init() {
        freqTable = new HashMap<>();
        for(int i = 0; i < 32; i++) {
            freqTable.put((char)('А' + i), 0);
            freqTable.put((char)('а' + i), 0);
        }
    }*/

    private void createFreqTable(String text) {
        int counter;
        int alphabetIndex;
        char charVar;
        char[] alphabet = new char[64];
        for (int i = 0; i < 64; i++) alphabet[i] = (char) ('А' + i);

        for (alphabetIndex = 1092 - 1040; alphabetIndex < alphabet.length; alphabetIndex++) {
            counter = 0;

            for (int j = 0; j < 15; j++) {
                charVar = text.charAt(j);
                if (charVar >= 1040 && charVar <= 1103) {
                    if (alphabet[alphabetIndex] == charVar) counter++;
                }
            }

            freqTable.put(alphabet[alphabetIndex], (double) (counter / text.length()));
        }
    }
}
