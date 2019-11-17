package com.example.unseenmessagesfha.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;

import com.example.unseenmessagesfha.Configurations.RealmConfig;
import com.example.unseenmessagesfha.Models.Apps;
import com.example.unseenmessagesfha.R;

import io.realm.Realm;

public class AddApps extends AppCompatActivity {

    private ImageView btnApply;
    private RealmConfig config = new RealmConfig();
    private Switch switchMessenger,switchInsta,switchSkype,switchViber,switchWhatsApp,switchImo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_apps);
        Realm.init(this);

        switchMessenger = findViewById(R.id.switch_Messenger2);
        switchImo = findViewById(R.id.switch_Imo2);
        switchInsta = findViewById(R.id.switch_Instagram2);
        switchSkype = findViewById(R.id.switch_Skype2);
        switchViber = findViewById(R.id.switch_Viber2);
        switchWhatsApp = findViewById(R.id.switch_WhatsApp2);
        btnApply = findViewById(R.id.btn_Apply);

        LoadSwitches();
        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewApps();
            }
        });

    }

    private void AddNewApps() {
        Realm db = Realm.getInstance(config.AppsConfig());
        db.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.deleteAll();
                Apps obj = realm.createObject(Apps.class);
                obj.setWhatsApp(0);
                obj.setInstagram(0);
                obj.setMessenger(0);
                obj.setSkype(0);
                obj.setImo(0);
                obj.setViber(0);
                if (switchMessenger.isChecked()) {
                    obj.setMessenger(10);
                }
                if (switchInsta.isChecked()) {
                    obj.setInstagram(10);
                }
                if (switchWhatsApp.isChecked()) {
                    obj.setWhatsApp(10);
                }
                if (switchSkype.isChecked()) {
                    obj.setSkype(10);
                }
                if (switchImo.isChecked()) {
                    obj.setImo(10);
                }
                if (switchViber.isChecked()) {
                    obj.setViber(10);
                }
            }
        });
        this.finish();
        Intent intent = new Intent(this,SelectedApps.class);
        startActivity(intent);
    }

    private void LoadSwitches() {
        Realm db =Realm.getInstance(config.AppsConfig());
        Apps obj = db.where(Apps.class).findFirst();
        if(obj.getImo() == 10)
        {
            switchImo.setChecked(true);
        }
        if(obj.getInstagram() == 10)
        {
            switchInsta.setChecked(true);
        }
        if(obj.getViber() == 10)
        {
            switchViber.setChecked(true);
        }
        if(obj.getMessenger() == 10)
        {
            switchMessenger.setChecked(true);
        }
        if(obj.getSkype() == 10)
        {
            switchSkype.setChecked(true);
        }
        if(obj.getWhatsApp() == 10)
        {
            switchWhatsApp.setChecked(true);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.nothing, R.anim.top_to_bottom);
    }
}
