package com.example.unseenmessagesfha.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.unseenmessagesfha.Adapters.SliderAdapter;
import com.example.unseenmessagesfha.Configurations.RealmConfig;
import com.example.unseenmessagesfha.Models.Apps;
import com.example.unseenmessagesfha.R;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import io.realm.Realm;

public class SelectedApps extends AppCompatActivity {
    private ViewPager viewPager;
    private ArrayList<Integer> data;
    private ImageView indicator1,indicator2,indicator3;
    private ImageView btnDelete,View1,View2;
    private ImageButton btnAddApps,btnProceed,imgView_Messenger,imgView_Skype,imgView_Imo,imgView_WhatsApp,imgView_Viber,imgView_Insta;
    private Drawable DisableMessenger,DisableWhatsApp,DisableSkype,DisableInsta,DisableImo,DisableViber;
    private RealmConfig config = new RealmConfig();
    private Boolean btnPressed = false;
    private ConstraintLayout layoutDull;
    private TextView deleteMsg,AddMsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_apps);
        Realm.init(this);
        getDrawables();

        btnPressed = false;
        viewPager = findViewById(R.id.viewPager);
        indicator1 = findViewById(R.id.indicator1);
        indicator2 = findViewById(R.id.indicator2);
        indicator3 = findViewById(R.id.indicator3);
        btnAddApps = findViewById(R.id.imgBtn_AddApp);
        imgView_Imo = findViewById(R.id.imgView_Imo);
        imgView_Insta = findViewById(R.id.imgView_Instagram);
        imgView_Skype = findViewById(R.id.imgView_Skype);
        imgView_WhatsApp = findViewById(R.id.imgView_WhatsApp);
        imgView_Viber = findViewById(R.id.imgView_Viber);
        imgView_Messenger = findViewById(R.id.imgView_Messenger);
        btnProceed = findViewById(R.id.imgBtn_Proceed);
        btnDelete = findViewById(R.id.imgBtn_Delete);
        View1 = findViewById(R.id.imgView_rectangle2);
        View2 = findViewById(R.id.imgView_group491);
        layoutDull = findViewById(R.id.layout_dull);
        deleteMsg = findViewById(R.id.deleteMessage);
        AddMsg = findViewById(R.id.AddAppMessage);
        AddMsg.setVisibility(View.GONE);
        deleteMsg.setVisibility(View.GONE);
        layoutDull.setVisibility(View.GONE);

        setAppIcons();

        data = new ArrayList<>();
        data.add(R.drawable.group31);
        data.add(R.drawable.group331);
        data.add(R.drawable.group478);

        viewPager.setAdapter(new SliderAdapter(this,data));
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 3000, 6000);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                moveIndicator(position);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        btnAddApps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectedApps.this,AddApps.class);
                startActivity(intent);
                overridePendingTransition(R.anim.bottom_to_top, R.anim.nothing);
            }
        });
        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectedApps.this,MainScreen.class);
                startActivity(intent);
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RemoveApps();
            }
        });
        /*imgView_WhatsApp.setEnabled(true);
        imgView_Insta.setEnabled(true);
        imgView_Imo.setEnabled(true);
        imgView_Skype.setEnabled(true);
        imgView_Messenger.setEnabled(true);
        imgView_Viber.setEnabled(true);*/
        imgView_Viber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(layoutDull.getVisibility() == View.GONE)
                {
                   AddMsg.setVisibility(View.VISIBLE);
                    Task(AddMsg);
                }
                else {
                    if (imgView_Viber.getDrawable().getConstantState() != getResources().getDrawable(R.drawable.viber_disabled).getConstantState()) {
                        if (imgView_Viber.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.group485).getConstantState()) {
                            imgView_Viber.setImageResource(R.drawable.group518);
                        }
                        else {
                            imgView_Viber.setImageResource(R.drawable.group485);
                        }
                    }
                }
            }
        });
        imgView_Messenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(layoutDull.getVisibility() == View.GONE)
                {
                    AddMsg.setVisibility(View.VISIBLE);
                    Task(AddMsg);
                }
                else {
                    if (imgView_Messenger.getDrawable().getConstantState() != getResources().getDrawable(R.drawable.messenger_disabled).getConstantState()) {
                        if (imgView_Messenger.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.group480).getConstantState()) {
                            imgView_Messenger.setImageResource(R.drawable.group519);
                        }
                        else {
                            imgView_Messenger.setImageResource(R.drawable.group480);
                        }
                    }
                }
            }
        });
        imgView_WhatsApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(layoutDull.getVisibility() == View.GONE)
                {
                    AddMsg.setVisibility(View.VISIBLE);
                    Task(AddMsg);
                }
                else {
                    if (imgView_WhatsApp.getDrawable().getConstantState() != getResources().getDrawable(R.drawable.whatsapp_disabled).getConstantState()) {
                        if (imgView_WhatsApp.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.group484).getConstantState()) {
                            imgView_WhatsApp.setImageResource(R.drawable.group517);
                        }
                        else {
                            imgView_WhatsApp.setImageResource(R.drawable.group484);
                        }
                    }
                }
            }
        });
        imgView_Skype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(layoutDull.getVisibility() == View.GONE)
                {
                    AddMsg.setVisibility(View.VISIBLE);
                    Task(AddMsg);
                }
                else {
                    if (imgView_Skype.getDrawable().getConstantState() != getResources().getDrawable(R.drawable.skype_disabled).getConstantState()) {
                        if (imgView_Skype.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.group482).getConstantState()) {
                            imgView_Skype.setImageResource(R.drawable.group515);
                        }
                        else {
                            imgView_Skype.setImageResource(R.drawable.group482);
                        }
                    }
                }
            }
        });
        imgView_Imo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(layoutDull.getVisibility() == View.GONE)
                {
                    AddMsg.setVisibility(View.VISIBLE);
                    Task(AddMsg);
                }
                else {
                    if (imgView_Imo.getDrawable().getConstantState() != getResources().getDrawable(R.drawable.imo_disabled).getConstantState()) {
                        if (imgView_Imo.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.group483).getConstantState()) {
                            imgView_Imo.setImageResource(R.drawable.group516);
                        }
                        else {
                            imgView_Imo.setImageResource(R.drawable.group483);
                        }
                    }
                }
            }
        });
        imgView_Insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(layoutDull.getVisibility() == View.GONE)
                {
                    AddMsg.setVisibility(View.VISIBLE);
                    Task(AddMsg);
                }
                else {
                    if (imgView_Insta.getDrawable().getConstantState() != getResources().getDrawable(R.drawable.insta_disabled).getConstantState()) {
                        if (imgView_Insta.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.group481).getConstantState()) {
                            imgView_Insta.setImageResource(R.drawable.group520);
                        }
                        else {
                            imgView_Insta.setImageResource(R.drawable.group481);
                        }
                    }
                }
            }
        });
    }

    private void RemoveApps() {
        if(!btnPressed)
        {
            btnProceed.setEnabled(false);
            layoutDull.setVisibility(View.VISIBLE);
            btnAddApps.setEnabled(false);
            btnProceed.setImageResource(R.drawable.group544);
            /*imgView_WhatsApp.setEnabled(true);
            imgView_Insta.setEnabled(true);
            imgView_Imo.setEnabled(true);
            imgView_Skype.setEnabled(true);
            imgView_Messenger.setEnabled(true);
            imgView_Viber.setEnabled(true);*/
            btnPressed = true;
        }
        else
        {
            btnProceed.setEnabled(true);
            layoutDull.setVisibility(View.GONE);
            btnAddApps.setEnabled(true);
            /*imgView_WhatsApp.setEnabled(false);
            imgView_Insta.setEnabled(false);
            imgView_Imo.setEnabled(false);
            imgView_Skype.setEnabled(false);
            imgView_Messenger.setEnabled(false);
            imgView_Viber.setEnabled(false);*/
            btnProceed.setImageResource(R.drawable.group487);
            deleteApps();
            btnPressed = false;
            deleteMsg.setVisibility(View.VISIBLE);
            Task(deleteMsg);
        }
    }

    private void deleteApps()
    {
        Realm db = Realm.getInstance(config.AppsConfig());
        Apps obj = db.where(Apps.class).findFirst();
        db.beginTransaction();
        if(imgView_Insta.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.group520).getConstantState())
        {
            obj.setInstagram(0);
        }
        if(imgView_Messenger.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.group519).getConstantState())
        {
            obj.setMessenger(0);
        }
        if(imgView_Viber.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.group518).getConstantState())
        {
            obj.setViber(0);
        }
        if(imgView_WhatsApp.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.group517).getConstantState())
        {
            obj.setWhatsApp(0);
        }
        if(imgView_Imo.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.group516).getConstantState())
        {
            obj.setImo(0);
        }
        if(imgView_Skype.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.group515).getConstantState())
        {
            obj.setSkype(0);
        }
        db.commitTransaction();
        setAppIcons();
    }

    private void setAppIcons() {
        Realm db = Realm.getInstance(config.AppsConfig());
        Apps obj = db.where(Apps.class).findFirst();
        if(obj.getImo() == 0)
        {
            imgView_Imo.setImageDrawable(DisableImo);
        }
        if(obj.getInstagram() == 0)
        {
            imgView_Insta.setImageDrawable(DisableInsta);
        }
        if(obj.getViber() == 0)
        {
            imgView_Viber.setImageDrawable(DisableViber);
        }
        if(obj.getMessenger() == 0)
        {
            imgView_Messenger.setImageDrawable(DisableMessenger);
        }
        if(obj.getSkype() == 0)
        {
            imgView_Skype.setImageDrawable(DisableSkype);
        }
        if(obj.getWhatsApp() == 0)
        {
            imgView_WhatsApp.setImageDrawable(DisableWhatsApp);
        }
    }

    private void getDrawables() {
        DisableImo = getResources().getDrawable(R.drawable.imo_disabled);
        DisableInsta = getResources().getDrawable(R.drawable.insta_disabled);
        DisableSkype = getResources().getDrawable(R.drawable.skype_disabled);
        DisableWhatsApp = getResources().getDrawable(R.drawable.whatsapp_disabled);
        DisableViber = getResources().getDrawable(R.drawable.viber_disabled);
        DisableMessenger = getResources().getDrawable(R.drawable.messenger_disabled);
    }


    private void moveIndicator(int position) {
        int activated = getResources().getDimensionPixelSize(R.dimen._20sdp);
        int defaultIndicated = getResources().getDimensionPixelSize(R.dimen._10sdp);
        switch (position){
            case 0:
            {
                indicator1.setImageResource(R.drawable.selected_indicator);
                indicator1.getLayoutParams().width = activated;
                indicator2.setImageResource(R.drawable.default_indicator);
                indicator2.getLayoutParams().width = defaultIndicated;
                indicator3.setImageResource(R.drawable.default_indicator);
                indicator3.getLayoutParams().width = defaultIndicated;
                break;
            }
            case 1:
            {
                indicator2.setImageResource(R.drawable.selected_indicator);
                indicator2.getLayoutParams().width = activated;
                indicator1.setImageResource(R.drawable.default_indicator);
                indicator1.getLayoutParams().width = defaultIndicated;
                indicator3.setImageResource(R.drawable.default_indicator);
                indicator3.getLayoutParams().width = defaultIndicated;
                break;
            }
            case 2:
            {
                indicator3.setImageResource(R.drawable.selected_indicator);
                indicator3.getLayoutParams().width =activated;
                indicator1.setImageResource(R.drawable.default_indicator);
                indicator1.getLayoutParams().width = defaultIndicated;
                indicator2.setImageResource(R.drawable.default_indicator);
                indicator2.getLayoutParams().width = defaultIndicated;
                break;
            }
        }
    }


    private class SliderTimer extends TimerTask {

        @Override
        public void run() {
            SelectedApps.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager.getCurrentItem() < data.size() - 1) {
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    } else {
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,ExitScreen.class);
        startActivity(intent);
    }
    private void Task(final TextView v)
    {
        Timer t = new Timer(false);
         t.schedule(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            v.setVisibility(View.GONE);
                        }
                    });
             }
         }, 3000);
    }
}
