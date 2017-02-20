package sriver.w.tyler.router2017_22.UI;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import sriver.w.tyler.router2017_22.R;
import sriver.w.tyler.router2017_22.networks.table.Table;
import sriver.w.tyler.router2017_22.networks.tablerecord.TableRecord;
import sriver.w.tyler.router2017_22.support.ParentActivity;

/**
 * Created by tyler.w.sriver on 2/16/17.
 *
 * This class is used to observe the underlying table
 * in the model and refreshes the screen any time the table
 * contents are changed
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

    /**
     * Constructor to setup class
     * @param table Table
     */
    public SingleTableUI(Activity parentActivity, int viewID, Table table){
        this.parentActivity = parentActivity;
        tableToDisplay = table;
        tableListViewWidget = (ListView) parentActivity.findViewById(viewID);
        tableListViewWidget.setAdapter(arrayAdapter);
        table.addObserver(this);
        tableList = table.getTableAsArrayList();
        arrayAdapter = new ArrayAdapter(parentActivity.getBaseContext(), android.R.layout.simple_list_item_1,
                tableToDisplay.getTableAsArrayList());
    }

    /**
     * Update Display when needed
     */
    public void updateView(){
        // Force all our work here to be on the UI thread!
        parentActivity.runOnUiThread(new Runnable() {
            @Override // this is a mini-Runnable class’s run method!
            public void run() {
                // notify the OS that the dataset has changed. It will update screen!
                arrayAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        updateView();
    }
}
