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
        if(ghostTrie.isCompleteWord(prefix)) {
            System.out.println(prefix);
            System.out.println("Computer formed a word. Player 1 wins");
        }
        return "prefix1";
    }

    public String computerMove(String prefix){
        return "prefix2";
    }
}
