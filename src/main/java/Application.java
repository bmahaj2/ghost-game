import java.util.List;
import java.util.Set;

public class Application {
    public static void main(String[] args){
        Dictionary dic = new Dictionary();
        Set<String> outputSet =  dic.createDictionarySet();
//        for(String item: outputSet)
//            System.out.println(item);
        System.out.println(outputSet.size());
        System.out.println("Starting to insert dictionary items in Trie -----------------------------------------");
        /////////////////////////////////////////////////////////////////////////
//        Trie trieObj = new Trie();
//        for(String item: outputSet) {
//            System.out.println(item);
//            trieObj.insert(item);
//        }
//        System.out.println(outputSet.size());
//        System.out.println(trieObj.search("bhavya"));
//        System.out.println(trieObj.search("cephalobranchiata"));
//        System.out.println(trieObj.startsWith("cephalobran"));
//        System.out.println(trieObj.startsWith("cephalobranchiata"));
//        System.out.println(trieObj.startsWith("cephalobranchiataa"));
//        System.out.println(trieObj.startsWith("cephalobrad"));
        /////////////////////////////////////////////////////////////////////////
        GhostTrie ghostTrie = new GhostTrie(outputSet);
        System.out.println(ghostTrie.isCompleteWord("helo"));
        System.out.println(ghostTrie.isCompleteWord("hell"));

        PlayGame game = new PlayGame(ghostTrie);
        game.startGame("");

//        String sample = "sta";
//       String bestWord = findBestForComputer(sample, ghostTrie);
//       System.out.println(bestWord);

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
                    if (word.length() < minLength)
                        minLength = word.length();
                    chosenWord = word;
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
                if (!chosenWord.equals(""))
                    return chosenWord;

            }

            for (String word : prefixWords) {
                if (!ghostTrie.isCompleteWord(word.substring(0, sample.length() + 1)))
                    return word;
            }
            return prefixWords.get(0);
        }
        return "NoMoreWords";
    }




//    static String findBestForComputer(String sample, GhostTrie ghostTrie){
//        List<String> prefixWords = ghostTrie.getWordsForPrefix(sample);
//        System.out.println("prefix words are:--------------------------");
//        for(String word: prefixWords) {
//            int lenDiff = word.length() - sample.length();
//            if(lenDiff % 2 == 0){
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
//            }
//
//        }
//
//        for(String word: prefixWords) {
//          if(! ghostTrie.isCompleteWord(word.substring(0, sample.length()+1)))
//              return word;
//        }
//        return prefixWords.get(0);
//    }

}
