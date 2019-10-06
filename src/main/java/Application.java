import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.BasicConfigurator;
import java.util.Set;

@Slf4j
public class Application {
    public static void main(String[] args){
        //Configure logger
        BasicConfigurator.configure();
        Dictionary dic = new Dictionary();
        Set<String> outputSet =  dic.createDictionarySet();
        log.info("Starting to insert dictionary items in Trie -----------------------------------------");
        GhostTrie ghostTrie = new GhostTrie(outputSet);
        log.info("Total number of dictionary items inserted in ghostTrie are {}", outputSet.size());
        PlayGame game = new PlayGame(ghostTrie);
        game.startGame("");
    }

}
