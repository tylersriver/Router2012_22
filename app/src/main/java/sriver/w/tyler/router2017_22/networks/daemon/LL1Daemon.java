package sriver.w.tyler.router2017_22.networks.daemon;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
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
import sriver.w.tyler.router2017_22.support.LabException;
import sriver.w.tyler.router2017_22.support.PacketInformation;

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
            adjacencyTable = new Table();
            new ReceiveUnicastFrame().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, receiveSocket);
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
    public Table getAdjacencyTable(){
        return adjacencyTable;
    }

    /**
     * add record to table
     * @param LL2PAddress String
     * @param ipaddress String
     */
    public void addAdjacency(String LL2PAddress, String ipaddress){
        AdjacencyRecord record = new AdjacencyRecord(nameServer.getInetAddress(ipaddress), Integer.valueOf(LL2PAddress, 16));
        adjacencyTable.addItem(record);
        notifyObservers(record);
    }

    public AdjacencyRecord getAdjacencyRecord(Integer key){
        try {
            return (AdjacencyRecord) adjacencyTable.getItem(key);
        } catch (LabException e) {
            e.printStackTrace();
            Log.d(Constants.logTag, "getAdjacencyRecord: "+e.toString());
        } return null;
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

        AdjacencyRecord record = null;
        try{
            record = (AdjacencyRecord) adjacencyTable.getItem(ll2p.getDestinationAddressValue());
        } catch (LabException e){
            e.printStackTrace();
        }

        InetAddress ipaddress = record.getIpaddress();
        DatagramPacket sendPacket = new DatagramPacket(ll2p.toString().getBytes(), ll2p.toString().length(), ipaddress, Constants.UDP_PORT);
        new SendUDPPacket().execute(new PacketInformation(sendSocket, sendPacket));
        notifyObservers(ll2p);
    }

    /**
     * Method to receive frame
     * @return DatagramPacket
     */
    public DatagramPacket receiveFrame(){
        byte[] receiveData = new byte[1024];   // byte array to store received bytes.
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        try {
            receiveSocket.receive(receivePacket); // check the socket for packet.
        } catch (IOException e) {
            e.printStackTrace();
        }
        return receivePacket;
    }

    // -- Classes
    // --------------------------------------------------------------

    /**
     * Async thread class for sending packets
     */
    private class SendUnicastFrame extends AsyncTask<PacketInformation, Void, Void>{
        @Override
        protected Void doInBackground(PacketInformation... params) {
            return null;
        }
    }

    /**
     * Async thread class for receiving packets
     */
    private class ReceiveUnicastFrame extends AsyncTask<DatagramSocket, Void, byte[]> {

        @Override
        protected byte[] doInBackground(DatagramSocket... params) {
            return new byte[0];
        }

        /**
         * Receive
         * @param frameBytes byte[]
         */
        protected void onPostExecute(byte[] frameBytes) {
            // get an LL2P Frame object and notify the Frame Logger, passing frame.
            LL2PFrame ll2PFrame = new LL2PFrame(frameBytes);
            setChanged();
            notifyObservers(ll2PFrame);

            // pass this LL2P Frame to the LL2PDaemon when you create one...
            ll2PDaemon.processLL2PFrame(ll2PFrame);
            // spin off a new thread to listen for packets.
            new ReceiveUnicastFrame().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, receiveSocket);
        }
    }

    /** send UDP Packet
     * A private Async class to send packets out of the UI thread.
     */
    private class SendUDPPacket extends AsyncTask<PacketInformation, Void, Void> {
        @Override
        protected Void doInBackground(PacketInformation... arg0) {
            PacketInformation pktInfo = arg0[0];
            try {
                pktInfo.getSocket().send(pktInfo.getPacket());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
