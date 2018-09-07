import java.util.Set;
import java.util.LinkedList;
import java.util.HashSet;
/**
 * The character frequency objects hold the individual characters that we are keeping track of throughout
 * the text. Once we scan in the file that we are analyzing we create a character frequency object
 * for each unique character that we find in the file. The object holds a String representation of each of
 * the characters as well as keeps track of the number of occurrences for each character. In addition we
 * hava separate constructor that puts the chars into a set and calculates the total number of occurrences
 * for that set. We have a getter for the number of occurrences as well as a getter for the character set. 
 * Finally we have a method that returns a String representation of the Set of characters, a toString
 * method that returns a string representation of the object with its number of occurences and a compareTo
 * method that sorts the object based on number of occurrences. 
 *
 * @author Gennie and Ellis
 * @version 4/24/18
 */
public class CharFrequency implements Comparable<CharFrequency>
{
    public Set<String> characters = new HashSet<String>();//new hash set of Strings
    public int numOccurrences;//number of occurrences for eacher character
    
    /**
     * Constructor for objects of charFrequency.
     * 
     * @param - String a, the character that we are keeping track of in the text
     * @param - int numOccurrences, the number of times each character appears in the text
     */
    public CharFrequency(String a, int numOccurrences){
        characters.add(a);//add characters to the HashSet
        this.numOccurrences = numOccurrences;//assigns the number of characters to the HashSet
    }

    /**
     * Constructor for objects of class charFrequency
     * 
     * @param - Set chars, a set of characters that are in the text
     * @param - int numOccurrences, the total number of times the set of chars each appear in the text
     */
    public CharFrequency(Set chars, int numOccurrences)
    {
        characters = chars;//assigns chars to the character variable
        this.numOccurrences = numOccurrences;//initializes the number of occurrences
    }

    /**
     * Getter for numOccurrences
     * 
     * @return  int numOccurences - the number of times a char appears in the file
     */
    public int getNumOccurrences()
    {
        return numOccurrences;
    }
    
    /**
     * Getter for characters.
     * 
     * @return Set characters - the set of the characters that appear in the file
     */
    public Set getCharacterSet(){
        return characters;
    }
    
    /**
     * Returns the characters in string form.
     * 
     * @return String chars - the String that consists of the characters in the text
     */
    public String getCharString(){
        String chars = "";//initializes the string with nothing in it
        for (String s: characters){//for each element in the character hashSet add it to the string
            chars += String.valueOf(s);
        }
        
        return chars;
    }
    
    /**
     * To String method for the CharFrequency objects.
     * 
     * @return String str - a string that consists of the character and its number of occurrences
     */
    public String toString(){
        String str = "";
        str = characters.toString() + " " + numOccurrences;//adds the character and its numOccurrences to the string
        return str;
    }
    
    /**
     * CompareTo method for the Character Frequency objects that sees which object has a higher
     * number of occurrences in the text.
     * 
     * @return int - 0 if the objects are equal in terms of number of occurrences, a negative int
     * if the left charFrequency object's number of occurrences is bigger than the right and a 
     * positive number if the left charFrequency object's number of occurrences is smaller than the right
     */
    @Override
    public int compareTo(CharFrequency f){
        return numOccurrences - f.numOccurrences;
    }
}
