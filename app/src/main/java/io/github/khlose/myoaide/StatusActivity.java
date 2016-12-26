package io.github.khlose.myoaide;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

public class StatusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Switch lock_switch = (Switch) findViewById(R.id.lock_switch);

        lock_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String statusString;
                if(isChecked){
                    statusString = "Your device is now listening to gesture control";
                }
                else{
                    statusString = "Your device is now ignoring gesture control";
                }
                Snackbar.make(buttonView, statusString, Snackbar.LENGTH_LONG)
                        .setAction("Action",null).show();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_status, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_bluetooth:
                startActivity(pairBluetoothDevice());
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return false;
        }

    }

    private Intent pairBluetoothDevice(){
        Intent bluetooth_launcher_intent = new Intent(Settings.ACTION_BLUETOOTH_SETTINGS);
        return bluetooth_launcher_intent;
    }
}
