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

    public static String IP_ADDRESS;	// the IP address of this system Note: will be stored here in dotted decimal notation
    public static int SOURCE_LL2P = 0x712712;
    public static int SOURCE_LL3P = 0x0A01;
    public static String IP_ADDRESS_PREFIX; // the prefix will be stored here
    final public static int UDP_PORT = 49999;

    final public static int THREAD_COUNT = 25;
    final public static int UI_UPDATE_INTERVAL = 1;
    final public static int ROUTER_BOOT_TIME = 5;
    final public static int MAX_AGE_ALLOWED = 30;


    // -- Header Fields
    // --------------------------------------------------------------
    final public static int LL2P_SOURCE_ADDRESS             = 21;
    final public static int LL2P_DEST_ADDRESS               = 22;
    final public static int LL2P_TYPE_FIELD                 = 23;
    final public static int LL2P_CRC_FIELD                  = 25;
    final public static int LL3P_SOURCE_ADDRESS             = 29;
    final public static int LL3P_DEST_ADDRESS               = 30;

    // -- LL2P Field Offsets - indices not bytes
    final public static int LL2P_DEST_ADDRESS_OFFSET        = 0;
    final public static int LL2P_SOURCE_ADDRESS_OFFSET      = 6;
    final public static int LL2P_TYPE_FIELD_OFFSET          = 12;
    final public static int LL2P_PAYLOAD_OFFSET             = 16;

    // -- LL2P lengths in bytes
    final public static int LL2P_CRC_FIELD_LENGTH           = 2;
    final public static int LL2P_ADDRESS_LENGTH             = 3;
    final public static int LL2P_TYPE_FIELD_LENGTH          = 2;
    final public static int LL3P_ADDRESS_FIELD_LENGTH       = 2;

    // -- LRP Field Offsets - indices not bytes
    final public static int LL3P_SOURCE_ADDRESS_OFFSET      = 0;
    final public static int SEQUENCE_NUMBER_OFFSET          = 4;
    final public static int COUNT_OFFSET                    = 5;
    final public static int FIRST_NETWORK_OFFSET            = 8;
    final public static int FIRST_DISTANCE_OFFSET           = 10;

    // -- LRP Field lengths in bytes
    final public static int LL3P_ADDRESS_LENGTH             = 2;
    final public static double SEQUENCE_NUMBER_LENGTH       = .5;
    final public static double COUNT_LENGTH                 = .5;
    final public static int NETWORK_LENGTH                  = 1;
    final public static int DISTANCE_LENGTH                 = 1;

    // -- LL2P Types
    final public static int LL2P_TYPE_IS_LL3P               = 0x8001;
    final public static int LL2P_TYPE_IS_RESERVED           = 0x8002;
    final public static int LL2P_TYPE_IS_LRP                = 0x8003;
    final public static int LL2P_TYPE_IS_ECHO_REQUEST       = 0x8004;
    final public static int LL2P_TYPE_IS_ECHO_REPLY         = 0x8005;
    final public static int LL2P_TYPE_IS_ARP_REQUEST        = 0x8006;
    final public static int LL2P_TYPE_IS_ARP_REPLY          = 0x8007;
    final public static int LL2P_TYPE_IS_TEXT               = 0x8008;

    // -- Record Constants
    // --------------------------------------------------------------

    // -- Record Types
    final public static int ADJACENCY_TABLE_RECORD  = 26;
    final public static int ARP_TABLE_RECORD        = 27;
    final public static int ROUTING_TABLE_RECORD    = 28;


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
     *
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
