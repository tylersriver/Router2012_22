package sriver.w.tyler.router2017_22.UI;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import sriver.w.tyler.router2017_22.R;
import sriver.w.tyler.router2017_22.networks.datagram.LL2PFrame;
import sriver.w.tyler.router2017_22.networks.tablerecord.AdjacencyRecord;
import sriver.w.tyler.router2017_22.networks.tablerecord.TableRecord;
import sriver.w.tyler.router2017_22.support.BootLoader;
import sriver.w.tyler.router2017_22.support.FrameLogger;
import sriver.w.tyler.router2017_22.support.ParentActivity;
import sriver.w.tyler.router2017_22.support.Utilities;

/**
 * Created by tyler on 2/20/2017.
 *
 * This class handles the UI for the packet sniffer
 * on the left side of the UI
 */
public class SnifferUI implements Observer {

    // -- Fields
    // --------------------------------------------------------------
    private Activity parentActivity;
    private Context context;
    private FrameLogger frameLogger;
    private ListView frameListView;
    private TextView protocolBreakoutText;
    private TextView frameBytesTextView;
    private SnifferFrameListAdapter frameListAdapter;

    // -- Methods
    // --------------------------------------------------------------
    public SnifferUI() {
    }

    /**
     * Updates Sniffer when it is notified
     * @param o Observable
     * @param arg Object
     */
    @Override
    public void update(Observable o, Object arg) {
        if(o.getClass().equals(BootLoader.class)) {
            parentActivity = ParentActivity.getActivity();
            frameLogger = FrameLogger.getInstance();
            context = parentActivity.getBaseContext();
            frameLogger.addObserver(this);
            connectWidgets();
        } else if (o.getClass().equals(FrameLogger.class)){
            frameListAdapter.notifyDataSetChanged();
        }
    }

    /**
     * This function attaches the local variables to the UI objects
     * Note: it is called when the Bootloader updates the class
     */
    private void connectWidgets(){
        frameListView = (ListView) parentActivity.findViewById(R.id.packetsCaptured);
        frameListAdapter = new SnifferFrameListAdapter(context, frameLogger.getFrameList());
        frameListView.setAdapter(frameListAdapter);
        protocolBreakoutText = (TextView) parentActivity.findViewById(R.id.protocolExplanation);
        protocolBreakoutText.setMovementMethod(new ScrollingMovementMethod());
        frameBytesTextView = (TextView) parentActivity.findViewById(R.id.hexDump);
        frameListView.setOnItemClickListener(showThisFrame);
    }

    /**
     * Click listener for the Sniffer UI to handle a click on a particular frame
     */
    private AdapterView.OnItemClickListener showThisFrame = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            LL2PFrame frame = frameLogger.getFrameList().get(position);
            protocolBreakoutText.setText(frame.toProtocolExplanationString());
            frameBytesTextView.setText(HexDumpFormatter(frame));
        }
    };

    /**
     * Take the frame and format the contents for
     * the hex dump
     * @param frame LL2PFrame
     * @return String
     */
    @NonNull
    private String HexDumpFormatter(LL2PFrame frame){
        String hexString = frame.toHexString();
        String asciiString = frame.toString();

        StringBuilder formattedString = new StringBuilder();

        // -- Set index pointers
        int byteStart = 0;
        int byteEnd = byteStart+2;
        int characterStart = 0;
        int characterEnd = characterStart+1;

        while (byteStart < hexString.length()){
            formattedString.append(Utilities.padHexString(Integer.valueOf(characterStart).toString(), 2));
            formattedString.append("\t\t");

            // Build the Hex display portion
            for(int i = 0; i < 8; i++){
                if(byteStart < hexString.length()){
                    formattedString.append(hexString.substring(byteStart, byteEnd));
                    formattedString.append(" ");
                    byteStart += 2;
                    byteEnd += 2;
                } else {
                    formattedString.append("   ");
                }
            } // End For loop for bytes
            formattedString.append("\t\t");

            // Build the ASCII display portion
            for(int i = 0; i < 8; i++){
                if(characterEnd <= asciiString.length()){
                    formattedString.append(asciiString.substring(characterStart, characterEnd));
                    characterStart++;
                    characterEnd++;
                }
            } // End loop for ASCII
            formattedString.append("\n");
        } // End While
        return formattedString.toString();
    }

    // -- Classes
    // --------------------------------------------------------------
    /**
     * This class is a holder. It holds widgets (views) that make
     * up a single row in the sniffer top window.
     */
    private static class ViewHolder {
        TextView packetNumber;
        TextView packetSummaryString;
    }

    /**
     * SnifferFrameListAdapter is a private adapter to display numbered rows from a List
     * object which contains all frames transmitted or received.
     *
     * It is instantiated above and note that the constructor passes the context as well as
     * the frameList.
     */
    private class SnifferFrameListAdapter extends ArrayAdapter<LL2PFrame> {
        // this is the ArrayList that will be displayed in the rows on the ListView.
        private ArrayList<LL2PFrame> frameList;

        /**
         *  The constructor is passed the context and the arrayList.
         *  the arrayList is assigned to the local variable so its contents can be
         *  adapted to the listView.
         */
        public SnifferFrameListAdapter(Context context, ArrayList<LL2PFrame> frames) {
            super(context, 0, frames);
            frameList = frames;
        }

        /**
         * Here is where the work is performed to adapt a specific row in the arrayList to
         * a row on the screen.
         *
         * @param position    - position in the array we're working with
         * @param convertView - a row View that passed in – has a view to use or a null object
         * @param parent      - the main view that contains the rows.  Note that is is the ListView object.
         * @return View
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // First retrieve a frame object from the arrayList at the position we're working on
            LL2PFrame ll2PFrame = getItem(position);
            // declare a viewHolder - this simply is a single object to hold a two widgets
            ViewHolder viewHolder;

            /**
             * If convertView is null then we didn't get a recycled View, we have to create from scratch.
             * We do that here.
             */
            if (convertView == null) {
                // inflate the view defined in the layout xml file using an inflater we create here.
                LayoutInflater inflator = LayoutInflater.from(context);
                convertView = inflator.inflate(R.layout.sniffer_layout, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.packetNumber = (TextView) convertView.findViewById(R.id.snifferFrameNumberTextView);
                viewHolder.packetSummaryString = (TextView) convertView.findViewById(R.id.snifferItemTextView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.packetNumber.setText(Integer.toString(position));
            viewHolder.packetSummaryString.setText(frameList.get(position).toSummaryString());
            return convertView;
        }
    }
}
