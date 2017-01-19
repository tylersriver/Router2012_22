package sriver.w.tyler.router2017_22.UI;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

import sriver.w.tyler.router2017_22.support.BootLoader;
import sriver.w.tyler.router2017_22.support.ParentActivity;

/**
 * Created by tyler.w.sriver on 1/19/17.
 *
 * This class is our top level manager for the UI
 * control
 */
public class UIManager implements Observer{

    // Fields
    // --------------------------------------------------------------
    private static UIManager ourInstance = new UIManager();
    private Activity parentActivity = ParentActivity.getActivity();
    private Context context = parentActivity.getBaseContext();

    // Methods
    // --------------------------------------------------------------
    private UIManager() {
    }

    public static UIManager getInstance() {
        return ourInstance;
    }

    public void raiseToast(String message, int displayTime) {
        Toast.makeText(context, message, displayTime).show();
    }

    public void raiseToast(String message){
        raiseToast(message, Toast.LENGTH_LONG);
    }

    private void setupWidgets() {

    }

    public void update(Observable object){

    }

    @Override
    public void update(Observable o, Object arg) {
        if (o.getClass().equals(BootLoader.class)){
            setupWidgets();
        }
    }
}
