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
            System.out.println("prefix from player1 is "+ prefix);
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
        System.out.println("Enter a character [a-z] or 'challange' to challange the computer");
        String playerInput = myObj.nextLine().toLowerCase();

        if(playerInput.equals("challange")) {
            if(ghostTrie.isCompleteWord(prefix)){
                System.out.println("Computer formed "+prefix +" that already exits in dictonary. Player 1 wins");
                System.exit(0);
                //return "GAME_OVER";
            }
            List<String> prefixList = ghostTrie.getWordsForPrefix(prefix);
            if(prefixList.size() == 0)
                System.out.println("No words exist that start with "+ prefix +".Player 1 wins");
            else
                System.out.println("Word "+ prefixList.get(0) + " exists in the dictionary Computer won");
            System.exit(0);
//            if (challangeOpponent(prefix)) {
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
        return prefix;
    }

    public boolean challangeOpponent(String prefix){
        List<String> prefixList = ghostTrie.getWordsForPrefix(prefix);
        if(prefixList.isEmpty())
                return true;
        return false;
//        if(ghostTrie.getWordsForPrefix(prefix)) {
//            return true;
//        }
//        return false;
    }
}
