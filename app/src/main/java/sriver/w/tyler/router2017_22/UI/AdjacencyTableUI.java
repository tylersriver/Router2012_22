package sriver.w.tyler.router2017_22.UI;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;

import java.util.Observer;

import sriver.w.tyler.router2017_22.networks.Constants;
import sriver.w.tyler.router2017_22.networks.daemon.LL1Daemon;
import sriver.w.tyler.router2017_22.networks.daemon.LL2PDaemon;
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

            LL2PDaemon.getInstance().sendEchoRequest(record.getLl2pAddress());
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
