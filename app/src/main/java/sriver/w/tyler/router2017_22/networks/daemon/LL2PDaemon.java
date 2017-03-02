package sriver.w.tyler.router2017_22.networks.daemon;

import android.provider.ContactsContract;
import android.util.Log;

import java.sql.CallableStatement;
import java.util.Observable;
import java.util.Observer;

import sriver.w.tyler.router2017_22.UI.UIManager;
import sriver.w.tyler.router2017_22.networks.Constants;
import sriver.w.tyler.router2017_22.networks.datagram.Datagram;
import sriver.w.tyler.router2017_22.networks.datagram.LL2PFrame;
import sriver.w.tyler.router2017_22.networks.datagram.TextDatagram;
import sriver.w.tyler.router2017_22.networks.datagram_fields.CRC;
import sriver.w.tyler.router2017_22.networks.datagram_fields.DatagramPayloadField;
import sriver.w.tyler.router2017_22.networks.datagram_fields.LL2PAddressField;
import sriver.w.tyler.router2017_22.networks.datagram_fields.LL2PTypeField;
import sriver.w.tyler.router2017_22.support.BootLoader;
import sriver.w.tyler.router2017_22.support.Factory;
import sriver.w.tyler.router2017_22.support.Utilities;

/**
 * Created by tyler on 2/7/2017.
 *
 * Daemon to process LL2P frames
 */
public class LL2PDaemon implements Observer{

    // -- Fields
    // --------------------------------------------------------------
    private static LL2PDaemon ourInstance = new LL2PDaemon();
    private UIManager uiManager;
    private LL1Daemon ll1Daemon;


    // -- Methods
    // --------------------------------------------------------------
    public static LL2PDaemon getInstance() {
        return ourInstance;
    }

    private LL2PDaemon() {
    }

    public void processLL2PFrame(LL2PFrame frame){
        Log.d(Constants.logTag, "processLL2PFrame: "+ frame.toString());
        uiManager.raiseToast("Receive LL2PFrame: " + frame.toString());
        if(frame.getDestVal().equals(Constants.SOURCE_LL2P)){
            processType(frame);
        } else {
            uiManager.raiseToast("Destination Address doesn't match");
        }
    }

    /**
     * handle the frame based on type
     * @param frame LL2PFrame
     */
    private void processType(LL2PFrame frame){
        switch (Integer.valueOf(frame.getType().toString(), 16)){
            case Constants.LL2P_TYPE_IS_ARP_REPLY:
                uiManager.raiseToast("Unsupported Frame Type");
                break;
            case Constants.LL2P_TYPE_IS_ARP_REQUEST:
                uiManager.raiseToast("Unsupported Frame Type");
                break;
            case Constants.LL2P_TYPE_IS_RESERVED:
                uiManager.raiseToast("Unsupported Frame Type");
                break;
            case Constants.LL2P_TYPE_IS_LRP:
                uiManager.raiseToast("Unsupported Frame Type");
                break;
            case Constants.LL2P_TYPE_IS_LL3P:
                uiManager.raiseToast("Unsupported Frame Type");
                break;
            case Constants.LL2P_TYPE_IS_ECHO_REQUEST:
                answerEchoRequest(frame);
            case Constants.LL2P_TYPE_IS_ECHO_REPLY:
                uiManager.raiseToast("Received Echo Reply: "+frame.toSummaryString());
                break;
            case Constants.LL2P_TYPE_IS_TEXT:
                uiManager.raiseToast("Unsupported Frame Type");
                break;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o.getClass().equals(BootLoader.class)) {
            ll1Daemon = LL1Daemon.getInstance();
            uiManager = UIManager.getInstance();
        }
    }

    /**
     * Build Echo Request Frame and send to LL1Daemon
     * @param frame LL2PFrame
     */
    public void answerEchoRequest(LL2PFrame frame){
        StringBuilder ll2pFrameString = new StringBuilder();
        ll2pFrameString.append(Utilities.padHexString(frame.getSourceAddress().toString(), 3)); // append destination address
        ll2pFrameString.append(Integer.toString(Constants.SOURCE_LL2P, 16)); // append source address
        ll2pFrameString.append(new LL2PTypeField(Constants.LL2P_TYPE_IS_ECHO_REPLY).toHexString()); // append type
        ll2pFrameString.append(frame.getPayload().toString()); // append payload
        ll2pFrameString.append("1234"); // append CRC
        LL2PFrame frameToSend = new LL2PFrame(ll2pFrameString.toString().getBytes());
        ll1Daemon.sendFrame(frameToSend);
        uiManager.raiseToast("Answered Echo Request");
    }

    /**
     * Build and sent text frame
     * @param ll2pAddress Integer
     */
    public void sendEchoRequest(Integer ll2pAddress){
        StringBuilder ll2pFrameString = new StringBuilder();
        ll2pFrameString.append(Utilities.padHexString(Utilities.intToAscii(ll2pAddress), 3)); // append destination address
        ll2pFrameString.append(Integer.toString(Constants.SOURCE_LL2P, 16)); // append source address
        ll2pFrameString.append(new LL2PTypeField(Constants.LL2P_TYPE_IS_ECHO_REQUEST).toHexString()); // append type
        ll2pFrameString.append("Echo Contents"); // append payload
        ll2pFrameString.append("1234"); // append CRC
        // -- Create and send frame
        LL2PFrame frameToSend = new LL2PFrame(ll2pFrameString.toString().getBytes());
        ll1Daemon.sendFrame(frameToSend);
    }

    /**
     * Wrap given datagram and send
     * @param datagram Datagram
     * @param ll2pAddress Integer
     */
    public void sendArpRequest(Datagram datagram, Integer ll2pAddress){
        LL2PFrame frame = new LL2PFrame(
                new LL2PAddressField(ll2pAddress, false),
                new LL2PAddressField(Constants.SOURCE_LL2P, true),
                new LL2PTypeField(Constants.LL2P_TYPE_IS_ARP_REQUEST),
                new DatagramPayloadField(datagram),
                new CRC("1234")
        );
        ll1Daemon.sendFrame(frame);
    }
}
