import java.util.Scanner;
import java.io.File;
import java.lang.Object;
import java.io.InputStream; 
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Queue; 
import java.util.PriorityQueue;
import java.util.Set;
import java.util.HashSet;
/**
 * This method constructs the Huffman Trees that we are going to use for the rest of our code.
 * The first thing the class does is read out a file and parse it by symbols. With these parsed
 * symbols (new lines and tabs are included) we then create a new binary tree with the root 
 * being the symbol. After that we add all of these to a priority queue which is order by the 
 * number of occurrences which is stored in the subtrees. With the priority queue we construct the sub
 * trees which are single characters at the leafs and sets as you traverse up the tree. 
 *
 * @author Gennie and Ellis
 * @version 4/20/18
 */
public class HuffmanTree<E extends Comparable> extends BinaryTree<E> implements Comparable<HuffmanTree>
{
    public FileInputStream file;//the file that we are analyzing 
    public HashMap<String,Integer> entries = new HashMap<>();//a hashmap that stores the character and the number of occurrences of that character
    public Queue<HuffmanTree> pq = new PriorityQueue<>();//priority tree of huffman trees used to construct the tree
    public Queue<HuffmanTree> helperPQ = new PriorityQueue<>();//priority tree of huffman tress that does not change
    public HuffmanTree top;//the top/root of the huffman tree
   
    
    /**
     * Constructor for objects of class HuffmanTree
     */
    public HuffmanTree(String inFile)
    {
        /** This is where the user reads in the file */
        try{
            file = new FileInputStream(inFile);//creates a new FileInputStream which links the program to the file

        }catch(FileNotFoundException e){
            System.out.println("The file was not found please enter another file name.");//throws the error if the file isn't found
            getFileName();//recalls the method so that the user can input another file name and try again

        }


        /** Make HashMap from the file */
        int content;
        try{
            while((content = file.read()) != -1){//while the file still has data to be scanned in
                char a = (char) content;//prints out each char separated by a space
                String s =  Character.toString(a);

                if (s.equals("\t")){
                    //New Tab!
                    if (entries.containsKey("\t")){
                        int temp = entries.get(s);
                        entries.replace("\t", temp+1);
                    } else {
                        entries.put("\t", 1);
                    }
                }else if (s.equals("\n")){
                    //New Line!
                    if (entries.containsKey("\n")){
                        int temp = entries.get(s);
                        entries.replace("\n", temp+1);
                    } else {
                        entries.put("\n", 1);
                    }
                }else if(entries.containsKey(s)){
                    int temp = entries.get(s);
                    entries.replace(s, temp+1);

                }else{
                    entries.put(s, 1);
                }
            }
        }catch(Exception e){//catches the exception 
            System.out.print("Some part of the file didn't work");//the message that the user is going to see

        }

        //System.out.println(entries);

        /** Fill PriorityQueue */
        for(String current : entries.keySet()){
            CharFrequency name = new CharFrequency(current, entries.get(current));
            HuffmanTree first = new HuffmanTree(name, null, null);

            pq.add(first);
            helperPQ.add(first);
        }

        /** Make BinaryTrees from the HashMap */
        while(pq.size() > 1){
            Set<String> tr = new HashSet<String>();

            HuffmanTree temp1 = pq.poll();
            HuffmanTree temp2 = pq.poll();

            CharFrequency temp3 = (CharFrequency) temp1.root.data;
            CharFrequency temp4 = (CharFrequency) temp2.root.data;

            for(String current : temp3.characters){
                tr.add(current);
            }

            for(String current1 : temp4.characters){
                tr.add(current1);
            }

            CharFrequency now = new CharFrequency(tr, (temp3.numOccurrences + temp4.numOccurrences));

            HuffmanTree new1 = new HuffmanTree(now, temp1, temp2);

            pq.offer(new1);
            top = new1;
            top.setMap(entries);
            top.setHelperPQ(helperPQ);

        }
    }

    /**
     * Creates a new Huffman Tree with a new root.
     * 
     * @param Node<E> - new_root, the new root of the huffman tree
     */
    public HuffmanTree(Node<E> new_root){
        super(new_root);
    }

    /**
     * Constructor for objects of class HuffmanTree
     * 
     * @param Node<E> - new_root, the new root of the huffman tree
     * @param HuffmanTree - left, the left subtree of the new huffman tree
     * @param HuffmanTree - right, the right subtree of the new huffman tree
     */
    public HuffmanTree(E a, HuffmanTree left, HuffmanTree right)
    {
        super(a, left, right); 
    }

