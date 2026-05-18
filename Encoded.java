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
//generate a shift value based on the group ID
    public int generateShift() {
        int hash = Math.abs(groupID.hashCode());
        int shift = (hash % 10) + 1;  
        return shift; 
    }

//calculate the final shift value by adding the character count to the generated shift
    public int getFinalShift() {
        int finalShift = generateShift() + this.charCount;
        return finalShift; 
    }

    public String applyCipher() {
    
    int finalShift = getFinalShift();
    StringBuilder result = new StringBuilder();

    for (int i = 0; i < this.inputText.length(); i++) {        
        char c = this.inputText.charAt(i); 
        
        if (Character.isLowerCase(c)) {
            result.append((char) ((c - 'a' + finalShift) % 26 + 'a'));
        } else if (Character.isDigit(c)) {
            result.append((char) ((c - '0' + finalShift) % 10 + '0'));
        } else {
            result.append(c);
        }
    }
    
    this.resultText = result.toString();
    return this.resultText;
    }
}