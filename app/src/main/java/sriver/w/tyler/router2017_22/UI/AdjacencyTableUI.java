package sriver.w.tyler.router2017_22.UI;

import android.app.Activity;

import java.util.Observer;

import sriver.w.tyler.router2017_22.networks.daemon.LL1Daemon;
import sriver.w.tyler.router2017_22.networks.table.Table;
import sriver.w.tyler.router2017_22.networks.table.TableInterface;

/**
 * Created by tyler on 2/19/2017.
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
     * @param integer int
     * @param tableInterface TableInterface
     * @param ll1Daemon LL1Daemon
     */
    public AdjacencyTableUI(Activity parent, int integer, TableInterface tableInterface, LL1Daemon ll1Daemon) {
        this.ll1Daemon = ll1Daemon;
    }
}
