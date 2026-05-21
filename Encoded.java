//Contributed by Danial Naseem Iman (103768): countCharacters(), checkStringValidity()
public class Encoded {
    private String inputText;
    private int charCount;
    private String resultText;
    private final String groupID ="G04/SE-G05"; 

    //default constructor
    public Encoded() {
        this.inputText = "";
        this.charCount = 0;
        this.resultText = "";
    }
   
    public Encoded(String inputText) {
        this.inputText = inputText;
        this.charCount = countCharacters(); 
        this.resultText = "";
    }

    //contributed by Danial Naseem Iman (103768
    //count non-space characters in the input string and return the count
    public int countCharacters() {
        int count = 0;
        // if no input, return 0
        if (this.inputText == null) {
            return 0;
        } else {

            for (int i = 0; i < this.inputText.length(); i++) {
                if (this.inputText.charAt(i) != ' ') {  //count non-space character sahaja
                count++;
                }
            }        
            this.charCount = count;
            return count;
        }
    }
    //contributed by Danial Naseem Iman (103768)
    //check if the input string is valid (only lowercase letters, digits and spaces)
    public boolean checkStringValidity() {
        if (this.inputText == null || this.inputText.isEmpty()) {
            return false;
        }

        for (int i = 0; i < this.inputText.length(); i++) {
            char c = this.inputText.charAt(i);
            //Check if its not a lowercase,digit or space.
            if (!Character.isLowerCase(c) && !Character.isDigit(c) && c != ' ') {
                return false;
            }
        }
        return true;
    }

// Contributed by BARACHELLE ELEAZAR ANAK GANI (103511): Cipher algorithm & shift logic

// Generate shift value using group ID hash
    public int generateShift() {
        int rawHash = groupID.hashCode();
        int positiveHash = Math.abs(rawHash);

        // ensure shift is always between 1 and 10
        return (positiveHash % 10) + 1;
    }

    // Combine dynamic character count with base shift
    public int getFinalShift() {
        return generateShift() + this.charCount;
    }

// Core encryption method (handles letters, digits, and preserves other chars)
    public String applyCipher() {
        int shiftValue = getFinalShift();
        StringBuilder encrypted = new StringBuilder();

        for (char currentChar : inputText.toCharArray()) {
            if (Character.isLowerCase(currentChar)) {
                char shifted = (char) ((currentChar - 'a' + shiftValue) % 26 + 'a');
                encrypted.append(shifted);
            } 
            else if (Character.isDigit(currentChar)) {
                char shifted = (char) ((currentChar - '0' + shiftValue) % 10 + '0');
                encrypted.append(shifted);
            } 
            else {
                // keep spaces and symbols unchanged
                encrypted.append(currentChar);
            }
        }

        this.resultText = encrypted.toString();
        return this.resultText;
    }
}
