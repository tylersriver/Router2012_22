package sriver.w.tyler.router2017_22.support;

/**
 * Created by tyler.w.sriver on 1/19/17.
 *
 * This class is used for logging exceptions
 * that occur within the application
 */

public class LabException extends Exception {

    // Fields
    // --------------------------------------------------------------
    private static final long serialVersionUID = 1L;

    // Methods
    // --------------------------------------------------------------
    public LabException(String errorMessage){
        super(errorMessage);
    }

}
