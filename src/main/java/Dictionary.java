import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Dictionary {
    Set<String> wordSet;
    String fileName;

    public Dictionary(){
        wordSet = new HashSet<String>();
        fileName = "dictionary.txt";
    }

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
            System.out.println("File not found");
        }
        return wordSet;
    }



}
