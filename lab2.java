import java.util.*;

public class lab2 {
    public static void main(String[] args){
        String text = text_generator(); // Створення тексту
        System.out.printf("\n\nThis is the text:\n" + text);
        char[] letters = letters_generator(); // Створення літер для подальшого видалення підрядків
        System.out.printf("\n\nThis is the letters:\n" + letters[0] + " and " + letters[1] + "\n");
        List<String> sentences = sentence_separator(text); // Виокремлення речень з тексту
        String result = string_fixer(letters[0], letters[1], sentences); // Видалення з кожного речення найбільшого підрядка, що починається та закінчується заданими літерами
        System.out.println(result);
    }

    public static String text_generator(){
        boolean isDone = false;
        Scanner scan = new Scanner(System.in);
        String answer;
        while (isDone == false){
            System.out.printf("\n\nUse default text (yes/no)?\n");
            answer = scan.nextLine();
            if (answer.equals("yes")){
                String text = "This is a test text. This text was written to test this code. This is a short text.";
                return text;
            }
            else if (answer.equals("no")){
                isDone = true;
            }
            else{
                System.out.printf("\n\nInvalid answer. Please, use only \'yes\' or \'no\'\n\n");
            }
        }
        System.out.printf("\n\nWrite your text here:\n");
        String text = scan.nextLine();
        return text;

    }

    public static char[] letters_generator(){
        boolean isDone = false;
        Scanner scan = new Scanner(System.in);
        String answer;
        while (isDone == false){
            System.out.printf("\n\nUse default letters (yes/no)?\n");
            answer = scan.nextLine();
            if (answer.equals("yes")){
                char[] letters = new char[] {'i', 'x'};
                return letters;
            }
            else if (answer.equals("no")){
                isDone = true;
            }
            else{
                System.out.printf("\n\nInvalid answer. Please, use only \'yes\' or \'no\'\n\n");
            }
        }
        isDone = false;
        char letter1 = 'a';
        char letter2 = 'b';
        while (isDone == false){
            try {
                System.out.printf("\n\nWrite your first letter:\n");
                String input = scan.nextLine();
                if (input.length() != 1) {
                    System.out.println("Remember: ONLY 1 letter is allowed");
                }
                else{
                    letter1 = input.charAt(0);
                    isDone = true;
                }
            }
            catch(Exception ex){
                System.out.println("Wrong input");
                System.out.println("Remember: ONLY 1 letter is allowed");
                System.out.println(ex);
                scan.next();
            }          
        }
        isDone = false;
        while (isDone == false){
            try {
                System.out.printf("\n\nWrite your second letter:\n");
                String input = scan.nextLine();
                if (input.length() != 1) {
                    System.out.println("Remember: ONLY 1 letter is allowed");
                }
                else{
                    letter2 = input.charAt(0);
                    isDone = true;
                }
            }
            catch(Exception ex){
                System.out.println("Wrong input");
                System.out.println("Remember: ONLY 1 letter is allowed");
                System.out.println(ex);
                scan.next();
            }          
        }
        return new char[] {letter1, letter2};

    }

    public static List<String> sentence_separator(String text){
        List<String> sentences = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < text.length(); i++){
            if (text.charAt(i) == '.' || text.charAt(i) == '!' || text.charAt(i) == '?' || i == text.length()-1){
                sentences.add(text.substring(start, i+1));
                if (i+1 < text.length()){
                    start = i + 1;
                }
            }
        }
        System.out.println(sentences);
        return sentences;
    }

    public static String string_fixer(char a, char b, List<String> sentences){
        String result = "";
        for (String sentence : sentences){
            result = result.concat(delete_substring(a, b, sentence));
        }
        return result;
    }

    public static String delete_substring(char a, char b, String text){
        int start = -1;
        int end = -1;
        for (int i = 0; i < text.length(); i++){
            if (Character.toLowerCase(text.charAt(i)) == Character.toLowerCase(a)){
                start = i;
                break;
            }
        }
        if (start == -1){
            return text;
        }
        
        for (int i = text.length()-1; i >= 0; i--){
            if (Character.toLowerCase(text.charAt(i)) == Character.toLowerCase(b)){
                end = i;
                break;
            }
        }
        if (end == -1 || end < start){
            return text;
        }

        return text.substring(0, start).concat(text.substring(end+1, text.length()));
    }
}
