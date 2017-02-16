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
 *
 * Class to create the dialog for adding an adjacency record
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

    /**
     * Empty Constructor
     */
    public AddAdjacencyDialog(){
    }

    /**
     * Override method to handle events on creation of the dialog view
     * @param inflater LayoutInflater
     * @param container ViewGroup
     * @param savedInstanceState Bundle
     * @return View
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // -- Create View
        View rootView = inflater.inflate(R.layout.add_adjacency_layout, container, false);
        getDialog().setTitle("Add Adjacency");
        getDialog().show();

        // -- Set Fields
        ipAddressEditText = (EditText) rootView.findViewById(R.id.ipAddressField);
        ll2pAddressEditText = (EditText) rootView.findViewById(R.id.ll2pAddressField);
        addAdjacencyButton = (Button) rootView.findViewById(R.id.addAdjacency);
        cancelButton = (Button) rootView.findViewById(R.id.cancelBtn);

        // -- Add Event Listeners
        addAdjacencyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdjacencyPairListener activity = (AdjacencyPairListener) getActivity();
                activity.onFinishedEditDialog(ipAddressEditText.getText().toString(),
                        ll2pAddressEditText.getText().toString());
                dismiss();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return rootView;
    }

    /**
     * Save the dialog state
     * @param savedInstanceState Bundle
     * @return Dialog
     */
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
