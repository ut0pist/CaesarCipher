import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class cipheredText {
    private List<String> cipheredText = new ArrayList<>();

    public List<String> getCipheredText() {
        return cipheredText;
    }

    public void cipherText(List<String> text, int offset) {
        String tempStr;
        int leftBorder = 1040, rightBorder = 1103;
        int endOfUpperCase = 1071, endOfLowerCase = 1103;
        char tempChar;

        for(String txt : text) {
            tempStr = "";
            for(int i = 0; i < txt.length(); i++) {
                tempChar = txt.charAt(i);

                if(tempChar >= leftBorder && tempChar <= rightBorder) {
                    if(tempChar >= leftBorder && tempChar <= endOfUpperCase) {
                        if(tempChar + offset > endOfUpperCase) tempChar = (char) (leftBorder + (offset - (endOfUpperCase - tempChar)));
                        else tempChar += offset;
                    }
                    else {
                        if(tempChar + offset > rightBorder) tempChar = (char) (endOfUpperCase  + (offset - (rightBorder - tempChar)));
                        else tempChar += offset;
                    }
                }

                tempStr += tempChar;
            }
            cipheredText.add(tempStr);
        }
    }

    public void putIntoFile(Path path) throws IOException {
        for(String str : cipheredText) {
            Files.writeString(path, str, StandardOpenOption.APPEND);
        }
    }
}
