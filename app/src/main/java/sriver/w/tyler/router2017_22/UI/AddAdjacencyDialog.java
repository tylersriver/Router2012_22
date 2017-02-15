package sriver.w.tyler.router2017_22.UI;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import sriver.w.tyler.router2017_22.R;

/**
 * Created by tyler on 2/14/2017.
 */

public class AddAdjacencyDialog extends DialogFragment {

    // -- Fields
    // --------------------------------------------------------------
    private EditText ipAddressEditText;
    private EditText ll2pAddressEditText;
    private Button addAdjacencyButton;
    private Button cancelButton;

    // -- Methods
    // --------------------------------------------------------------
    public AddAdjacencyDialog(){
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // -- Create View
        View rootView = inflater.inflate(R.layout.add_adjacency_layout, container, false);
        getDialog().setTitle("Add Adjacency");

        // -- Set Fields
        ipAddressEditText = (EditText) rootView.findViewById(R.id.ipAddressField);
        ll2pAddressEditText = (EditText) rootView.findViewById(R.id.ll2pAddressField);
        addAdjacencyButton = (Button) rootView.findViewById(R.id.addAdjacency);
        cancelButton = (Button) rootView.findViewById(R.id.cancelBtn);

        return rootView;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    // -- Classes
    // --------------------------------------------------------------
    public interface AdjacencyPairListener{
        void onFinishedEditDialog(String ipAddress, String ll2pAddress);
    }
}
