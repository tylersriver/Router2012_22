package sriver.w.tyler.router2017_22;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import sriver.w.tyler.router2017_22.support.BootLoader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BootLoader bootloader = new BootLoader(this);
    }
}
