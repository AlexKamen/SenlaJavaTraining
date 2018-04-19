import java.util.Scanner;
 
public class Main {
    public static void main(String args[]) {
    	System.out.println("Enter words by space (for example 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua')");
        Scanner scan = new Scanner(System.in);
        String wordsStr = scan.nextLine();
        scan.close();

        String[] wordsArr = Parser.parse(wordsStr);
        String resultRow = "";
        for (int i = 0; i < wordsArr.length; i++) {
        	Word word = new Word(wordsArr[i]);
        	resultRow += word.getNewWord()+" ";
        }

        System.out.println(resultRow);
    }
}