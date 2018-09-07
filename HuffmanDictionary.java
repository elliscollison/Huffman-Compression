import java.util.Set;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Stack;
/**
 *FINISH COMMENTING
 * @author Gennie and Ellis
 * @version 4/20/18
 */
public class HuffmanDictionary implements CodeDictionary
{
    HuffmanTree tree; //the tree to be traversed
    HashMap<String, String> codeMap = new HashMap<>();

    /**
     * Constructor for objects of class HuffmanDictionary
     * 
     * @param HuffmanTree - tree, the huffman tree we want to base our HuffmanDictionary off of
     */
    public HuffmanDictionary(HuffmanTree tree)
    {
        this.tree = tree;
    }

    public HuffmanDictionary(String inFile){
        HuffmanTree temp = new HuffmanTree(inFile);
        tree = temp.top;
    }

    /**
     * Searches through the dictionary and finds the 
     * value mapped to the given key.
     * 
     * @param key
     * @return the value mapped by the given parameter
     */
    public String lookup(String key){
        //String c = key.substring(0,1);//the key is the String at the 0 position
        if (codeMap.containsValue(key)){
            
        } else {
            codeMap.put(lookupHelp(tree, key), key);
        }

        return lookupHelp(tree, key);
    }

    private String lookupHelp(HuffmanTree t, String key){        
        String code = "";
        CharFrequency rootData = (CharFrequency) t.getData();
        Set set = rootData.getCharacterSet();

        /** Base Case 1: Tree is a leaf and its value equals the inputted key */
        if (t.isLeaf() && set.contains(key) ){
            return code += "";
        }
        else if(t.isLeaf() && set.contains("\n") && key.equals("\n")){
            return code += "";
        }
        else if(t.isLeaf() && set.contains("\t") && key.equals("\t")){
            return code += "";
        }

        /** Traverses the left subtree */
        if(t.getLeftSubtree() != null){
            HuffmanTree left = t.getLeftSubtree();
            CharFrequency leftData = (CharFrequency) left.getData();
            Set leftSet = leftData.getCharacterSet();

            if(leftSet.contains(key)){
                return code += "0" + lookupHelp(t.getLeftSubtree(), key); //add 0 to code for left traversal
            }
        }

        /** Traverses right subtree */ 
        if(t.getRightSubtree() != null){
            HuffmanTree right = t.getRightSubtree();
            CharFrequency rightData = (CharFrequency) right.getData();
            Set rightSet = rightData.getCharacterSet();

            if (rightSet.contains(key)){
                return code += "1" + lookupHelp(t.getRightSubtree(), key); //add 1 to code for the right traversal
            }
        }

        throw new IllegalArgumentException();//throws an IllegalArgumentException if the key cannot be found
    }

    /**
     * Searches through the dictionary and finds the key 
     * mapped to the given value. If multiple keys are mapped, 
     * only the first occurrence is returned.
     * 
     * @param value
     * @return the key mapped from the given parameter
     */
    public String reverseLookup(String value){
        if (codeMap.containsKey(value)){
            return codeMap.get(value);
        }
        
        
        throw new IllegalArgumentException();
    }

    /**
     * Returns a table with the summary of the dictionary
     * Freq is the ratio of occurences of the individual character to total charactes in the document
     */
    public String toString(){
        Queue<HuffmanTree> pq = tree.helperPQ;

        String s = "Term \t" + "Freq \t" + "Code \n";
        CharFrequency treeRoot = (CharFrequency) tree.getData();
        Set<String> allChars = treeRoot.getCharacterSet(); //new HashSet<>();
        HashMap<String,Integer> map = tree.getEntries();
        int totalOccurences = treeRoot.getNumOccurrences();
        Stack<HuffmanTree> stack = new Stack<>();

        while (pq.size() > 0){
            HuffmanTree t = pq.poll();
            stack.add(t);
        }

        /** Make priotity queue of charfrequencies for list sorting */ 
        while (stack.size() > 0){
            //loops through all characters
            HuffmanTree tree =  stack.pop();
            CharFrequency cf = (CharFrequency) tree.root.data;
            String character = cf.getCharString();
            double freq = (double) cf.getNumOccurrences() / totalOccurences;

            if(character.equals("\n")){
                s += "\\n" + "\t" + freq + "\t" + lookup(character) + "\n";
            }
            else if (character.equals("\t")){
                s += "\\t" + "\t" + freq + "\t" + lookup(character) + "\n";
            }
            else{
                s += character + "\t" + freq + "\t" + lookup(character) + "\n";
            }
        }

        return s;
    }
}
