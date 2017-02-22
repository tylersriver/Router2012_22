package sriver.w.tyler.router2017_22.UI;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;

import java.util.Observer;

import sriver.w.tyler.router2017_22.networks.Constants;
import sriver.w.tyler.router2017_22.networks.daemon.LL1Daemon;
import sriver.w.tyler.router2017_22.networks.datagram.LL2PFrame;
import sriver.w.tyler.router2017_22.networks.table.Table;
import sriver.w.tyler.router2017_22.networks.tablerecord.AdjacencyRecord;
import sriver.w.tyler.router2017_22.support.Utilities;

/**
 * Created by tyler on 2/19/2017.
 *
 * UI Class for the AdjacencyTable
 */
public class AdjacencyTableUI extends SingleTableUI implements Observer {


    // -- Fields
    // --------------------------------------------------------------
    private LL1Daemon ll1Daemon;

    // -- Methods
    // --------------------------------------------------------------
    /**
     * Constructor
     * @param parent Activity
     * @param viewID int
     * @param tableInterface TableInterface
     * @param ll1Daemon LL1Daemon
     */
    public AdjacencyTableUI(Activity parent, int viewID, Table tableInterface, LL1Daemon ll1Daemon) {
        super(parent, viewID, tableInterface);
        this.ll1Daemon = ll1Daemon;
        // -- Set click listeners
        tableListViewWidget.setOnItemClickListener(sendEchoRequest);
        tableListViewWidget.setOnItemLongClickListener(removeAdjacency);
    }

    /**
     * Method to handle click event
     */
    private AdapterView.OnItemClickListener sendEchoRequest = new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            AdjacencyRecord record = (AdjacencyRecord) tableList.get(position);

            // -- Build String to make byte array for LL2P constructor
            StringBuilder ll2pFrameString = new StringBuilder();
            ll2pFrameString.append(Utilities.padHexString(Utilities.intToAscii(record.getLl2pAddress()), 3)); // append destination address
            ll2pFrameString.append(Integer.toString(Constants.SOURCE_LL2P, 16)); // append source address
            ll2pFrameString.append("8004"); // append type
            ll2pFrameString.append("Echo Contents"); // append payload
            ll2pFrameString.append("1234"); // append CRC
            // -- Create and send frame
            LL2PFrame frameToSend = new LL2PFrame(ll2pFrameString.toString().getBytes());
            ll1Daemon.sendFrame(frameToSend);
            UIManager.getInstance().raiseToast("Echo Request Sent");
        }
    };

    /**
     * Method to handle long click on records
     */
    private AdapterView.OnItemLongClickListener removeAdjacency = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            ll1Daemon.removeAdjacency((AdjacencyRecord) tableList.get(position));
            updateView();
            return false;
        }
    };
}
