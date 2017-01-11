package sriver.w.tyler.router2017_22.support;

import android.app.Activity;
import java.util.Observable;
import sriver.w.tyler.router2017_22.networks.Constants;

/**
 * Created by tyler.w.sriver on 1/11/2017.
 */
public class BootLoader extends Observable {

    public BootLoader(Activity activity){
        bootRouter(activity);
    }

    private void bootRouter(Activity activity){
        ParentActivity.setActivity(activity);
        addObserver(Constants.getInstance());

        setChanged();
        notifyObservers();
    }
}
