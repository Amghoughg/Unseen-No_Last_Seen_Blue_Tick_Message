package com.example.unseenmessagesfha.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.unseenmessagesfha.Configurations.RealmConfig;
import com.example.unseenmessagesfha.Fragments.FragmentWhatsApp;
import com.example.unseenmessagesfha.Models.AllChat;
import com.example.unseenmessagesfha.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import io.realm.Realm;
import io.realm.RealmResults;

public class AllChatAdapter extends RecyclerView.Adapter<AllChatAdapter.ViewHolder> {

    private RealmResults<AllChat> myData;
    private LayoutInflater inflater;
    private ItemClickListener mClickListener;
    Fragment fragment;
    Context context;
    public AllChatAdapter(RealmResults<AllChat> myData, Context context) {
        this.myData = myData;
        this.context = context;
        inflater = LayoutInflater.from(context);
        Realm.init(context);
    }

    @NonNull
    @Override
    public AllChatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.layout_mainchat,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AllChatAdapter.ViewHolder holder, final int position) {
        RealmConfig config = new RealmConfig();
        //final Realm db = Realm.getInstance(config.AllChat());
        holder.Title.setText(String.valueOf(myData.get(position).getTitle()));
        holder.counter.setText("(" + String.valueOf(myData.get(position).getCounter()) + ")");
        byte[] bytes = myData.get(position).getAvatar();
        if (bytes != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            holder.Avatar.setImageBitmap(bitmap);
        }
    }

    @Override
    public int getItemCount() {
        return myData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CircleImageView Avatar;
        TextView Title,counter;
        ConstraintLayout constraintLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Avatar =(CircleImageView) itemView.findViewById(R.id.imgView_MainAvatar);
            Title =(TextView) itemView.findViewById(R.id.txtView_MainTitle);
            counter = (TextView) itemView.findViewById(R.id.txtView_MainCounter);
            constraintLayout = itemView.findViewById(R.id.constraintLayoutMain);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (mClickListener != null) mClickListener.onItemClick(v, myData.get(getAdapterPosition()));
        }
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener{
        void onItemClick(View v, AllChat obj);
    }
}
