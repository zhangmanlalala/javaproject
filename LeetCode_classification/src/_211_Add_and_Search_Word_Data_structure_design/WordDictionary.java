package _211_Add_and_Search_Word_Data_structure_design;
/**
 * Design a data structure that supports the following two operations:
	void addWord(word)
	bool search(word)
	
	search(word) can search a literal word or a regular expression string 
	containing only letters a-z or .. A . means it can represent any one letter.
	
	For example:
	
	addWord("bad")
	addWord("dad")
	addWord("mad")
	search("pad") -> false
	search("bad") -> true
	search(".ad") -> true
	search("b..") -> true

Note:
You may assume that all words are consist of lowercase letters a-z 
 * 
 * 
 * 
 * @author Administrator
 *
 */
public class WordDictionary {
    // Adds a word into the data structure.
	
	public class TrieNode{
		public TrieNode[] children = new TrieNode[26];
		public String item = "";
	}
	private TrieNode root = new TrieNode();
	
    public void addWord(String word) {
        TrieNode node = root;
        for(char c:word.toCharArray()){
        	if(node.children[c-'a'] == null){
        		node.children[c-'a'] = new TrieNode();
        	}
        	
        	node = node.children[c-'a'];
        }
        
        node.item = word;
    }
    
    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        return match(word.toCharArray(),0,root);
    }
    
    private boolean match(char[] chs,int k,TrieNode node){
    	if(k == chs.length) return !node.item.equals("");
    	if(chs[k] !='.'){
    		return node.children[chs[k] - 'a'] !=null && match(chs,k+1,node.children[chs[k]-'a']);
    	}else{
    		for(int i=0;i<node.children.length;i++){
    			if(node.children[i]!=null){
    				if(match(chs,k+1,node.children[i])){
    					return true;
    				}
    			}
    		}
    	}
    	
    	
    	
    	return false;
    }
}
