package sriver.w.tyler.router2017_22;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import sriver.w.tyler.router2017_22.UI.AddAdjacencyDialog;
import sriver.w.tyler.router2017_22.UI.UIManager;
import sriver.w.tyler.router2017_22.networks.Constants;
import sriver.w.tyler.router2017_22.networks.daemon.LL1Daemon;
import sriver.w.tyler.router2017_22.support.BootLoader;

public class MainActivity extends AppCompatActivity implements AddAdjacencyDialog.AdjacencyPairListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BootLoader bootloader = new BootLoader(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.showIPAddress){
            UIManager.getInstance().raiseToast("Your IP address is "+ Constants.IP_ADDRESS);
        } else if(item.getItemId() == R.id.addAdjacencyDialog) {
            AddAdjacencyDialog dialog = new AddAdjacencyDialog();
            dialog.show(getFragmentManager(), "add_adjacency_dialog");
        } else if (item.getItemId() == R.id.showMessenger) {
            UIManager.getInstance().getMessenger().openMessengerWindow();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onFinishedEditDialog(String ipAddress, String ll2pAddress) {
        LL1Daemon.getInstance().addAdjacency(ll2pAddress, ipAddress);
    }
}
