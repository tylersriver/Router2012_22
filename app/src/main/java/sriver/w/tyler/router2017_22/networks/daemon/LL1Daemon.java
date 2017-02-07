package sriver.w.tyler.router2017_22.networks.daemon;

import android.database.CursorIndexOutOfBoundsException;
import android.util.Log;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Observable;
import java.util.Observer;

import sriver.w.tyler.router2017_22.UI.UIManager;
import sriver.w.tyler.router2017_22.networks.Constants;
import sriver.w.tyler.router2017_22.networks.datagram.LL2PFrame;
import sriver.w.tyler.router2017_22.networks.table.Table;
import sriver.w.tyler.router2017_22.networks.tablerecord.AdjacencyRecord;
import sriver.w.tyler.router2017_22.support.BootLoader;
import sriver.w.tyler.router2017_22.support.Factory;
import sriver.w.tyler.router2017_22.support.FrameLogger;
import sriver.w.tyler.router2017_22.support.GetIPAddress;

/**
 * Created by tyler.w.sriver on 1/26/17.
 *
 * Daemon class
 */
public class LL1Daemon extends Observable implements Observer {
    // -- Fields
    // --------------------------------------------------------------
    private static LL1Daemon ourInstance = new LL1Daemon();
    private DatagramSocket receiveSocket;
    private DatagramSocket sendSocket;
    private Table adjacencyTable;
    private GetIPAddress nameServer;
    private UIManager uiManager;
    private Factory factory;
    private LL2PDaemon ll2PDaemon;


    // -- Methods
    // --------------------------------------------------------------

    /**
     * Return the singleton instance
     * @return LL1Daemon
     */
    public static LL1Daemon getInstance() {
        return ourInstance;
    }

    /**
     * Constructor called internally
     */
    private LL1Daemon() {
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o.getClass().equals(BootLoader.class)) {
            openSockets();
            nameServer = GetIPAddress.getInstance();
            factory = Factory.getInstance();
            addObserver(FrameLogger.getInstance());
            uiManager = UIManager.getInstance();
            ll2PDaemon = LL2PDaemon.getInstance();
            // TODO: 2/7/2017 need spin off thread
        }
    }

    /**
     * Remove given record from adjacency table
     * @param recordToRemove AdjacencyRecords
     */
    public void removeAdjacency(AdjacencyRecord recordToRemove){
        adjacencyTable.removeItem(recordToRemove.getKey());
    }

    /**
     * Get the Adjacency table
     * @return AdjacencyTable
     */
    public AdjacencyTable getAdjacencyTable(){
        return adjacencyTable;
        // TODO: 2/7/2017 needing obvious class AdjacencyTable
    }

    /**
     * Open up UDP Sockets
     */
    private void openSockets(){
        try { // -- Open the send socket
            sendSocket = new DatagramSocket();
            Log.d(Constants.logTag, "Socket opened successfully");
        } catch (SocketException e){
            Log.d(Constants.logTag, "openSockets: " + e.toString());
        } // -- End --

        try { // -- Open receive socket
            receiveSocket = new DatagramSocket(Constants.UDP_PORT);
            Log.d(Constants.logTag, "Socket opened successfully");
        } catch (SocketException e) {
            Log.d(Constants.logTag, "openSockets: " + e.toString());
        } // -- End --
    }

    /**
     * Transmit ll2p frame
     * @param ll2p LL2PFrame
     */
    public void sendFrame(LL2PFrame ll2p){
        // TODO: 2/7/2017 Spin off thread to Tx frame
        notifyObservers(ll2p);
    }
}
