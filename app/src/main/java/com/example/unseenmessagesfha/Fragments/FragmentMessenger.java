package com.example.unseenmessagesfha.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.unseenmessagesfha.Adapters.AllChatAdapter;
import com.example.unseenmessagesfha.Configurations.RealmConfig;
import com.example.unseenmessagesfha.Models.AllChat;
import com.example.unseenmessagesfha.R;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMessenger extends Fragment {

    private RecyclerView recyclerView;
    private AllChatAdapter adapter;
    private RealmConfig config=new RealmConfig();
    private Realm db;

    public FragmentMessenger() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_messenger, container, false);
        Realm.init(v.getContext());

        recyclerView = v.findViewById(R.id.recyclerView_Messenger);


        db = Realm.getInstance(config.AllChat());
        db.addChangeListener(new RealmChangeListener<Realm>() {
            @Override
            public void onChange(Realm realm) {
                dataUpdated();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        adapter = new AllChatAdapter(getAll(),v.getContext());
        adapter.setClickListener(new AllChatAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View v, AllChat obj) {

                OpenChat(obj);
            }
        });
        recyclerView.setAdapter(adapter);

        return v;
    }
    private void dataUpdated() {
        adapter.notifyDataSetChanged();
    }
    private RealmResults<AllChat> getAll()
    {
        return db.where(AllChat.class).equalTo("AppName","com.facebook.orca").sort("DateTime", Sort.DESCENDING).findAll();
    }
    private void OpenChat(AllChat obj) {
        FragOpenChat fragment = new FragOpenChat();
        Bundle args = new Bundle();
        args.putString("AppName",obj.getAppName());
        args.putString("Title",obj.getTitle());
        fragment.setArguments(args);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout,fragment,"Next Fragment")
                .addToBackStack(null)
                .commit();
    }
}
