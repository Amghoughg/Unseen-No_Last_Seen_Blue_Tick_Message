package com.example.unseenmessagesfha.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.unseenmessagesfha.R;

public class PermissionActivity extends AppCompatActivity {
    private ImageView start;
    private final String ENABLED_NOTIFICATION_LISTENERS = "enabled_notification_listeners";
    private static final String ACTION_NOTIFICATION_LISTENER_SETTINGS = "android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS";
    private TextView companyName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        start = findViewById(R.id.imgView_Start);
        companyName = findViewById(R.id.textView_CompanyName);
        companyName.setMovementMethod(LinkMovementMethod.getInstance());

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPermission();
            }
        });


    }

    private void getPermission() {
        if(!isNotificationServiceEnabled()){
            AlertDialog enableNotificationListenerAlertDialog = buildNotificationServiceAlertDialog();
            enableNotificationListenerAlertDialog.show();
            enableNotificationListenerAlertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.YesButton));
            enableNotificationListenerAlertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.redish));
        }
        else {
            finish();
            Intent intent = new Intent(this,SelectApps.class);
            startActivity(intent);
        }
    }

    //Check if service is Enabled or not
    private boolean isNotificationServiceEnabled(){
        String pkgName = getPackageName();
        final String flat = Settings.Secure.getString(getContentResolver(),
                                                      ENABLED_NOTIFICATION_LISTENERS);
        if (!TextUtils.isEmpty(flat)) {
            final String[] names = flat.split(":");
            for (String name : names) {
                final ComponentName cn = ComponentName.unflattenFromString(name);
                if (cn != null) {
                    if (TextUtils.equals(pkgName, cn.getPackageName())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //Dialogue to get Notification Permission
    private AlertDialog buildNotificationServiceAlertDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(R.string.notification_listener_service);
        alertDialogBuilder.setMessage(R.string.notification_listener_service_explanation);
        alertDialogBuilder.setPositiveButton(R.string.yes,
                                             new DialogInterface.OnClickListener() {
                                                 public void onClick(DialogInterface dialog, int id) {
                                                     startActivity(new Intent(ACTION_NOTIFICATION_LISTENER_SETTINGS));
                                                 }
                                             });
        alertDialogBuilder.setNegativeButton(R.string.no,
                                             new DialogInterface.OnClickListener() {
                                                 public void onClick(DialogInterface dialog, int id) {
                                                     // If you choose to not enable the notification listener
                                                     // the app. will not work as expected
                                                 }
                                             });
        return(alertDialogBuilder.create());
    }
}
