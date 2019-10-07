import java.util.List;
import java.util.Scanner;

public class PlayGame {

    private static final String NO_MORE_WORDS = "NoMoreWords";
    private static final String INPUT_CHALLENGE = "challenge";
    GhostTrie ghostTrie;

    public PlayGame(GhostTrie ghostTrie){
        this.ghostTrie = ghostTrie;
    }

    /*
     * The game continues until player1 or computer loses and in that case the program terminates.
     * In the below implementation player1 or computer is chosen randomly to make the first move
     */
    public void startGame(String prefix, int choosePlayer){
        if(choosePlayer == 0) {
            while (true) {
                prefix = playerOneMove(prefix);
                prefix = computerMove(prefix);
            }
        }
        else {
            while (true) {
                prefix = computerMove(prefix);
                prefix = playerOneMove(prefix);
            }
        }
    }

    // returns 0 or 1 randomly which decides the starting player
    public  int getRandom(int max){
        int val = (int) (Math.random()*max);
        if (val < 5)
            return 0;
        return 1;
    }

    /*
     * Player1 makes a move. Player should enter a character [a-z] or "challenge" to challenge the computer
     */
    public String playerOneMove(String prefix){
        System.out.println("Current word is " + prefix);
        Scanner myObj = new Scanner(System.in);
        System.out.println("Player 1 enter a character [a-z] or 'challenge' to challenge the computer");
        String playerInput = myObj.nextLine().toLowerCase();

        //Player1 challenges the computer
        if(playerInput.equals(INPUT_CHALLENGE)) {
            if(prefix.equals("")) {
                System.out.println("You challenged an empty word. Computer won");
                System.exit(0);
            }

            /*
             * Player1 challenges the computer and prefix formed by computer is already a complete word.
             * In that case player 1 wins and computer looses.
             */
            if(ghostTrie.isCompleteWord(prefix)){
                System.out.println("Computer formed "+prefix +" that already exits in dictonary. Player 1 wins");
                System.exit(0);
            }
            String prefixWord = "";
            // Get the first complete word that can be formed with the current prefix
            prefixWord = ghostTrie.getFirstPrefixWord(prefix);
            if(prefixWord.equals(""))
                System.out.println("No words exist that start with "+ prefix +".Player 1 wins");
            else
                System.out.println("Word "+ prefixWord + " exists in the dictionary Computer won");
            System.exit(0);
        }


        // Player1 inputs a single character. Append that single character to prefix and return
        else if (playerInput.length() == 1 && playerInput.charAt(0) >= 'a' && playerInput.charAt(0) <= 'z') {
            return prefix + playerInput;
        }

        System.out.println("Invalid input. Try again with correct input");
        return playerOneMove(prefix);
    }

    // Computer makes a move.
    public String computerMove(String prefix){

        // Computer checks if prefix is already a word. In that case player1 already formed a word and Computer wins.
        if(ghostTrie.isCompleteWord(prefix)){
            System.out.println("Player 1 formed the word "+ prefix + " that exists in dictionary. Computer won.");
            System.exit(0);
        }

        /*
         * Computer checks if the player is trying to bluff. Computer finds the first complete word
         * that can be formed using the prefix formed by player1. If not word can be found then the computer
         * challenges the player1.
         */
        String prefixWord = ghostTrie.getFirstPrefixWord(prefix);
        if(prefixWord.equals("")){
            challengePlayerOne(prefix);
        }

        /*
         * Computer makes a move where it selects a character that maximises its chances of winning.
         */
        String comWord = findBestWordForComputer(prefix, ghostTrie);
        if(comWord.equals(NO_MORE_WORDS))
            System.out.println("Computer admits defeat. Player1 won");
        System.out.println("Word formed by computer is: "+ comWord);
        return comWord;
    }

    /*
     * Here computer challenges player1. Player1 is given a chance to enter a correct word which starts with prefix.
     * If that is the correct word then player1 wins, otherwise computer wins.
     */
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

    /*
     * Computer selects a character that maximises its chances of winning.
     * Computer looks for a word that ends when player1 makes a move.
     * for e.g. Computer selects a word yawnful, such that player1 enters the last l.
     * But while forming the word yawnful, the game has to go through yawn. Computer needs to make sure that
     * its not the one who's entering n in yawn. If computer is the one entering n, then it will discard the word yawnful
     */
    static String findBestWordForComputer(String currPrefix, GhostTrie ghostTrie) {
        // Get a list of words that starts with currPrefix
        List<String> prefixWords = ghostTrie.getWordsForPrefix(currPrefix);
        if (!prefixWords.isEmpty()) {
            for (String word : prefixWords) {
                // Filter only the words that end when player1 makes a move.
                int lenDiff = word.length() - currPrefix.length();
                if (lenDiff % 2 == 0) {
                    boolean consider = true;
                    for (int i = 1; i <= lenDiff; i++) {
                        if (i % 2 != 0) {
                            // if substring of a selected word can cause computer to loose then discard the word
                            if (ghostTrie.isCompleteWord(word.substring(0, currPrefix.length() + i))) {
                                consider = false;
                                break;
                            }
                        }
                    }
                    if (consider)
                        return word.substring(0, currPrefix.length()+1);
                }
            }

            /*
             * If computer coudn't find any word satisfying the above condition then
             * try to return a string which doesn't make it loose in the current turn.
             * Basically computer is trying to delay its loss, with the hope that player1 messes up
             * and looses before computer looses
             */
            for (String word : prefixWords) {
                if (!ghostTrie.isCompleteWord(word.substring(0, currPrefix.length() +1)))
                    return word.substring(0, currPrefix.length() +1);
            }

            /*
             * If none of the above conditions satisfy, then return the first string
             * which is on the path to make a complete word.
             */
            return prefixWords.get(0).substring(0, currPrefix.length() +1);
        }

        // If list of prefixWords is empty, then no words exist in the dictionary that starts with prefix words
        return NO_MORE_WORDS;
    }

}
