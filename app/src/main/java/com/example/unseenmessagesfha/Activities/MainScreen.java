package com.example.unseenmessagesfha.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.unseenmessagesfha.Configurations.RealmConfig;
import com.example.unseenmessagesfha.Fragments.FragOpenChat;
import com.example.unseenmessagesfha.Fragments.FragmentIMO;
import com.example.unseenmessagesfha.Fragments.FragmentInstagram;
import com.example.unseenmessagesfha.Fragments.FragmentMessenger;
import com.example.unseenmessagesfha.Fragments.FragmentSkype;
import com.example.unseenmessagesfha.Fragments.FragmentViber;
import com.example.unseenmessagesfha.Fragments.FragmentWhatsApp;
import com.example.unseenmessagesfha.Models.Apps;
import com.example.unseenmessagesfha.R;

import io.realm.Realm;

public class MainScreen extends AppCompatActivity {

    private ImageButton btn1,btn3,btn4,btn5,btn6,btn2;
    private Drawable WhatsApp,Instagram,Imo,Viber,Skype,Messenger,WhatsAppFade,InstagramFade,ImoFade,ViberFade,SkypeFade,MessengerFade;
    private FrameLayout frameLayout;
    private RealmConfig config = new RealmConfig();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        Realm.init(this);

        btn1 = findViewById(R.id.imgBtn_1);
        btn2 = findViewById(R.id.imgBtn_2);
        btn3 = findViewById(R.id.imgBtn_3);
        btn4 = findViewById(R.id.imgBtn_4);
        btn5 = findViewById(R.id.imgBtn_5);
        btn6 = findViewById(R.id.imgBtn_6);
        btn1.setVisibility(View.GONE);
        btn2.setVisibility(View.GONE);
        btn3.setVisibility(View.GONE);
        btn4.setVisibility(View.GONE);
        btn5.setVisibility(View.GONE);
        btn6.setVisibility(View.GONE);
        ImageView logOut = findViewById(R.id.imgViewLogout);
        frameLayout = findViewById(R.id.frameLayout);

