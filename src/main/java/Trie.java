class TrieNode{
    public boolean isWord;
    TrieNode[] children;
    public TrieNode() {
        children = new TrieNode[26];
    }
}

public class Trie {
    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode ws = root;
        for(int i = 0; i< word.length(); i++){
            char ch = word.charAt(i);
            if(ws.children[ch - 'a'] == null)
                ws.children[ch - 'a'] = new TrieNode();
            ws = ws.children[ch - 'a'];
        }
        ws.isWord = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode ws = root;
        for(int i = 0; i< word.length(); i++){
            char ch = word.charAt(i);
            if(ws.children[ch - 'a'] == null)
                return false;
            ws = ws.children[ch - 'a'];
        }
        return ws.isWord;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode ws = root;
        for(int i = 0; i< prefix.length(); i++){
            char ch = prefix.charAt(i);
            if(ws.children[ch - 'a'] == null)
                return false;
            ws = ws.children[ch - 'a'];
        }
        return true;
    }
}
