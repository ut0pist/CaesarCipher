import java.util.Iterator;
import java.util.List;

public class cipheredText {
    private List<String> cipheredText;

    public List<String> cipherText(List<String> text, int offset) {
        cipheredText = text;
        //Iterator<String> iterator = cipheredText.iterator();

        String tempStr = "";
        int leftBorder = 1040, rightBorder = 1103;
        int endOfUpperCase = 1071, endOfLowerCase = 1103;
        char tempChar;


        for(String txt : cipheredText) {
            for(int i = 0; i < txt.length(); i++) {
                tempChar = txt.charAt(i);

                if(tempChar >= leftBorder && tempChar <= rightBorder) {
                    if(tempChar >= leftBorder && tempChar <= endOfUpperCase) {
                        if(tempChar + offset > endOfUpperCase) tempChar = (char) (leftBorder + (endOfUpperCase - offset));
                    }
                    else {
                        if(tempChar + offset > rightBorder) tempChar = (char) ((endOfLowerCase + 1) + (rightBorder - offset));
                    }
                }
                tempStr += tempChar;
            }

        }

        return cipheredText;
    }
}
