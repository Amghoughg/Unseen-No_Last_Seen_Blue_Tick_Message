package com.example.unseenmessagesfha.Fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.unseenmessagesfha.Adapters.MessagesAdapter;
import com.example.unseenmessagesfha.Configurations.RealmConfig;
import com.example.unseenmessagesfha.Models.AllChat;
import com.example.unseenmessagesfha.Models.Messages;
import com.example.unseenmessagesfha.R;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragOpenChat extends Fragment {

    private RecyclerView recyclerView;
    private MessagesAdapter adapter;
    public FragOpenChat() {
        // Required empty public constructor
    }
    private String Title;
    private String AppName;
    private RealmConfig config = new RealmConfig();
    private Realm WhatsAppDb,MessengerDb,ImoDb,SkypeDb,ViberDb,InstaDb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_openchat, container, false);
        Realm.init(v.getContext());

        recyclerView = v.findViewById(R.id.recyclerView_WhatsAppChat);
        WhatsAppDb = Realm.getInstance(config.WhatsappConfig());
        MessengerDb = Realm.getInstance(config.MessengerConfig());
        ImoDb = Realm.getInstance(config.ImoConfig());
        InstaDb = Realm.getInstance(config.InstagramConfig());
        ViberDb = Realm.getInstance(config.ViberConfig());
        SkypeDb = Realm.getInstance(config.SkypeConfig());

        Bundle bundle = this.getArguments();
        Title = bundle.getString("Title");
        AppName = bundle.getString("AppName");

        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        openChat(v.getContext(),AppName);

        DatabaseChangeListener(v.getContext());

        return v;
    }

    private void openChat(Context context, String appName) {
        switch (appName)
        {
            case "com.whatsapp":
            {
                adapter = new MessagesAdapter(getWhatsAppChat(),context);
                recyclerView.setAdapter(adapter);
                break;
            }
            case "com.facebook.orca":
            {
                adapter = new MessagesAdapter(getMessengerChat(),context);
                recyclerView.setAdapter(adapter);
                break;
            }
            case "com.instagram.android":
            {
                adapter = new MessagesAdapter(getInstaChat(),context);
                recyclerView.setAdapter(adapter);
                break;
            }
            case "com.imo.android.imoim":
            {
                adapter = new MessagesAdapter(getImoChat(),context);
                recyclerView.setAdapter(adapter);
                break;
            }
            case "com.skype.raider":
            {
                adapter = new MessagesAdapter(getSkypeChat(),context);
                recyclerView.setAdapter(adapter);
                break;
            }
            case "com.viber.voip":
            {
                adapter = new MessagesAdapter(getViberChat(),context);
                recyclerView.setAdapter(adapter);
                break;
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
            if(myArray.get(i).getText() == null)
            {
                continue;
            }
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

    private void DatabaseChangeListener(final Context context) {
        WhatsAppDb.addChangeListener(new RealmChangeListener<Realm>() {
            @Override
            public void onChange(Realm realm) {
                adapter = new MessagesAdapter(getWhatsAppChat(),context);
                recyclerView.setAdapter(adapter);
            }
        });
        ImoDb.addChangeListener(new RealmChangeListener<Realm>() {
            @Override
            public void onChange(Realm realm) {
                adapter = new MessagesAdapter(getImoChat(),context);
                recyclerView.setAdapter(adapter);
            }
        });
        MessengerDb.addChangeListener(new RealmChangeListener<Realm>() {
            @Override
            public void onChange(Realm realm) {
                adapter = new MessagesAdapter(getMessengerChat(),context);
                recyclerView.setAdapter(adapter);
            }
        });
        InstaDb.addChangeListener(new RealmChangeListener<Realm>() {
            @Override
            public void onChange(Realm realm) {
                adapter = new MessagesAdapter(getInstaChat(),context);
                recyclerView.setAdapter(adapter);
            }
        });
        ViberDb.addChangeListener(new RealmChangeListener<Realm>() {
            @Override
            public void onChange(Realm realm) {
                adapter = new MessagesAdapter(getViberChat(),context);
                recyclerView.setAdapter(adapter);
            }
        });
        SkypeDb.addChangeListener(new RealmChangeListener<Realm>() {
            @Override
            public void onChange(Realm realm) {
                adapter = new MessagesAdapter(getSkypeChat(),context);
                recyclerView.setAdapter(adapter);
            }
        });
        Realm db = Realm.getInstance(config.AllChat());
        final AllChat obj = db.where(AllChat.class).equalTo("Title", Title).equalTo("AppName", AppName).findFirst();
        db.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if(adapter.getItemCount() > 0) {
                    obj.setCounter(adapter.getItemCount());
                }
                else {
                    obj.deleteFromRealm();
                }
            }
        });
    }

}
