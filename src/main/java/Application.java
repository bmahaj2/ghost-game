import java.util.List;
import java.util.Set;

public class Application {
    public static void main(String[] args){
        Dictionary dic = new Dictionary();
        Set<String> outputSet =  dic.createDictionarySet();
        System.out.println("Starting to insert dictionary items in Trie -----------------------------------------");
        GhostTrie ghostTrie = new GhostTrie(outputSet);
        System.out.println(outputSet.size()+" dictionary items inserted in ghostTrie");

        PlayGame game = new PlayGame(ghostTrie);
        game.startGame("");
    }

}
