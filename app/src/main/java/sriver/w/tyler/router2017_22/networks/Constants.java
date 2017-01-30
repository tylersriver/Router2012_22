package sriver.w.tyler.router2017_22.networks;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by tyler.w.sriver on 1/11/2017.
 *
 * This class stores the many global constants
 * needed by the entire applications
 */
public class Constants implements Observer {

    // -- Fields
    // --------------------------------------------------------------
    public static String routerName = new String("RouteMaster");
    public static String logTag = new String("ROUTEMASTER: ");
    private static Constants ourInstance = new Constants();

    public static String IP_ADDRESS;	// the IP address of this system
    //will be stored here in dotted decimal notation
    public static String IP_ADDRESS_PREFIX; // the prefix will be stored here


    // -- LL2P Frame Constants
    final public static int LL2P_SOURCE_ADDRESS = 21;
    final public static int LL2P_DEST_ADDRESS = ; // TODO: 1/29/2017 What is?
    final public static int LL2P_TYPE_FIELD = ; // TODO: 1/29/2017 What is? 
    final public static int LL2P_PAYLOAD_FIELD = ; // TODO: 1/29/2017 What is?
    final public static int LL2P_CRC_FIELD = ; // TODO: 1/29/2017 What is?
    
    final public static int LL2P_DEST_ADDRESS_OFFSET = 0;
    final public static int LL2P_SOURCE_ADDRESS_OFFSET = 3;
    final public static int LL2P_TYPE_FIELD_OFFSET = 6;
    final public static int LL2P_PAYLOAD_OFFSET = 8;
    final public static int LL2P_CRC_FIELD_OFFSET = 40;

    // -- Methods
    // --------------------------------------------------------------

    /**
     * Constructor for Constants -- will eventually find out my IP address and do other nice
     * things that need to be set up in the constants file.
     */
    protected Constants (){
    // call the local method to get the IP address of this device.
        IP_ADDRESS = getLocalIpAddress();
        int lastDot = IP_ADDRESS.lastIndexOf(".");
        int secondDot = IP_ADDRESS.substring(0, lastDot-1).lastIndexOf(".");
        IP_ADDRESS_PREFIX = IP_ADDRESS.substring(0, secondDot+1);
    }

    /**
     * getLocalIPAddress - this function goes through the network interfaces,
     *    looking for one that has a valid IP address.
     * Care must be taken to avoid a loopback address and IPv6 Addresses.
     * @return - a string containing the IP address in dotted decimal notation.
     */
    public String getLocalIpAddress() {
        String address= null;
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * TODO: Add desc when needed
     * @param o Observable
     * @param arg Object
     */
    @Override
    public void update(Observable o, Object arg) {
    }


    /**
     * Return our singleton instance
     * @return Constants
     */
    public static Constants getInstance() {
        return ourInstance;
    }
}
