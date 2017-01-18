package sriver.w.tyler.router2017_22.support;

import android.app.Activity;

/**
 * Created by tyler.w.sriver on 1/11/2017.
 *
 * This is a singleton class used to provide
 * a safe copy of this Activity for the application
 */
public class ParentActivity {

    static Activity parentActivity;

    private static ParentActivity ourInstance = new ParentActivity();

    public static ParentActivity getInstance() {
        return ourInstance;
    }

    private ParentActivity() {
    }

    public Activity getActivity() {
        return parentActivity;
    }

    public static void setActivity(Activity Activity) {
        parentActivity = Activity;
    }
}
