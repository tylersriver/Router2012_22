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

    // -- Fields
    // --------------------------------------------------------------
    private static UIManager ourInstance = new UIManager();
    private Activity parentActivity = ParentActivity.getActivity();
    private Context context = parentActivity.getBaseContext();
    private TableUI tableUI;
    private SnifferUI snifferUI;

    // -- Methods
    // --------------------------------------------------------------

    /**
     * Constructor
     */
    private UIManager() {
        tableUI = new TableUI();
        snifferUI = new SnifferUI();
    }

    /**show
     * Return the singleton instance
     * @return UIManager
     */
    public static UIManager getInstance() {
        return ourInstance;
    }

    /**
     * Display message on screen
     * @param message String
     * @param displayTime int
     */
    public void raiseToast(String message, int displayTime) {
        Toast.makeText(context, message, displayTime).show();
    }

    /**
     * Override display without given time
     * @param message String
     */
    public void raiseToast(String message){
        raiseToast(message, Toast.LENGTH_LONG);
    }

    /**
     * Build the widgets
     */
    private void setupWidgets() {
    }

    /**
     * Update information
     * @param object Observable
     */
    public void update(Observable object){

    }

    /**
     * Setup widgets on notified
     * @param o Observable
     * @param arg Object
     */
    @Override
    public void update(Observable o, Object arg) {
        if (o.getClass().equals(BootLoader.class)){
            setupWidgets();
        }
    }

    /**
     * Return the table UI
     * @return TableUI
     */
    public TableUI getTableUI() {
        return tableUI;
    }

    /**
     * Return the Sniffer UI
     * @return SnifferUI
     */
    public SnifferUI getSnifferUI() {
        return snifferUI;
    }
}
