import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.BasicConfigurator;
import java.util.Set;


/*
 * The main entry point of the project.
 * It reads words from the file, adds them to a Trie and then chooses player1 or computer randomly to start the game
 */

@Slf4j
public class Application {
    public static void main(String[] args){
        //Configure logger
        BasicConfigurator.configure();
        Dictionary dic = new Dictionary();
        Set<String> outputSet =  dic.createDictionarySet();
        log.info("Starting to insert dictionary items in Trie");
        GhostTrie ghostTrie = new GhostTrie(outputSet);
        log.info("Total number of dictionary items inserted in ghostTrie are {}", outputSet.size());
        PlayGame game = new PlayGame(ghostTrie);
        int choosePlayer = game.getRandom(10);
        game.startGame("", choosePlayer);
    }

}
