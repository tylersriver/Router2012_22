package sriver.w.tyler.router2017_22.support;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import sriver.w.tyler.router2017_22.networks.daemon.LL1Daemon;
import sriver.w.tyler.router2017_22.networks.datagram.LL2PFrame;

/**
 * Created by tyler.w.sriver on 1/26/17.
 *
 * This class is used to log and keep track of the frames
 */
public class FrameLogger extends Observable implements Observer {

    // -- Fields
    // --------------------------------------------------------------
    private static FrameLogger ourInstance = new FrameLogger();
    private ArrayList<LL2PFrame> frameList;

    // -- Methods
    // --------------------------------------------------------------

    /**
     * Return our singleton instance
     * @return FrameLogger
     */
    public static FrameLogger getInstance() {
        return ourInstance;
    }

    /**
     * Constructor
     */
    private FrameLogger() {
        frameList = new ArrayList<LL2PFrame>();
    }

    /**
     * Update method for observer changes
     * @param o Observable
     * @param arg arg
     */
    @Override
    public void update(Observable o, Object arg) {
        if(o.getClass().equals(LL1Daemon.class)) { // TODO: 1/26/17 Need class
            frameList.add( (LL2PFrame) arg );
        }

        setChanged();
        notifyObservers();
    }

    /**
     * return the frame list
     * @return ArrayList
     */
    public ArrayList<LL2PFrame> getFrameList(){
        return frameList;
    }
}
