// Contributed by AMREEN MUSTAQEEM BIN ASLAN: Class structure & constructors
// Contributed by Danial Naseem Iman (103768): countCharacters(), checkStringValidity()
// Contributed by SII JONG MING: Testing & documentation

public class Encoded {
    private String inputText;
    private int charCount;
    private String resultText;
    private final String groupID = "G04/SE-G05";

    // Contributed by AMREEN MUSTAQEEM BIN ASLAN: Class structure & constructors
    // Default constructor
    public Encoded() {
        this.inputText = "";
        this.charCount = 0;
        this.resultText = "";
    }

    // Contributed by AMREEN MUSTAQEEM BIN ASLAN: Class structure & constructors
    public Encoded(String inputText) {
        this.inputText = inputText;
        this.charCount = countCharacters();
        this.resultText = "";
    }

    // =========================================================
    // GETTERS & SETTERS
    // Contributed by AMREEN MUSTAQEEM BIN ASLAN: Class structure & constructors
    // =========================================================

    public String getInputText() { return inputText; }
    public void setInputText(String inputText) { this.inputText = inputText; }

    public int getCharCount() { return charCount; }
    public void setCharCount(int charCount) { this.charCount = charCount; }

    public String getResultText() { return resultText; }
    public void setResultText(String resultText) { this.resultText = resultText; }

    public String getGroupID() { return groupID; }

    // =========================================================
    // =========================================================

    // Contributed by Danial Naseem Iman (103768): countCharacters(), checkStringValidity()
    public int countCharacters() {
        int count = 0;
        if (this.inputText == null) {
            return 0;
        } else {
            for (int i = 0; i < this.inputText.length(); i++) {
                if (this.inputText.charAt(i) != ' ') {
                    count++;
                }
            }
            this.charCount = count;
            return count;
        }
    }

    public boolean checkStringValidity() {
        if (this.inputText == null || this.inputText.isEmpty()) {
            return false;
        }
        for (int i = 0; i < this.inputText.length(); i++) {
            char c = this.inputText.charAt(i);
            if (!Character.isLowerCase(c) && !Character.isDigit(c) && c != ' ') {
                return false;
            }
        }
        return true;
    }

    // Contributed by BARACHELLE ELEAZAR ANAK GANI (103511): Cipher algorithm & shift logic
    public int generateShift() {
        int rawHash = groupID.hashCode();
        int positiveHash = Math.abs(rawHash);
        return (positiveHash % 10) + 1;
    }

    public int getFinalShift() {
        return generateShift() + this.charCount;
    }

    public String applyCipher() {
        int shiftValue = getFinalShift();
        StringBuilder encrypted = new StringBuilder();

        for (char currentChar : inputText.toCharArray()) {
            if (Character.isLowerCase(currentChar)) {
                char shifted = (char) ((currentChar - 'a' + shiftValue) % 26 + 'a');
                encrypted.append(shifted);
            } else if (Character.isDigit(currentChar)) {
                char shifted = (char) ((currentChar - '0' + shiftValue) % 10 + '0');
                encrypted.append(shifted);
            } else {
                encrypted.append(currentChar);
            }
        }

        this.resultText = encrypted.toString();
        return this.resultText;
    }
}