        getDrawables();
        LoadApps();
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadMessages(btn1.getDrawable(),btn2.getDrawable(),btn2);
                if(btn6.getVisibility() == View.VISIBLE)
                {
                    Drawable drawable = btn6.getDrawable();
                    btn6.setImageDrawable(btn2.getDrawable());
                    btn2.setImageDrawable(drawable);
                }
                else if(btn5.getVisibility() == View.VISIBLE)
                {
                    Drawable drawable = btn5.getDrawable();
                    btn5.setImageDrawable(btn2.getDrawable());
                    btn2.setImageDrawable(drawable);
                }
                else if(btn4.getVisibility() == View.VISIBLE)
                {
                    Drawable drawable = btn4.getDrawable();
                    btn4.setImageDrawable(btn2.getDrawable());
                    btn2.setImageDrawable(drawable);
                }
                else if(btn3.getVisibility() == View.VISIBLE)
                {
                    Drawable drawable = btn3.getDrawable();
                    btn3.setImageDrawable(btn2.getDrawable());
                    btn2.setImageDrawable(drawable);
                }
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadMessages(btn1.getDrawable(),btn3.getDrawable(),btn3);
                if(btn6.getVisibility() == View.VISIBLE)
                {
                    Drawable drawable = btn6.getDrawable();
                    btn6.setImageDrawable(btn3.getDrawable());
                    btn3.setImageDrawable(drawable);
                }
                else if(btn5.getVisibility() == View.VISIBLE)
                {
                    Drawable drawable = btn5.getDrawable();
                    btn5.setImageDrawable(btn3.getDrawable());
                    btn3.setImageDrawable(drawable);
                }
                else if(btn4.getVisibility() == View.VISIBLE)
                {
                    Drawable drawable = btn4.getDrawable();
                    btn4.setImageDrawable(btn3.getDrawable());
                    btn3.setImageDrawable(drawable);
                }
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadMessages(btn1.getDrawable(),btn4.getDrawable(),btn4);
                if(btn6.getVisibility() == View.VISIBLE)
                {
                    Drawable drawable = btn6.getDrawable();
                    btn6.setImageDrawable(btn4.getDrawable());
                    btn4.setImageDrawable(drawable);
                }
                else if(btn5.getVisibility() == View.VISIBLE)
                {
                    Drawable drawable = btn5.getDrawable();
                    btn5.setImageDrawable(btn4.getDrawable());
                    btn4.setImageDrawable(drawable);
                }
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadMessages(btn1.getDrawable(),btn5.getDrawable(),btn5);
                if(btn6.getVisibility() == View.VISIBLE)
                {
                    Drawable drawable = btn6.getDrawable();
                    btn6.setImageDrawable(btn5.getDrawable());
                    btn5.setImageDrawable(drawable);
                }
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadMessages(btn1.getDrawable(),btn6.getDrawable(),btn6);
            }
        });
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frameLayout);
                Fragment fragment1 = new FragOpenChat();
                if(fragment.getClass().equals(FragOpenChat.class))
                {
                    MainScreen.super.onBackPressed();
                }
                else {
                    finish();
                    Intent intent = new Intent(MainScreen.this, SelectedApps.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void LoadMessages(Drawable drawable, Drawable drawable1, ImageButton imageButton) {
        if(drawable1.getConstantState() == WhatsAppFade.getConstantState())
        {
            btn1.setImageDrawable(WhatsApp);
            frameLayout.removeAllViews();
            for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            }
            FragmentWhatsApp whatsApp = new FragmentWhatsApp();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.frameLayout,whatsApp);
            transaction.commit();
        }
        else if(drawable1.getConstantState() == InstagramFade.getConstantState())
        {
            btn1.setImageDrawable(Instagram);
            frameLayout.removeAllViews();
            for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            }
            FragmentInstagram instagram = new FragmentInstagram();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.frameLayout,instagram);
            transaction.commit();
        }
        else if(drawable1.getConstantState() == ImoFade.getConstantState())
        {
            btn1.setImageDrawable(Imo);
            frameLayout.removeAllViews();
            for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            }
            FragmentIMO imo = new FragmentIMO();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.frameLayout,imo);
            transaction.commit();
        }
        else if(drawable1.getConstantState() == SkypeFade.getConstantState())
        {
            btn1.setImageDrawable(Skype);
            frameLayout.removeAllViews();
            for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            }
            FragmentSkype skype = new FragmentSkype();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.frameLayout,skype);
            transaction.commit();
        }
        else if(drawable1.getConstantState() == ViberFade.getConstantState())
        {
            btn1.setImageDrawable(Viber);
            frameLayout.removeAllViews();
            for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            }
            FragmentViber viber = new FragmentViber();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.frameLayout,viber);
            transaction.commit();
        }
        else if(drawable1.getConstantState() == MessengerFade.getConstantState())
        {
            btn1.setImageDrawable(Messenger);
            frameLayout.removeAllViews();
            for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            }
            FragmentMessenger messenger = new FragmentMessenger();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.frameLayout,messenger);
            transaction.commit();
        }
        if(drawable.getConstantState() == WhatsApp.getConstantState())
        {
            imageButton.setImageDrawable(WhatsAppFade);
        }
        else if(drawable.getConstantState() == Instagram.getConstantState())
        {
            imageButton.setImageDrawable(InstagramFade);
        }
        else if(drawable.getConstantState() == Skype.getConstantState())
        {
            imageButton.setImageDrawable(SkypeFade);
        }
        else if(drawable.getConstantState() == Imo.getConstantState())
        {
            imageButton.setImageDrawable(ImoFade);
        }
        else if(drawable.getConstantState() == Viber.getConstantState())
        {
            imageButton.setImageDrawable(ViberFade);
        }
        else if(drawable.getConstantState() == Messenger.getConstantState())
        {
            imageButton.setImageDrawable(MessengerFade);
        }
    }

    private void LoadApps()
    {
        Realm db =Realm.getInstance(config.AppsConfig());
        Apps result = db.where(Apps.class).findFirst();
        if(result.getWhatsApp() == 10)
        {
            btn1.setVisibility(View.VISIBLE);
            frameLayout.removeAllViews();
            FragmentWhatsApp whatsApp = new FragmentWhatsApp();
            Bundle args = new Bundle();
            args.putInt("ViewId",R.id.frameLayout);
            whatsApp.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.frameLayout,whatsApp);
            transaction.commit();
        }
        if(result.getSkype() == 10)
        {
            if(btn1.getVisibility()== View.GONE)
            {
                btn1.setImageDrawable(Skype);
                btn1.setVisibility(View.VISIBLE);
                frameLayout.removeAllViews();
                FragmentSkype skype = new FragmentSkype();
                Bundle args = new Bundle();
                args.putInt("ViewId",R.id.frameLayout);
                skype.setArguments(args);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.frameLayout,skype);
                transaction.commit();
            }
            else {
                btn2.setVisibility(View.VISIBLE);
            }
        }
        if(result.getMessenger() == 10)
        {
            if(btn1.getVisibility()== View.GONE)
            {
                btn1.setImageDrawable(Messenger);
                btn1.setVisibility(View.VISIBLE);
                frameLayout.removeAllViews();
                FragmentMessenger fragment = new FragmentMessenger();
                Bundle args = new Bundle();
                args.putInt("ViewId",R.id.frameLayout);
                fragment.setArguments(args);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.frameLayout,fragment);
                transaction.commit();
            }
            else {
                btn3.setVisibility(View.VISIBLE);
            }
        }
        if(result.getViber() == 10)
        {
            if(btn1.getVisibility()== View.GONE)
            {
                btn1.setImageDrawable(Viber);
                btn1.setVisibility(View.VISIBLE);
                frameLayout.removeAllViews();
                FragmentViber fragment = new FragmentViber();
                Bundle args = new Bundle();
                args.putInt("ViewId",R.id.frameLayout);
                fragment.setArguments(args);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.frameLayout,fragment);
                transaction.commit();
            }
            else {
                btn4.setVisibility(View.VISIBLE);
            }
        }
        if(result.getInstagram() == 10)
        {
            if(btn1.getVisibility()== View.GONE)
            {
                btn1.setImageDrawable(Instagram);
                btn1.setVisibility(View.VISIBLE);
                frameLayout.removeAllViews();
                FragmentInstagram fragment = new FragmentInstagram();
                Bundle args = new Bundle();
                args.putInt("ViewId",R.id.frameLayout);
                fragment.setArguments(args);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.frameLayout,fragment);
                transaction.commit();
            }
            else {
                btn5.setVisibility(View.VISIBLE);
            }
        }
        if(result.getImo() == 10)
        {
            if(btn1.getVisibility()== View.GONE)
            {
                btn1.setImageDrawable(Imo);
                btn1.setVisibility(View.VISIBLE);
                frameLayout.removeAllViews();
                FragmentIMO fragment = new FragmentIMO();
                Bundle args = new Bundle();
                args.putInt("ViewId",R.id.frameLayout);
                fragment.setArguments(args);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.frameLayout,fragment);
                transaction.commit();
            }
            else {
                btn6.setVisibility(View.VISIBLE);
            }
        }
    }
    void getDrawables()
    {
        WhatsApp = getResources().getDrawable(R.drawable.whatsapp);
        WhatsAppFade = getResources().getDrawable(R.drawable.wa);
        Instagram = getResources().getDrawable(R.drawable.instagram);
        InstagramFade = getResources().getDrawable(R.drawable.insta);
        Imo = getResources().getDrawable(R.drawable.imo);
        ImoFade = getResources().getDrawable(R.drawable.imol);
        Messenger = getResources().getDrawable(R.drawable.messenger);
        MessengerFade = getResources().getDrawable(R.drawable.mg);
        Viber = getResources().getDrawable(R.drawable.viber);
        ViberFade = getResources().getDrawable(R.drawable.viib);
        Skype = getResources().getDrawable(R.drawable.skype);
        SkypeFade = getResources().getDrawable(R.drawable.sp);
    }

}
