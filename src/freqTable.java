import java.util.HashMap;

public class freqTable {
    private HashMap<Character, Integer> freqTable = new HashMap<>();

    public freqTable(String text){
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
        char tempChar;

        
    }
}
