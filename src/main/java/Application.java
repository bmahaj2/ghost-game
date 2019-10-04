import java.util.Set;

public class Application {
    public static void main(String[] args){
        Dictionary dic = new Dictionary();
        Set<String> outputSet =  dic.createDictionarySet();
//        for(String item: outputSet)
//            System.out.println(item);
        System.out.println(outputSet.size());
        System.out.println("Starting to insert dictionary items in Trie -----------------------------------------");
        Trie trieObj = new Trie();
        for(String item: outputSet) {
            System.out.println(item);
            trieObj.insert(item);
        }
        System.out.println(outputSet.size());
        System.out.println(trieObj.search("bhavya"));
        System.out.println(trieObj.search("cephalobranchiata"));
        System.out.println(trieObj.startsWith("cephalobran"));
        System.out.println(trieObj.startsWith("cephalobranchiata"));
        System.out.println(trieObj.startsWith("cephalobranchiataa"));
        System.out.println(trieObj.startsWith("cephalobrad"));
    }

}
