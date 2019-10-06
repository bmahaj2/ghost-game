import java.util.List;
import java.util.Scanner;

import static javafx.application.Platform.exit;

public class PlayGame {

    GhostTrie ghostTrie;
    boolean gameOver;

    public PlayGame(GhostTrie ghostTrie){
        this.ghostTrie = ghostTrie;
        this.gameOver = false;
    }

    public void startGame(String prefix){
        while(!gameOver) {
            prefix = playerOneMove(prefix);
            prefix = computerMove(prefix);
        }
    }

    public String playerOneMove(String prefix){

//        if(ghostTrie.isCompleteWord(prefix)) {
//            System.out.println(prefix);
//            System.out.println("Computer formed a word. Player 1 wins");
//        }
        System.out.println("Current word is " + prefix);
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter a character [a-z] or 'challenge' to challenge the computer");
        String playerInput = myObj.nextLine().toLowerCase();

        if(playerInput.equals("challenge")) {
            if(prefix.equals("")) {
                System.out.println("You challenged an empty word. Computer won");
                System.exit(0);
            }
            if(ghostTrie.isCompleteWord(prefix)){
                System.out.println("Computer formed "+prefix +" that already exits in dictonary. Player 1 wins");
                System.exit(0);
                //return "GAME_OVER";
            }
            String prefixWord = "";
            prefixWord = ghostTrie.getFirstPrefixWord(prefix);
            if(prefixWord.equals(""))
//            List<String> prefixList = ghostTrie.getWordsForPrefix(prefix);
//            if(prefixList.size() == 0)
                System.out.println("No words exist that start with "+ prefix +".Player 1 wins");
            else
                System.out.println("Word "+ prefixWord + " exists in the dictionary Computer won");
            System.exit(0);
//            if (challengeOpponent(prefix)) {
//                System.out.println("Computer formed a word. Player 1 wins");
//                System.exit(0);
//                //return "GAME_OVER";
//            }
        }
        else if (playerInput.length() == 1 && playerInput.charAt(0) >= 'a' && playerInput.charAt(0) <= 'z') {
            return prefix + playerInput;
        }

        System.out.println("Invalid input. Try again with correct input");
        return playerOneMove(prefix);
//        return "prefix1";
//        return playerInput;
    }

    public String computerMove(String prefix){
        if(ghostTrie.isCompleteWord(prefix)){
            System.out.println("Player 1 formed the word "+ prefix + " that exists in dictionary. Computer won.");
            System.exit(0);
        }
        String prefixWord = ghostTrie.getFirstPrefixWord(prefix);
        if(prefixWord.equals("")){
//            System.out.println("No word with "+ prefix + " exists");
            challengePlayerOne(prefix);
        }
        String comWord = findBestWordForComputer(prefix, ghostTrie);
        if(comWord.equals("NoMoreWords"))
            System.out.println("Computer admits defeat. Player1 won");
        System.out.println("Word formed by computer is: "+ comWord);
//        return prefix;
        return comWord;
    }


    static String findBestForComputer(String sample, GhostTrie ghostTrie){
        List<String> prefixWords = ghostTrie.getWordsForPrefix(sample);
        if(!prefixWords.isEmpty()) {
            System.out.println("prefix words are:--------------------------");
            int minLength = Integer.MAX_VALUE;
            String chosenWord = "";
            for (String word : prefixWords) {
                int lenDiff = word.length() - sample.length();
                if (lenDiff % 2 == 0) {
                    if (word.length() < minLength) {
                        minLength = word.length();
                        chosenWord = word;
                    }
//                boolean consider = true;
//                for(int i = 1; i<= lenDiff; i++) {
//                    if (i % 2 != 0){
//                        if(ghostTrie.isCompleteWord(word.substring(0, sample.length()+i))) {
////                          System.out.println("desired is :" + word.substring(0, sample.length() + i));
////                          System.out.println("haha");
//                            consider = false;
//                            continue;
//                        }
//                    }
//                }
//                if(consider)
//                    return word;
                }

            }
            if (!chosenWord.equals(""))
                return chosenWord.substring(0, sample.length()+1);

            for (String word : prefixWords) {
                if (!ghostTrie.isCompleteWord(word.substring(0, sample.length() + 1)))
                    return word;
            }
            return prefixWords.get(0);
        }
        return "NoMoreWords";
    }

    public void challengePlayerOne(String prefix){
        Scanner myObj = new Scanner(System.in);
        System.out.println("You have been challenged by computer. Enter correct word starting with prefix "+ prefix);
        String playerInput = myObj.nextLine().toLowerCase();
        if (playerInput.matches("^[a-zA-Z]*$") && playerInput.startsWith(prefix) && ghostTrie.isCompleteWord(playerInput) ){
            System.out.println("Correct word. Player1 won");
        }
        else
            System.out.println("Incorrect word. Computer won");
        System.exit(0);

    }





    static String findBestWordForComputer(String sample, GhostTrie ghostTrie) {
        List<String> prefixWords = ghostTrie.getWordsForPrefix(sample);
        System.out.println("prefix words are:--------------------------");
        if (!prefixWords.isEmpty()) {
            for (String word : prefixWords) {
                int lenDiff = word.length() - sample.length();
                if (lenDiff % 2 == 0) {
                    boolean consider = true;
                    for (int i = 1; i <= lenDiff; i++) {
                        if (i % 2 != 0) {
                            if (ghostTrie.isCompleteWord(word.substring(0, sample.length() + i))) {
//                          System.out.println("desired is :" + word.substring(0, sample.length() + i));
//                          System.out.println("haha");
                                consider = false;
                                break;
                            }
                        }
                    }
                    if (consider)
                        return word.substring(0, sample.length()+1);
                }

            }


            for (String word : prefixWords) {
                if (!ghostTrie.isCompleteWord(word.substring(0, sample.length() +1)))
                    return word.substring(0, sample.length() +1);
            }
            return prefixWords.get(0);
        }
        return "NoMoreWords";
    }

}
