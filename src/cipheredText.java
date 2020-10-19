import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class cipheredText {
    private String cipheredText = new String();

    public String getCipheredText() {
        return cipheredText;
    }

    public void cipherText(String text, int offset) {
        int leftBorder = 1040, rightBorder = 1103;
        int endOfUpperCase = 1071, endOfLowerCase = 1103;
        char tempChar;


            for(int i = 0; i < text.length(); i++) {
                tempChar = text.charAt(i);

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
            cipheredText += tempChar;
        }
    }

    public void putIntoFile(Path path) throws IOException {
            Files.writeString(path, cipheredText, StandardOpenOption.APPEND);
    }
}
