import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.BasicConfigurator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class Dictionary {
    Set<String> wordSet;
    String fileName;
    private static final String DICTIONARY_FILENAME = "dictionary.txt";

    public Dictionary(){
        wordSet = new HashSet<String>();
        fileName = DICTIONARY_FILENAME;
    }

    /*
     * The following method reads words from filename and adds them to a hashset
     * Consider only the words which are more than 2 characters long and which only contains a-z or A-Z.
     * Words are converted to lowercase before adding them to a set.
     */

    public Set<String> createDictionarySet() {
        try {
            ClassLoader classLoader = ClassLoader.getSystemClassLoader();
            File file = new File(classLoader.getResource(fileName).getFile());
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String input;
            while ((input = bufferedReader.readLine()) != null ){
                if(input.length() > 2 && input.matches("^[a-zA-Z]*$")) {
                    wordSet.add(input.toLowerCase());
                }
            }
        } catch (IOException e){
            log.error("{} File not found", fileName);
        }
        return wordSet;
    }



}