    /**
     * Getter for the HashMap entries.
     * 
     * @return HashMap - entries, the hashmap with all of the charFrequency objects
     */
    public HashMap getEntries(){
        return entries;
    }

    /**
     * Method that uses the scanner object to ask the user for the
     * name of the movie review file and then returns the file name.
     * 
     * @return fileName - file, that user had entered to be trained.
     */
    public String getFileName(){
        System.out.println("Instructions: Enter a file to create the Huffman Tree");//prompts the user to enter a file name
        Scanner keyboard = new Scanner(System.in);
        String fileName = keyboard.nextLine();//assigns the file name to the next line that the user inputs 
        try{
            file = new FileInputStream(fileName);//creates a new FileInputStream which links the program to the file

        }catch(FileNotFoundException e){
            System.out.println("The file was not found please enter another file name.");//throws the error if the file isn't found
            getFileName();//recalls the method so that the user can input another file name and try again

        }

        keyboard.close();//closes the scanner
        return fileName;
    }

    /**
     * Method makes the hashmap of all of the character frequency objects.
     */
    public void makeHashMap(){
        int content;
        try{
            while((content = file.read()) != -1){//while the file still has data to be scanned in
                String s = Integer.toString(content);
                if(entries.containsKey(s)){
                    int temp = entries.get(s);
                    entries.replace(s, temp+1);
                }else{
                    entries.put(s, 1);
                }
            }
        }catch(Exception e){//catches the exception 
            System.out.print("Some part of the file didn't work");//what part of the file didn't work?
            //e.printStackTrace();//prints the stacktrace to find out what part didn't work

        }

    }

    /**
     * Setter for the HashMap of char frequency objects.
     * 
     * @param Hashmap - h, the hashmap we want entries to be
     */
    public void setMap(HashMap h){
        entries = h;
    }

    /**
     * This method makes the huffman trees by taking the priority queue of charFrequency objects
     * and constructing the huffman tree based on number of occurrences. 
     */
    public void makeHuffmanTree(){
        while(pq.size() > 1){
            Set<String> tr = new HashSet<String>();

            HuffmanTree temp1 = pq.poll();
            HuffmanTree temp2 = pq.poll();

            CharFrequency temp3 = (CharFrequency) temp1.root.data;
            CharFrequency temp4 = (CharFrequency) temp2.root.data;

            for(String current : temp3.characters){
                tr.add(current);
            }

            for(String current1 : temp4.characters){
                tr.add(current1);
            }

            CharFrequency now = new CharFrequency(tr, (temp3.numOccurrences + temp4.numOccurrences));

            HuffmanTree new1 = new HuffmanTree(now, temp1, temp2);

            pq.add(new1);

        }

    }

    /**
     * Setter for helperPQ which is the priority queue that we don't alter in order to keep track
     * of the data. 
     * 
     * @param Queue - q, the queue that we want helperPQ to be equal to 
     */
    public void setHelperPQ(Queue q){
        helperPQ = q;
    }

    /**
     * Another compare to method that we are using in order to order the priority queue of huffman
     * trees, based on the total number of occurrences we have in the set.
     * 
     * @return int - 0 if the objects are equal in terms of number of occurrences, a negative int
     * if the left charFrequency object's number of occurrences is bigger than the right and a 
     * positive number if the left charFrequency object's number of occurrences is smaller than the right
     */
    @Override
    public int compareTo(HuffmanTree t){
        CharFrequency cf = (CharFrequency) t.root.data;
        CharFrequency cf2 = (CharFrequency) this.root.data; 
        return cf2.getNumOccurrences() - cf.getNumOccurrences();
    }

    /**
     * This method gets the left subtree of the huffman tree.
     * 
     * @return the left subtree, or null if either the root or the left subtree is null
     */
    @Override
    public HuffmanTree<E> getLeftSubtree() {
        if (root != null && root.left != null) {
            // need to "wrap" left node in a BinaryTree object before returning
            HuffmanTree<E> subtree = new HuffmanTree<>(root.left);
            return subtree;
        }
        return null;
    }

    /**
     * This method gets the right subtree of the huffman tree. 
     * 
     * @return the right subtree, or null if either the root or the right subtree is null
     */
    @Override
    public HuffmanTree<E> getRightSubtree() {
        if (root != null && root.right != null) {
            // need to "wrap" right node in a BinaryTree object before returning
            HuffmanTree<E> subtree = new HuffmanTree<>(root.right);
            return subtree;
        }
        return null;
    }

    /**
     * This method gets the top of the huffman tree which is also the root of the huffman tree.
     * 
     * @return HuffmanTree - top, which is also the root of the huffman tree.
     */    
    public HuffmanTree getTop(){
        return top;
    }
}
