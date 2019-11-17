package com.example.unseenmessagesfha.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.unseenmessagesfha.Configurations.RealmConfig;
import com.example.unseenmessagesfha.Models.Apps;
import com.example.unseenmessagesfha.R;

import io.realm.Realm;

public class SelectApps extends AppCompatActivity {

    private TextView txtView_Skip;
    private ImageView startButton;
    private Switch switchMessenger,switchInsta,switchSkype,switchViber,switchWhatsApp,switchImo;
    private RealmConfig config = new RealmConfig();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_apps);
        Realm.init(this);

        startButton = findViewById(R.id.imgView_Start);
        txtView_Skip = findViewById(R.id.txtView_Skip);
        switchMessenger = findViewById(R.id.switch_Messenger);
        switchImo = findViewById(R.id.switch_Imo);
        switchInsta = findViewById(R.id.switch_Instagram);
        switchSkype = findViewById(R.id.switch_Skype);
        switchViber = findViewById(R.id.switch_Viber);
        switchWhatsApp = findViewById(R.id.switch_WhatsApp);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadApps();
            }
        });
        txtView_Skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadSelectedApp();
            }
        });
    }

    private void LoadSelectedApp() {
        try (Realm db = Realm.getInstance(config.AppsConfig())) {
            db.beginTransaction();
            db.deleteAll();
            Apps obj = db.createObject(Apps.class);
            obj.setWhatsApp(0);
            obj.setInstagram(0);
            obj.setMessenger(0);
            obj.setSkype(0);
            obj.setImo(0);
            obj.setViber(0);
            db.commitTransaction();
            Intent intent = new Intent(this, SelectedApps.class);
            startActivity(intent);
        }
    }

    private void LoadApps() {
        try (Realm db = Realm.getInstance(config.AppsConfig())) {
            db.beginTransaction();
            db.deleteAll();
            Apps obj = db.createObject(Apps.class);
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
            db.commitTransaction();
            finish();
            Intent intent = new Intent(this, SelectedApps.class);
            startActivity(intent);
        }
    }
}
