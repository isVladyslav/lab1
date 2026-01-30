import java.util.*;

public class lab4 {
    public static void main(String[] args){
        String text = text_generator(); // Створення тексту
        System.out.printf("\n\nThis is the text:\n" + text);
        char[] letters = letters_generator(); // Створення літер для подальшого видалення підрядків
        System.out.printf("\n\nThis is the letters:\n" + letters[0] + " and " + letters[1] + "\n");
        TextClass result_text = sentence_separator(text); // Виокремлення речень з тексту
        TextClass full_result = string_fixer(letters[0], letters[1], result_text); // Видалення з кожного речення найбільшого підрядка, що починається та закінчується заданими літерами
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

    public static TextClass sentence_separator(String text){
        List<Object> characters = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < text.length(); i++){
            char character = text.charAt(i);
            if (text.charAt(i) == '.' || text.charAt(i) == '!' || text.charAt(i) == '?' || text.charAt(i) == ',' || text.charAt(i) == ';'){
                PunctuationClass punct = new PunctuationClass(text.charAt(i));
                characters.add(punct);
            }
            else{
                LettersClass letter = new LettersClass(text.charAt(i));
                characters.add(letter);
            }
        }
        
        List<SentenceClass> text_arr = new ArrayList<>();
        List<Object> sentences = new ArrayList<>();
        List<LettersClass> word = new ArrayList<>();
        for (int i = 0; i < text.length(); i++){
            if (characters.get(i) instanceof LettersClass){
                if (((LettersClass) characters.get(i)).letter == ' '){
                    sentences.add(word);
                    word.clear();
                }
                else{
                    word.add((LettersClass) characters.get(i));
                }
            }
            else{
                if (((PunctuationClass) characters.get(i)).punctuation == '.' || ((PunctuationClass) characters.get(i)).punctuation == '!' || ((PunctuationClass) characters.get(i)).punctuation == '?' || i == text.length()-1){
                    SentenceClass sent = new SentenceClass(sentences);
                    text_arr.add(sent);
                    sentences.clear();
                }
                else{
                sentences.add(word);
                sentences.add(characters.get(i));   
                word.clear();                
                }
            }
        }
        TextClass result_text = new TextClass(text_arr);
        return result_text;
    }

    public static TextClass string_fixer(char a, char b, TextClass sentences){
        List<SentenceClass> result = new ArrayList<>();
        for (int i = 0; i < sentences.text.size(); i++){
            result.add(delete_substring(a, b, (SentenceClass) sentences.text.get(i)));
        }
        TextClass full_text = new TextClass(result);
        return full_text;
    }

    public static SentenceClass delete_substring(char a, char b, SentenceClass text){
        int start_word = -1;
        int start_letter = -1;
        int end_word = -1;
        int end_letter = -1;
        for (int i = 0; i < text.sentence.size(); i++){
            if(start_letter != -1){
                break;
            }
            else if (text.sentence.get(i) instanceof WordClass){
                for (int j = 0; j < ((WordClass) text.sentence.get(i)).letters.size(); j++){
                    if (Character.toLowerCase(((WordClass) text.sentence.get(i)).letters.get(j).letter) == Character.toLowerCase(a)){
                        start_word = i;
                        start_letter = j;
                        break;
                    }
                }
            }
        }
        if (start_word == -1){
            return text;
        }

        for (int i = text.sentence.size()-1; i >= 0; i--){
            if(end_letter != -1){
                break;
            }
            else if (text.sentence.get(i) instanceof WordClass){
                for (int j = ((WordClass) text.sentence.get(i)).letters.size(); j >= 0; j--){
                    if (Character.toLowerCase(((WordClass) text.sentence.get(i)).letters.get(j).letter) == Character.toLowerCase(b)){
                        end_word = i;
                        end_letter = j;
                        break;
                    }
                }
            }
        }
        if (end_word == -1 || end_letter < start_letter){
            return text;
        }

        for (int i = start_word + 1; i < end_word; i++){
            text.sentence.remove(i);
        }

        for (int j = start_letter; j < ((WordClass) text.sentence.get(start_word)).letters.size(); j++){
            ((WordClass) text.sentence.get(start_word)).letters.remove(j);
        }

        for (int j = end_letter; j >= 0; j--){
            ((WordClass) text.sentence.get(end_word)).letters.remove(j);
        }
        
        return text;
    }
}

class LettersClass{
    char letter;
    
    LettersClass(char letter){
        this.letter = letter;
    }
}

class PunctuationClass{
    char punctuation;

    PunctuationClass(char punctuation){
        this.punctuation = punctuation;
    }
}

class WordClass{
    List<LettersClass> letters  = new ArrayList<>();

    WordClass(List<LettersClass> letters){
        for (LettersClass letter : letters){
            this.letters.add(letter);
        }
    }
}

class SentenceClass{
    List<Object> sentence  = new ArrayList<>();

    SentenceClass(List<Object> elements){
        for (Object element : elements){
            this.sentence.add(element);
        }
    }
}

class TextClass{
    List<SentenceClass> text = new ArrayList<>();

    TextClass(List<SentenceClass> sentences){
        for (SentenceClass sentence : sentences){
            this.text.add(sentence);
        }
    }
}
