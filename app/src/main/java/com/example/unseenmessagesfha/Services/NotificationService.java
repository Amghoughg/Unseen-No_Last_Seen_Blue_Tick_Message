package com.example.unseenmessagesfha.Services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

import com.example.unseenmessagesfha.Configurations.RealmConfig;
import com.example.unseenmessagesfha.Models.AllChat;
import com.example.unseenmessagesfha.Models.Messages;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class NotificationService extends NotificationListenerService {

    private final static String MessengerPackageName = "com.facebook.orca";
    private final static String WhatsappPackageName = "com.whatsapp";
    private final static String InstagramPackageName = "com.instagram.android";
    private final static String SkypePackageName = "com.skype.raider";
    private final static String IMOPackageName = "com.imo.android.imoim";
    private final static String ViberPackageName = "com.viber.voip";
    private final static String NOTIFICATION_CHANNELID = "NotificationService_Channel";
    private final static int NotificationID = 512;
    private RealmConfig obj = new RealmConfig();
    private String checkDuplication;

    public NotificationService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(getApplicationContext());
        checkDuplication = "asdasdsc";
        createNotificationChannelID();
        Intent notificationIntent = new Intent(this, NotificationService.class);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(this, 0, notificationIntent, 0);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification notification =
                    new Notification.Builder(this, NOTIFICATION_CHANNELID)
                            .setContentTitle("Unseen Messages")
                            .setContentText("App service is running to get messages")
                            .setContentIntent(pendingIntent)
                            .build();
            startForeground(NotificationID, notification);
        }
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        super.onNotificationPosted(sbn);
        switch (checkPackageName(sbn))
        {
            case 1: {
                String Checker = sbn.getNotification().extras.getString("android.title");
                if (Checker != null) {
                    if (Checker.equals("Chat heads active") || Checker.equals("Messenger")) {
                        return;
                    }
                    else {
                        Realm db = Realm.getInstance(obj.MessengerConfig());
                        try {
                            db.beginTransaction();
                            Messages obj = db.createObject(Messages.class);
                            obj.setTitle(sbn.getNotification().extras.getString("android.title"));
                            obj.setText(sbn.getNotification().extras.getCharSequence("android.text").toString());
                            SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
                            SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
                            SimpleDateFormat sdf3 = new SimpleDateFormat("dd yyyy HH:mm:ss");
                            obj.setDayMonth(sdf1.format(new Date()));
                            obj.setTime(sdf2.format(new Date()));
                            obj.setAppName(MessengerPackageName);
                            obj.setTimeDate(sdf3.format(new Date()));
                            Bitmap bitmap = sbn.getNotification().largeIcon;
                            if(bitmap != null)
                            {
                                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
                                obj.setBitmap(stream.toByteArray());
                            }
                            db.commitTransaction();
                            SaveAllChat(sbn.getPackageName(),sbn,sbn.getNotification().extras.getString("android.title"));
                        } finally {

                        }
                    }
                }
                break;
            }
            case 2: {
                String Checker = sbn.getNotification().extras.getString("android.title");
                String Text;
                CharSequence abc = sbn.getNotification().extras.getCharSequence("android.text");
                if(abc != null)
                {
                    Text = abc.toString();
                }
                else {
                    Text = sbn.getNotification().extras.getString("android.text");
                }
                if(Text == null)
                {
                    Text = "dtujhgn45";
                }
                if (Checker != null && Text != null) {
                    if (Checker.equals("WhatsApp") || Checker.equals("WhatsApp Web")
                            || Checker.contains("Missed voice call") || Checker.contains("Missed video call") || Checker.contains("missed voice call")
                             || Checker.contains("missed video call") || Checker.contains("missed call") || Checker.contains("Missed call")) {
                        return;
                    }
                    else {
                        if (Text != null) {
                            if(checkDuplication != null && checkDuplication.equals(Text))
                            {
                                return;
                            }
                            else {
                                if (Text.toLowerCase().contains("new messages") || Text.contains("Ringing") || Text.contains("Calling")) {
                                    return;
                                }
                                else {
                                    Realm db = Realm.getInstance(obj.WhatsappConfig());
                                    Messages lastTitle = db.where(Messages.class).equalTo("Title", Checker).sort("TimeDate", Sort.DESCENDING).findFirst();
                                    if (lastTitle != null && lastTitle.getText()!= null && lastTitle.getText().equals(Text)) {
                                        return;
                                    }
                                    else {
                                        try {
                                            db.beginTransaction();
                                            Messages obj = db.createObject(Messages.class);
                                            obj.setTitle(sbn.getNotification().extras.getString("android.title"));
                                            obj.setText(sbn.getNotification().extras.getString("android.text"));
                                            SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
                                            SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
                                            SimpleDateFormat sdf3 = new SimpleDateFormat("dd yyyy HH:mm:ss");
                                            obj.setDayMonth(sdf1.format(new Date()));
                                            obj.setAppName(WhatsappPackageName);
                                            obj.setTime(sdf2.format(new Date()));
                                            obj.setTimeDate(sdf3.format(new Date()));
                                            Bitmap bitmap = sbn.getNotification().largeIcon;
                                            if (bitmap != null) {
                                                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                                                obj.setBitmap(stream.toByteArray());
                                            }
                                            /*else {
                                                return;
                                            }*/
                                            checkDuplication = obj.getText();

                                            db.commitTransaction();
                                            String result = Checker;
                                            if (Checker.contains("(")) {
                                                result = Checker.split(Pattern.quote("("))[0];
                                            }
                                            else if (Checker.contains(":")) {
                                                result = Checker.split(":")[0];
                                            }
                                            SaveAllChat(sbn.getPackageName(), sbn, result.trim());
                                        } finally {
                                            //db.close();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                break;

            }
            case 3: {
                String Checker = sbn.getNotification().extras.getString("android.title");
                String Text = sbn.getNotification().extras.getString("android.text");
                if(Text == null)
                {
                    Text = "";
                }
                if (Checker != null) {
                    if (Checker.equals("Instagram")) {
                        return;
                    }
                    else {
                        Realm db = Realm.getInstance(obj.InstagramConfig());
                        Messages lastTitle = db.where(Messages.class).equalTo("Title",Checker).sort("TimeDate", Sort.DESCENDING).findFirst();
                        if(lastTitle != null && lastTitle.getText().equals(Text))
                        {
                            return;
                        }else {
                            try {
                                db.beginTransaction();
                                Messages obj = db.createObject(Messages.class);
                                obj.setTitle(sbn.getNotification().extras.getString("android.title"));
                                obj.setText(sbn.getNotification().extras.getString("android.text"));
                                SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
                                SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
                                SimpleDateFormat sdf3 = new SimpleDateFormat("dd yyyy HH:mm:ss");
                                obj.setDayMonth(sdf1.format(new Date()));
                                obj.setTime(sdf2.format(new Date()));
                                obj.setTimeDate(sdf3.format(new Date()));
                                obj.setAppName(InstagramPackageName);
                                Bitmap bitmap = sbn.getNotification().largeIcon;
                                if (bitmap != null) {
                                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                                    obj.setBitmap(stream.toByteArray());
                                }
                                db.commitTransaction();
                                SaveAllChat(sbn.getPackageName(), sbn, sbn.getNotification().extras.getString("android.title"));
                            } finally {
                                //db.close();
                            }
                        }
                    }
                }
                break;
            }
            case 4: {
                String Checker = sbn.getNotification().extras.getString("android.title");
                String Text = sbn.getNotification().extras.getString("android.text");
                if(Text == null)
                {
                    Text = "";
                }
                if (Checker != null) {
                    if (Checker.equals("Skype") || Checker.toLowerCase().contains("new conversation")) {

                    }
                    else {
                        String Title = Checker;
                        if(Checker.contains("(") || Checker.contains(":")) {
                            String str1 = Checker.split(Pattern.quote("("))[0].trim();
                            String str2 = Checker.substring(Checker.lastIndexOf(":") + 1).trim();
                            if(str1.equals(str2))
                            {
                                Title = str1;
                            }
                        }
                        Realm db = Realm.getInstance(obj.SkypeConfig());
                        Messages lastTitle = db.where(Messages.class).equalTo("Title",Title).sort("TimeDate", Sort.DESCENDING).findFirst();
                        if(lastTitle != null && lastTitle.getText().equals(Text))
                        {
                            return;
                        }
                        else {
                            try {
                                db.beginTransaction();
                                Messages obj = db.createObject(Messages.class);
                                obj.setTitle(Title);
                                obj.setText(sbn.getNotification().extras.getString("android.text"));
                                SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
                                SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
                                SimpleDateFormat sdf3 = new SimpleDateFormat("dd yyyy HH:mm:ss");
                                obj.setDayMonth(sdf1.format(new Date()));
                                obj.setTime(sdf2.format(new Date()));
                                obj.setTimeDate(sdf3.format(new Date()));
                                obj.setAppName(SkypePackageName);
                                Bitmap bitmap = sbn.getNotification().largeIcon;
                                if (bitmap != null) {
                                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                                    obj.setBitmap(stream.toByteArray());
                                }
                                db.commitTransaction();
                                String result = Checker;
                                if (Checker.contains("(")) {
                                    result = Checker.split(Pattern.quote("("))[0];
                                }
                                SaveAllChat(sbn.getPackageName(), sbn, result.trim());
                            } finally {
                                //db.close();
                            }
                        }
                    }
                }
                break;
            }
            case 5: {
                String Checker = sbn.getNotification().extras.getString("android.title");
                String Text = sbn.getNotification().extras.getString("android.text");
                if(Text == null)
                {
                    Text = "";
                }
                if (Checker != null) {
                    if (Checker.equals("imo") || Checker.equals("Ongoing imo call") || Checker.equals("Ongoing imo video call")) {

                    }
                    else {
                        Realm db = Realm.getInstance(obj.ImoConfig());
                        Messages lastTitle = db.where(Messages.class).equalTo("Title",Checker).sort("TimeDate", Sort.DESCENDING).findFirst();
                        if(lastTitle != null && lastTitle.getText().equals(Text))
                        {
                            return;
                        }
                        else {
                            try {
                                db.beginTransaction();
                                Messages obj = db.createObject(Messages.class);
                                obj.setTitle(sbn.getNotification().extras.getString("android.title"));
                                obj.setText(sbn.getNotification().extras.getString("android.text"));
                                SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
                                SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
                                SimpleDateFormat sdf3 = new SimpleDateFormat("dd yyyy HH:mm:ss");
                                obj.setDayMonth(sdf1.format(new Date()));
                                obj.setTime(sdf2.format(new Date()));
                                obj.setAppName(IMOPackageName);
                                obj.setTimeDate(sdf3.format(new Date()));
                                Bitmap bitmap = sbn.getNotification().largeIcon;
                                if (bitmap != null) {
                                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                                    obj.setBitmap(stream.toByteArray());
                                }
                                db.commitTransaction();
                                SaveAllChat(sbn.getPackageName(), sbn, Checker);
                            } finally {
                                //db.close();
                            }
                        }
                    }
                }
                break;
            }
            case 6: {
                String Text;
                String Checker = sbn.getNotification().extras.getString("android.title");
                CharSequence abc = sbn.getNotification().extras.getCharSequence("android.text");
                if(abc != null)
                {
                    Text = abc.toString();
                }
                else {
                    Text = sbn.getNotification().extras.getString("android.text");
                }
                if (Checker != null) {
                    if (Checker.equals("Viber") || Checker.equals("Your stickers are ready") || Checker.contains("Missed call")
                            || Checker.contains("Missed video call") || Checker.contains("unread messages")){

                    }
                    else {
                        if(Text == null)
                        {
                            Text = "";
                        }
                        Realm db = Realm.getInstance(obj.ViberConfig());
                        Messages lastTitle = db.where(Messages.class).equalTo("Title",Checker).sort("TimeDate", Sort.DESCENDING).findFirst();
                        if(lastTitle != null && lastTitle.getText().equals(Text))
                        {
                            return;
                        }
                        else {
                            try {
                                db.beginTransaction();
                                Messages obj = db.createObject(Messages.class);
                                obj.setTitle(sbn.getNotification().extras.getString("android.title"));
                                obj.setText(sbn.getNotification().extras.getCharSequence("android.text").toString());
                                SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
                                SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
                                SimpleDateFormat sdf3 = new SimpleDateFormat("dd yyyy HH:mm:ss");
                                obj.setDayMonth(sdf1.format(new Date()));
                                obj.setTime(sdf2.format(new Date()));
                                obj.setAppName(ViberPackageName);
                                obj.setTimeDate(sdf3.format(new Date()));
                                Bitmap bitmap = sbn.getNotification().largeIcon;
                                if (bitmap != null) {
                                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                                    obj.setBitmap(stream.toByteArray());
                                }
                                db.commitTransaction();
                                /*String result = Checker;
                                if (Checker.contains(":")) {
                                    result = Checker.split(Pattern.quote(":"))[0];
                                }*/
                                SaveAllChat(sbn.getPackageName(), sbn, Checker);
                            } finally {
                                //db.close();
                            }
                        }
                    }
                }
                break;
            }
            default:
                break;
        }
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        super.onNotificationRemoved(sbn);
    }

    public void SaveAllChat(String AppName,final StatusBarNotification sbn,final String Title)
    {
        RealmConfig config = new RealmConfig();
        Realm db = Realm.getInstance(config.AllChat());
        switch (AppName)
        {
            case WhatsappPackageName:
            {
                final AllChat results = db.where(AllChat.class).equalTo("Title", Title).equalTo("AppName", WhatsappPackageName).findFirst();
                if(results == null) {
                    db.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            AllChat people = realm.createObject(AllChat.class);
                            people.setAppName(WhatsappPackageName);
                            people.setTitle(Title);
                            SimpleDateFormat sdf2 = new SimpleDateFormat("dd yyyy HH:mm:ss");
                            people.setDateTime(sdf2.format(new Date()));
                            people.setCounter(1);
                            Bitmap bitmap = sbn.getNotification().largeIcon;
                            if (bitmap != null) {
                                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                                people.setAvatar(stream.toByteArray());
                            }
                        }
                    });
                }
                else {
                    db.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            results.setCounter(results.getCounter() + 1);
                            SimpleDateFormat sdf2 = new SimpleDateFormat("dd yyyy HH:mm:ss");
                            results.setDateTime(sdf2.format(new Date()));
                            results.setTitle(Title);
                            Bitmap bitmap = sbn.getNotification().largeIcon;
                            if (bitmap != null) {
                                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                                results.setAvatar(stream.toByteArray());
                            }
                        }
                    });
                }
                break;
            }
            case MessengerPackageName:
            {
                final AllChat results = db.where(AllChat.class).equalTo("Title",Title).equalTo("AppName", MessengerPackageName).findFirst();
                if(results == null) {
                    db.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            AllChat people = realm.createObject(AllChat.class);
                            people.setCounter(1);
                            people.setAppName(MessengerPackageName);
                            people.setTitle(Title);
                            SimpleDateFormat sdf2 = new SimpleDateFormat("dd yyyy HH:mm:ss");
                            people.setDateTime(sdf2.format(new Date()));
                            Bitmap bitmap = sbn.getNotification().largeIcon;
                            if (bitmap != null) {
                                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                                people.setAvatar(stream.toByteArray());
                            }
                        }
                    });
                }else {
                    db.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            SimpleDateFormat sdf2 = new SimpleDateFormat("dd yyyy HH:mm:ss");
                            results.setDateTime(sdf2.format(new Date()));
                            results.setTitle(Title);
                            results.setCounter(results.getCounter() + 1);
                            Bitmap bitmap = sbn.getNotification().largeIcon;
                            if (bitmap != null) {
                                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                                results.setAvatar(stream.toByteArray());
                            }
                        }
                    });
                }break;
            }
            case InstagramPackageName:
            {
                final AllChat results = db.where(AllChat.class).equalTo("Title", Title).equalTo("AppName", InstagramPackageName).findFirst();
                if(results == null) {
                    db.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            AllChat people = realm.createObject(AllChat.class);
                            people.setAppName(InstagramPackageName);
                            people.setTitle(Title);
                            SimpleDateFormat sdf2 = new SimpleDateFormat("dd yyyy HH:mm:ss");
                            people.setDateTime(sdf2.format(new Date()));
                            people.setCounter(1);
                            Bitmap bitmap = sbn.getNotification().largeIcon;
                            if (bitmap != null) {
                                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                                people.setAvatar(stream.toByteArray());
                            }
                        }
                    });
                }else {
                    db.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            SimpleDateFormat sdf2 = new SimpleDateFormat("dd yyyy HH:mm:ss");
                            results.setDateTime(sdf2.format(new Date()));
                            results.setTitle(Title);
                            results.setCounter(results.getCounter() + 1);
                            Bitmap bitmap = sbn.getNotification().largeIcon;
                            if (bitmap != null) {
                                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                                results.setAvatar(stream.toByteArray());
                            }
                        }
                    });
                }
                break;
            }
            case IMOPackageName:
            {
                final AllChat results = db.where(AllChat.class).equalTo("Title",Title).equalTo("AppName", IMOPackageName).findFirst();
                if(results == null) {
                    db.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            AllChat people = realm.createObject(AllChat.class);
                            people.setAppName(IMOPackageName);
                            people.setTitle(Title);
                            SimpleDateFormat sdf2 = new SimpleDateFormat("dd yyyy HH:mm:ss");
                            people.setDateTime(sdf2.format(new Date()));
                            people.setCounter(1);
                            Bitmap bitmap = sbn.getNotification().largeIcon;
                            if (bitmap != null) {
                                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                                people.setAvatar(stream.toByteArray());
                            }
                        }
                    });
                }else {
                    db.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            SimpleDateFormat sdf2 = new SimpleDateFormat("dd yyyy HH:mm:ss");
                            results.setDateTime(sdf2.format(new Date()));
                            results.setTitle(Title);
                            results.setCounter(results.getCounter() + 1);
                            Bitmap bitmap = sbn.getNotification().largeIcon;
                            if (bitmap != null) {
                                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                                results.setAvatar(stream.toByteArray());
                            }
                        }
                    });
                }
                break;
            }
            case SkypePackageName:
            {
                final AllChat results = db.where(AllChat.class).equalTo("Title",Title).equalTo("AppName",SkypePackageName).findFirst();
                if(results == null) {
                    db.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            AllChat people = realm.createObject(AllChat.class);
                            people.setAppName(SkypePackageName);
                            people.setTitle(Title);
                            SimpleDateFormat sdf2 = new SimpleDateFormat("dd yyyy HH:mm:ss");
                            people.setDateTime(sdf2.format(new Date()));
                            people.setCounter(1);
                            Bitmap bitmap = sbn.getNotification().largeIcon;
                            if (bitmap != null) {
                                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                                people.setAvatar(stream.toByteArray());
                            }
                        }
                    });
                }else {
                    db.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            SimpleDateFormat sdf2 = new SimpleDateFormat("dd yyyy HH:mm:ss");
                            results.setDateTime(sdf2.format(new Date()));
                            results.setTitle(Title);
                            results.setCounter(results.getCounter() + 1);
                            Bitmap bitmap = sbn.getNotification().largeIcon;
                            if (bitmap != null) {
                                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                                results.setAvatar(stream.toByteArray());
                            }
                        }
                    });
                }
                break;
            }
            case ViberPackageName:
            {
                final AllChat results = db.where(AllChat.class).equalTo("Title",Title).equalTo("AppName",ViberPackageName).findFirst();
                if(results == null) {
                    db.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            AllChat people = realm.createObject(AllChat.class);
                            people.setAppName(ViberPackageName);
                            people.setTitle(Title);
                            SimpleDateFormat sdf2 = new SimpleDateFormat("dd yyyy HH:mm:ss");
                            people.setDateTime(sdf2.format(new Date()));
                            people.setCounter(1);
                            Bitmap bitmap = sbn.getNotification().largeIcon;
                            if (bitmap != null) {
                                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                                people.setAvatar(stream.toByteArray());
                            }
                        }
                    });
                }else {
                    db.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            SimpleDateFormat sdf2 = new SimpleDateFormat("dd yyyy HH:mm:ss");
                            results.setDateTime(sdf2.format(new Date()));
                            results.setTitle(Title);
                            results.setCounter(results.getCounter() + 1);
                            Bitmap bitmap = sbn.getNotification().largeIcon;
                            if (bitmap != null) {
                                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                                results.setAvatar(stream.toByteArray());
                            }

                        }
                    });
                }
                break;
            }

        }
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        Intent broadcastIntent = new Intent(getApplicationContext(), BroadcastRestartService.class);
        sendBroadcast(broadcastIntent);
    }

    private int checkPackageName(StatusBarNotification statusBarNotification) {
        if (statusBarNotification.getPackageName().equals(MessengerPackageName)) {
            return 1;
        }
        else if (statusBarNotification.getPackageName().equals(WhatsappPackageName)) {
            return 2;
        }
        else if (statusBarNotification.getPackageName().equals(InstagramPackageName)) {
            return 3;
        }
        else if (statusBarNotification.getPackageName().equals(SkypePackageName)) {
            return 4;
        }
        else if (statusBarNotification.getPackageName().equals(IMOPackageName)) {
            return 5;
        }
        else if (statusBarNotification.getPackageName().equals(ViberPackageName)) {
            return 6;
        }
        else {
            return 0;
        }
    }

    private void createNotificationChannelID() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    NOTIFICATION_CHANNELID,
                    "NotificationService_Channel",
                    NotificationManager.IMPORTANCE_HIGH
            );

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Intent broadcastIntent = new Intent(getApplicationContext(), BroadcastRestartService.class);
        sendBroadcast(broadcastIntent);
    }
}