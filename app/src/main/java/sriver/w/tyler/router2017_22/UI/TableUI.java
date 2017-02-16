package sriver.w.tyler.router2017_22.UI;

import android.app.Activity;

import java.util.Observable;
import java.util.Observer;

import sriver.w.tyler.router2017_22.support.BootLoader;
import sriver.w.tyler.router2017_22.support.ParentActivity;

/**
 * Created by tyler.w.sriver on 2/16/17.
 */

public class TableUI implements Runnable, Observer {

    // -- Fields
    // --------------------------------------------------------------
    private SingleTableUI adjacencyUI;
    private SingleTableUI arpTableUI;
    private SingleTableUI routingTableUI;
    private SingleTableUI forwardingUI;

    // -- Methods
    // --------------------------------------------------------------
    public TableUI(){
    }

    @Override
    public void run() {

    }

    @Override
    public void update(Observable o, Object arg) {
        if(o.getClass().equals(BootLoader.class)) {
            Activity activity = ParentActivity.getActivity();
            adjacencyUI = new
        }
    }
}
