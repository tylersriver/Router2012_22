package sriver.w.tyler.router2017_22.support;

import android.app.Activity;

/**
 * Created by tyler.w.sriver on 1/11/2017.
 *
 * This is a singleton class used to provide
 * a safe copy of this Activity for the application
 */
public class ParentActivity {

    // -- Fields
    // --------------------------------------------------------------
    static Activity parentActivity;
    private static ParentActivity ourInstance = new ParentActivity();

    // -- Methods
    // --------------------------------------------------------------

    /**
     * Return singleton instance
     * @return ParentActivity
     */
    public static ParentActivity getInstance() {
        return ourInstance;
    }

    /**
     * Constructor
     */
    private ParentActivity() {
    }

    /**
     * Retrieve the parentActivity
     * @return Activity
     */
    public static Activity getActivity() {
        return parentActivity;
    }

    /**
     * Set the Activity
     * @param Activity Activity
     */
    public static void setActivity(Activity Activity) {
        parentActivity = Activity;
    }
}
