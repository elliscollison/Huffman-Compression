
/**
 * TODOOOOOOOO
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Tester
{
    public static void main(String[] args){
        //HuffmanTree tree = new HuffmanTree(data/Loggers.txt);//makes a new instance of huffman tree

        
        HuffmanDictionary dict = new HuffmanDictionary("data/AsYouLikeIt_orig.txt");//creates a new dictionary with the root of the tree as the parameter
        
        //System.out.println(dict.toString());//prints out the toString of the Huffman Dictionary 
        
        dict.lookup("\\n");
    }
}
