/**
 * This class contains the main method. Uncomment the files as
 * appropriate to run your compression tests.
 *
 * @author Gennie and Ellis
 * @version 4/20/18
 */
public class Compressor {
    /*
    private static String inFilename = "data/Loggers.txt";
    private static String compFilename = "data/Loggers.huff";
    private static String decompFilename = "data/Loggers_inflated.txt";
    */
   
    //As You Like It (120 KB original)
    private static String inFilename = "data/AsYouLikeIt_orig.txt";
    private static String compFilename = "data/AsYouLikeIt.huff";
    private static String decompFilename = "data/AsYouLikeIt_inflated.txt";
   

    /* Pride and Prejudice (687 KB original)
    private static String inFilename = "data/Pride_orig.txt";
    private static String compFilename = "data/Pride.huff";
    private static String decompFilename = "data/Pride_inflated.txt";
    */

    /* Gone with the Wind (2.4 MB original)
    private static String inFilename = "data/Gone_orig.txt";
    private static String compFilename = "data/Gone.huff";
    private static String decompFilename = "data/Gone_inflated.txt";
    */

    /**
     * Main method runs Huffman's algorithm on a given file
     * @param args Unused
     */
    public static void main(String[] args) {
        //use the input file to build dictioary
        Huffman compressor = new Huffman();
        compressor.buildDictionary(inFilename);
        compressor.printDictionary();

        //compress the original file!
        System.out.println("Compressing " + inFilename + " into " + compFilename);
        compressor.encode(inFilename, compFilename);
        System.out.println("Complete (compression ratio: " + 
                compressor.getCompressionRatio() + ")\n");

        //decode it: inflated file better be same as original!
        System.out.println("Inflating " + compFilename + " into " + decompFilename);
        compressor.decode(compFilename, decompFilename);
        System.out.println("Complete.");
    }
}
