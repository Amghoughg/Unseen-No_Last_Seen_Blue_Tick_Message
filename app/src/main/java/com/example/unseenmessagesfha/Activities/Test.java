package com.example.unseenmessagesfha.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.unseenmessagesfha.Adapters.AllChatAdapter;
import com.example.unseenmessagesfha.Adapters.MessagesAdapter;
import com.example.unseenmessagesfha.Configurations.RealmConfig;
import com.example.unseenmessagesfha.Fragments.FragmentIMO;
import com.example.unseenmessagesfha.Fragments.FragmentInstagram;
import com.example.unseenmessagesfha.Fragments.FragmentMessenger;
import com.example.unseenmessagesfha.Fragments.FragmentSkype;
import com.example.unseenmessagesfha.Fragments.FragmentViber;
import com.example.unseenmessagesfha.Fragments.FragmentWhatsApp;
import com.example.unseenmessagesfha.Models.AllChat;
import com.example.unseenmessagesfha.Models.Apps;
import com.example.unseenmessagesfha.Models.Messages;
import com.example.unseenmessagesfha.R;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class Test extends AppCompatActivity {

    private ImageButton btn1,btn3,btn4,btn5,btn6,btn2;
    private Drawable WhatsApp,Instagram,Imo,Viber,Skype,Messenger,WhatsAppFade,InstagramFade,ImoFade,ViberFade,SkypeFade,MessengerFade;
    private RecyclerView recyclerView;
    private ImageView logOut;
    String Title,AppName;
    private RealmConfig config = new RealmConfig();
    private AllChatAdapter adapter;
    private Realm WhatsAppDb,MessengerDb,ImoDb,SkypeDb,ViberDb,InstaDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Realm.init(this);

        btn1 = findViewById(R.id.imgBtn_1test);
        btn2 = findViewById(R.id.imgBtn_2test);
        btn3 = findViewById(R.id.imgBtn_3test);
        btn4 = findViewById(R.id.imgBtn_4test);
        btn5 = findViewById(R.id.imgBtn_5test);
        btn6 = findViewById(R.id.imgBtn_6test);
        btn1.setVisibility(View.GONE);
        btn2.setVisibility(View.GONE);
        btn3.setVisibility(View.GONE);
        btn4.setVisibility(View.GONE);
        btn5.setVisibility(View.GONE);
        btn6.setVisibility(View.GONE);
        WhatsAppDb = Realm.getInstance(config.WhatsappConfig());
        MessengerDb = Realm.getInstance(config.MessengerConfig());
        ImoDb = Realm.getInstance(config.ImoConfig());
        InstaDb = Realm.getInstance(config.InstagramConfig());
        ViberDb = Realm.getInstance(config.ViberConfig());
        SkypeDb = Realm.getInstance(config.SkypeConfig());
        logOut = findViewById(R.id.imgViewLogout);
        recyclerView = findViewById(R.id.frameLayouttest);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getDrawables();
        LoadApps();

        adapter.setClickListener(new AllChatAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View v, AllChat obj) {
                OpenChat(obj);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(btn2);
                /*Drawable drawable = btn2.getDrawable();
                if(btn6.getVisibility() == View.VISIBLE)
                {
                    btn2.setImageDrawable(btn3.getDrawable());
                    btn3.setImageDrawable(btn4.getDrawable());
                    btn4.setImageDrawable(btn5.getDrawable());
                    btn5.setImageDrawable(btn6.getDrawable());
                    btn6.setImageDrawable(drawable);
                }
                else if(btn5.getVisibility() == View.VISIBLE)
                {
                    btn2.setImageDrawable(btn3.getDrawable());
                    btn3.setImageDrawable(btn4.getDrawable());
                    btn4.setImageDrawable(btn5.getDrawable());
                    btn5.setImageDrawable(drawable);
                }
                else if(btn4.getVisibility() == View.VISIBLE)
                {
                    btn2.setImageDrawable(btn3.getDrawable());
                    btn3.setImageDrawable(btn4.getDrawable());
                    btn4.setImageDrawable(drawable);
                }
                else if (btn3.getVisibility() == View.VISIBLE)
                {
                    btn2.setImageDrawable(btn3.getDrawable());
                    btn3.setImageDrawable(drawable);
                }*/
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(btn3);
                /*Drawable drawable = btn3.getDrawable();
                if(btn6.getVisibility() == View.VISIBLE)
                {
                    btn2.setImageDrawable(btn3.getDrawable());
                    btn3.setImageDrawable(btn4.getDrawable());
                    btn4.setImageDrawable(btn5.getDrawable());
                    btn5.setImageDrawable(btn6.getDrawable());
                    btn6.setImageDrawable(drawable);
                }
                else if(btn5.getVisibility() == View.VISIBLE)
                {
                    btn2.setImageDrawable(btn3.getDrawable());
                    btn3.setImageDrawable(btn4.getDrawable());
                    btn4.setImageDrawable(btn5.getDrawable());
                    btn5.setImageDrawable(drawable);
                }
                else if(btn4.getVisibility() == View.VISIBLE)
                {
                    btn2.setImageDrawable(btn3.getDrawable());
                    btn3.setImageDrawable(btn4.getDrawable());
                    btn4.setImageDrawable(drawable);
                }*/
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(btn4);
                /*Drawable drawable = btn4.getDrawable();
                if(btn6.getVisibility() == View.VISIBLE)
                {
                    btn2.setImageDrawable(btn3.getDrawable());
                    btn3.setImageDrawable(btn4.getDrawable());
                    btn4.setImageDrawable(btn5.getDrawable());
                    btn5.setImageDrawable(btn6.getDrawable());
                    btn6.setImageDrawable(drawable);
                }
                else if(btn5.getVisibility() == View.VISIBLE)
                {
                    btn2.setImageDrawable(btn3.getDrawable());
                    btn3.setImageDrawable(btn4.getDrawable());
                    btn4.setImageDrawable(btn5.getDrawable());
                    btn5.setImageDrawable(drawable);
                }*/
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(btn5);
                /*Drawable drawable = btn4.getDrawable();
                if(btn6.getVisibility() == View.VISIBLE)
                {
                    btn2.setImageDrawable(btn3.getDrawable());
                    btn3.setImageDrawable(btn4.getDrawable());
                    btn4.setImageDrawable(btn5.getDrawable());
                    btn5.setImageDrawable(btn6.getDrawable());
                    btn6.setImageDrawable(drawable);
                }*/
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(btn6);
            }
        });
    }

    private void onButtonClick(ImageButton imageButton) {
        Drawable FirstButtonDrawable = btn1.getDrawable();
        Realm db = Realm.getInstance(config.AllChat());
        if(imageButton.getDrawable().getConstantState() == MessengerFade.getConstantState())
        {
            btn1.setImageDrawable(Messenger);
            RealmResults results = db.where(AllChat.class).equalTo("AppName", "com.facebook.orca").sort("DateTime", Sort.DESCENDING).findAll();
            adapter = new AllChatAdapter(results,this);
            adapter.setClickListener(new AllChatAdapter.ItemClickListener() {
                @Override
                public void onItemClick(View v, AllChat obj) {
                    OpenChat(obj);
                }
            });
            recyclerView.setAdapter(adapter);
        }
        else if(imageButton.getDrawable().getConstantState() == InstagramFade.getConstantState())
        {
            btn1.setImageDrawable(Instagram);
            RealmResults results = db.where(AllChat.class).equalTo("AppName", "com.instagram.android").sort("DateTime", Sort.DESCENDING).findAll();
            adapter = new AllChatAdapter(results,this);
            adapter.setClickListener(new AllChatAdapter.ItemClickListener() {
                @Override
                public void onItemClick(View v, AllChat obj) {
                    OpenChat(obj);
                }
            });
            recyclerView.setAdapter(adapter);
        }
        else if(imageButton.getDrawable().getConstantState() == SkypeFade.getConstantState())
        {
            btn1.setImageDrawable(Skype);
            RealmResults results = db.where(AllChat.class).equalTo("AppName", "com.skype.raider").sort("DateTime", Sort.DESCENDING).findAll();
            adapter = new AllChatAdapter(results,this);
            adapter.setClickListener(new AllChatAdapter.ItemClickListener() {
                @Override
                public void onItemClick(View v, AllChat obj) {
                    OpenChat(obj);
                }
            });
            recyclerView.setAdapter(adapter);
        }
        else if(imageButton.getDrawable().getConstantState() == ImoFade.getConstantState())
        {
            btn1.setImageDrawable(Imo);
            RealmResults results = db.where(AllChat.class).equalTo("AppName", "com.imo.android.imoim").sort("DateTime", Sort.DESCENDING).findAll();
            adapter = new AllChatAdapter(results,this);
            adapter.setClickListener(new AllChatAdapter.ItemClickListener() {
                @Override
                public void onItemClick(View v, AllChat obj) {
                    OpenChat(obj);
                }
            });
            recyclerView.setAdapter(adapter);
        }
        else if(imageButton.getDrawable().getConstantState() == ViberFade.getConstantState())
        {
            btn1.setImageDrawable(Viber);
            RealmResults results = db.where(AllChat.class).equalTo("AppName", "com.viber.voip").sort("DateTime", Sort.DESCENDING).findAll();
            adapter = new AllChatAdapter(results,this);
            adapter.setClickListener(new AllChatAdapter.ItemClickListener() {
                @Override
                public void onItemClick(View v, AllChat obj) {
                    OpenChat(obj);
                }
            });
            recyclerView.setAdapter(adapter);
        }
        else if(imageButton.getDrawable().getConstantState() == WhatsAppFade.getConstantState())
        {
            btn1.setImageDrawable(WhatsApp);
            RealmResults results = db.where(AllChat.class).equalTo("AppName", "com.whatsapp").sort("DateTime", Sort.DESCENDING).findAll();
            adapter = new AllChatAdapter(results,this);
            adapter.setClickListener(new AllChatAdapter.ItemClickListener() {
                @Override
                public void onItemClick(View v, AllChat obj) {
                    OpenChat(obj);
                }
            });
            recyclerView.setAdapter(adapter);
        }
        Drawable newDrawable=null;
        if(FirstButtonDrawable.getConstantState() == WhatsApp.getConstantState())
        {
            newDrawable = WhatsAppFade;
        }
        else if(FirstButtonDrawable.getConstantState() == Messenger.getConstantState())
        {
            newDrawable = MessengerFade;
        }
        else if(FirstButtonDrawable.getConstantState() == Imo.getConstantState())
        {
            newDrawable = ImoFade;
        }
        else if(FirstButtonDrawable.getConstantState() == Viber.getConstantState())
        {
            newDrawable = ViberFade;
        }
        else if(FirstButtonDrawable.getConstantState() == Skype.getConstantState())
        {
            newDrawable = SkypeFade;
        }
        else if(FirstButtonDrawable.getConstantState() == Instagram.getConstantState())
        {
            newDrawable = InstagramFade;
        }
        imageButton.setImageDrawable(newDrawable);
//        ChangeButtonPosition(newDrawable);
    }

    private void ChangeButtonPosition(Drawable drawable)
    {
        if(btn6.getVisibility() == View.VISIBLE)
        {
            btn2.setImageDrawable(btn3.getDrawable());
            btn3.setImageDrawable(btn4.getDrawable());
            btn4.setImageDrawable(btn5.getDrawable());
            btn5.setImageDrawable(btn6.getDrawable());
            btn6.setImageDrawable(drawable);
        }
        else if(btn5.getVisibility() == View.VISIBLE)
        {
            btn2.setImageDrawable(btn3.getDrawable());
            btn3.setImageDrawable(btn4.getDrawable());
            btn4.setImageDrawable(btn5.getDrawable());
            btn5.setImageDrawable(drawable);
        }
        else if(btn4.getVisibility() == View.VISIBLE)
        {
            btn2.setImageDrawable(btn3.getDrawable());
            btn3.setImageDrawable(btn4.getDrawable());
            btn4.setImageDrawable(drawable);
        }
        else if(btn3.getVisibility() == View.VISIBLE)
        {
            btn2.setImageDrawable(btn3.getDrawable());
            btn3.setImageDrawable(drawable);
        }
        else if(btn2.getVisibility() == View.VISIBLE)
        {
            btn2.setImageDrawable(drawable);
        }
    }

    private void OpenChat(AllChat obj) {
        Title = obj.getTitle();
        AppName = obj.getAppName();
        LoadChat(this,AppName);
    }

    private void LoadChat(Context context, String appName) {
        MessagesAdapter newAdapter;
        switch (appName)
        {
            case "com.whatsapp":
            {
                newAdapter = new MessagesAdapter(getWhatsAppChat(), context);
                recyclerView.setAdapter(newAdapter);
                break;
            }
            case "com.facebook.orca":
            {
                newAdapter = new MessagesAdapter(getMessengerChat(),context);
                recyclerView.setAdapter(newAdapter);
                break;
            }
            case "com.instagram.android":
            {
                newAdapter = new MessagesAdapter(getInstaChat(),context);
                recyclerView.setAdapter(newAdapter);
                break;
            }
            case "com.imo.android.imoim":
            {
                newAdapter = new MessagesAdapter(getImoChat(),context);
                recyclerView.setAdapter(newAdapter);
                break;
            }
            case "com.skype.raider":
            {
                newAdapter = new MessagesAdapter(getSkypeChat(),context);
                recyclerView.setAdapter(newAdapter);
                break;
            }
            case "com.viber.voip":
            {
                newAdapter = new MessagesAdapter(getViberChat(),context);
                recyclerView.setAdapter(newAdapter);
                break;
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
    private void LoadApps()
    {
        Realm db =Realm.getInstance(config.AppsConfig());
        Apps result = db.where(Apps.class).findFirst();
        Realm chat = Realm.getInstance(config.AllChat());
        RealmResults<AllChat> results;
        if(result.getWhatsApp() == 10)
        {
            results = chat.where(AllChat.class).equalTo("AppName", "com.whatsapp").sort("DateTime", Sort.DESCENDING).findAll();
            btn1.setVisibility(View.VISIBLE);
            adapter= new AllChatAdapter(results,this);
            recyclerView.setAdapter(adapter);
        }
        if(result.getSkype() == 10)
        {
            if(btn1.getVisibility()== View.GONE)
            {
                btn1.setImageDrawable(Skype);
                btn1.setVisibility(View.VISIBLE);
                results = chat.where(AllChat.class).equalTo("AppName", "com.skype.raider").sort("DateTime", Sort.DESCENDING).findAll();
                adapter= new AllChatAdapter(results,this);
                recyclerView.setAdapter(adapter);
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
                results = chat.where(AllChat.class).equalTo("AppName", "com.facebook.orca").sort("DateTime", Sort.DESCENDING).findAll();
                adapter= new AllChatAdapter(results,this);
                recyclerView.setAdapter(adapter);
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
                results = chat.where(AllChat.class).equalTo("AppName", "com.viber.voip").sort("DateTime", Sort.DESCENDING).findAll();
                adapter= new AllChatAdapter(results,this);
                recyclerView.setAdapter(adapter);
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
                results = chat.where(AllChat.class).equalTo("AppName", "com.instagram.android").sort("DateTime", Sort.DESCENDING).findAll();
                adapter= new AllChatAdapter(results,this);
                recyclerView.setAdapter(adapter);
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
                results = chat.where(AllChat.class).equalTo("AppName", "com.imo.android.imoim").sort("DateTime", Sort.DESCENDING).findAll();
                adapter= new AllChatAdapter(results,this);
                recyclerView.setAdapter(adapter);
            }
            else {
                btn6.setVisibility(View.VISIBLE);
            }
        }
    }
    private ArrayList<Messages> getWhatsAppChat()
    {
        ArrayList<Messages> myArray = new ArrayList<>();
        ArrayList<Messages> result1 = new ArrayList<>();
        ArrayList<Messages> result2 = new ArrayList<>();
        RealmResults<Messages> Checker =  WhatsAppDb.where(Messages.class).contains("Title", Title).sort("TimeDate", Sort.DESCENDING).findAll();
        myArray.addAll(Checker);
        for(int i = 0 ; i < myArray.size() ; i++)
        {
            if(myArray.get(i).getTitle().equals(Title))
            {
                result1.add(myArray.get(i));
            }
            else {
                result2.add(myArray.get(i));
            }
        }
        if(result1.size() > 0) return result1;
        else return result2;
    }
    private ArrayList<Messages> getMessengerChat()
    {
        ArrayList<Messages> result = new ArrayList<>();
        RealmResults<Messages> Checker =  MessengerDb.where(Messages.class).equalTo("Title", Title).sort("TimeDate",Sort.DESCENDING).findAll();
        result.addAll(Checker);
        return result;
    }
    private ArrayList<Messages> getInstaChat()
    {
        ArrayList<Messages> result = new ArrayList<>();
        RealmResults<Messages> Checker =  InstaDb.where(Messages.class).equalTo("Title", Title).sort("TimeDate",Sort.DESCENDING).findAll();
        result.addAll(Checker);
        return result;
    }
    private ArrayList<Messages> getSkypeChat()
    {
        ArrayList<Messages> myArray = new ArrayList<>();
        ArrayList<Messages> result1 = new ArrayList<>();
        ArrayList<Messages> result2 = new ArrayList<>();
        RealmResults<Messages> Checker =  SkypeDb.where(Messages.class).contains("Title", Title).sort("TimeDate", Sort.DESCENDING).findAll();
        myArray.addAll(Checker);
        for(int i = 0 ; i < myArray.size() ; i++)
        {
            if(myArray.get(i).getTitle().equals(Title))
            {
                result1.add(myArray.get(i));
            }
            else {
                result2.add(myArray.get(i));
            }
        }
        if(result1.size() > 0) return result1;
        else return result2;
    }
    private ArrayList<Messages> getImoChat()
    {
        ArrayList<Messages> myArray = new ArrayList<>();
        ArrayList<Messages> result1 = new ArrayList<>();
        ArrayList<Messages> result2 = new ArrayList<>();
        RealmResults<Messages> Checker =  ImoDb.where(Messages.class).contains("Title", Title).sort("TimeDate", Sort.DESCENDING).findAll();
        myArray.addAll(Checker);
        for(int i = 0 ; i < myArray.size() ; i++)
        {
            if(myArray.get(i).getTitle().equals(Title))
            {
                result1.add(myArray.get(i));
            }
            else {
                result2.add(myArray.get(i));
            }
        }
        if(result1.size() > 0) return result1;
        else return result2;
    }
    private ArrayList<Messages> getViberChat()
    {
        ArrayList<Messages> myArray = new ArrayList<>();
        ArrayList<Messages> result1 = new ArrayList<>();
        ArrayList<Messages> result2 = new ArrayList<>();
        RealmResults<Messages> Checker =  ViberDb.where(Messages.class).contains("Title", Title).sort("TimeDate", Sort.DESCENDING).findAll();
        myArray.addAll(Checker);
        for(int i = 0 ; i < myArray.size() ; i++)
        {
            if(myArray.get(i).getTitle().equals(Title))
            {
                result1.add(myArray.get(i));
            }
            else {
                result2.add(myArray.get(i));
            }
        }
        if(result1.size() > 0) return result1;
        else return result2;
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(new AllChatAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View v, AllChat obj) {
                OpenChat(obj);
            }
        });
    }
}
