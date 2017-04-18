package sriver.w.tyler.router2017_22.networks.daemon;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import sriver.w.tyler.router2017_22.UI.TableUI;
import sriver.w.tyler.router2017_22.UI.UIManager;
import sriver.w.tyler.router2017_22.networks.Constants;
import sriver.w.tyler.router2017_22.networks.tablerecord.ARPRecord;
import sriver.w.tyler.router2017_22.support.BootLoader;

/**
 * Created by tyler.w.sriver on 3/9/17.
 *
 * Scheduler Class
 */
public class Scheduler implements Observer{

    // -- Fields
    // --------------------------------------------------------------
    private static Scheduler ourInstance = new Scheduler();
    private ScheduledThreadPoolExecutor threadManager;
    private ARPDaemon arpDaemon;
    private LRPDaemon lrpDaemon;
    private TableUI tableUI;

    // -- Methods
    // --------------------------------------------------------------

    /**
     * return the singleton instance
     * @return Scheduler
     */
    public static Scheduler getInstance() {
        return ourInstance;
    }

    /**
     * Empty constructor
     */
    private Scheduler() {
    }

    /**
     * Observer update method to update when things
     * happen
     * @param o Observable
     * @param arg Object
     */
    @Override
    public void update(Observable o, Object arg) {
        if(o.getClass().equals(BootLoader.class)) {

            // -- Get Singleton References
            arpDaemon = ARPDaemon.getInstance();
            lrpDaemon = LRPDaemon.getInstance();
            tableUI = UIManager.getInstance().getTableUI();

            threadManager = new ScheduledThreadPoolExecutor(Constants.THREAD_COUNT);

            threadManager.scheduleAtFixedRate(tableUI,
                    Constants.ROUTER_BOOT_TIME,
                    Constants.UI_UPDATE_INTERVAL,
                    TimeUnit.SECONDS); // Thread for TAble UI

            threadManager.scheduleAtFixedRate(arpDaemon,
                    Constants.ROUTER_BOOT_TIME,
                    Constants.ARP_DAEMON_UPDATE_INTERVAL,
                    TimeUnit.SECONDS); // Thread for ARPDaemon

            threadManager.scheduleAtFixedRate(lrpDaemon,
                    Constants.ROUTER_BOOT_TIME,
                    Constants.LRP_UPDATE_INTERVAL,
                    TimeUnit.SECONDS);

        }
    }
}
