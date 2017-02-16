package sriver.w.tyler.router2017_22.UI;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import sriver.w.tyler.router2017_22.networks.table.Table;
import sriver.w.tyler.router2017_22.networks.tablerecord.TableRecord;

/**
 * Created by tyler.w.sriver on 2/16/17.
 */

public class SingleTableUI implements Observer {

    // -- Fields
    // --------------------------------------------------------------
    protected Activity parentActivity;
    protected Table tableToDisplay;
    protected List<TableRecord> tableList;
    protected ListView tableListViewWidget;
    private ArrayAdapter arrayAdapter;

    // -- Methods
    // --------------------------------------------------------------
    public SingleTableUI(){
        // TODO: 2/16/17 Fill in
    }

    public void updateView(){
        // TODO: 2/16/17 Fill in
    }

    @Override
    public void update(Observable o, Object arg) {
        // TODO: 2/16/17 Fill in
    }
}
