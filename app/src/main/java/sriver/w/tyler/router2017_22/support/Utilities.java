package sriver.w.tyler.router2017_22.support;

/**
 * Created by tyler.w.sriver on 1/11/2017.
 *
 * This class has a bunch of static methods
 * for use within the application 
 */
public class Utilities {

    // -- Fields
    // --------------------------------------------------------------
    private static Utilities ourInstance = new Utilities();
    public static Utilities getInstance() {
        return ourInstance;
    }

    // -- Methods
    // --------------------------------------------------------------

    /**
     * Constructor
     */
    private Utilities() {
    }

    /**
     * Function to prepend 0's to hex string
     * @param hexString String
     * @param finalLength int
     * @return String
     */
     static public String padHexString(String hexString, int finalLength){
        if(hexString.length() < finalLength*2) { // Make sure padding is needed
            int zerosNeeded = finalLength*2 - hexString.length();
            String padding = "";

            // Build string of needed zeros
            for (int i = 0; i < zerosNeeded; i++) {
                padding = padding.concat("0");
            }

            // build final string
            hexString = padding.concat(hexString);
        }
        return hexString;
    }
}
