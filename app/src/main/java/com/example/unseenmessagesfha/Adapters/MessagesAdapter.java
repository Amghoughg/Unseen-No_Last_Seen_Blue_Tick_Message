package com.example.unseenmessagesfha.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unseenmessagesfha.Configurations.RealmConfig;
import com.example.unseenmessagesfha.Models.AllChat;
import com.example.unseenmessagesfha.Models.Messages;
import com.example.unseenmessagesfha.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import io.realm.Realm;
import io.realm.RealmResults;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.ViewHolder> {

    private ArrayList<Messages> myData;
    private LayoutInflater inflater;
    private RealmConfig config =new RealmConfig();
    Context context;

    public MessagesAdapter(ArrayList<Messages> myData, Context context) {
        this.myData = myData;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        Realm.init(context);
    }

    @NonNull
    @Override
    public MessagesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=inflater.inflate(R.layout.layout_messages, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MessagesAdapter.ViewHolder holder, final int position) {
        if(myData.size() > 0) {
            holder.txt_title.setText(myData.get(position).getTitle());
            holder.txt_message.setText(myData.get(position).getText());
            holder.txt_timedate.setText(myData.get(position).getTime());
            holder.txt_MonthDay.setText(myData.get(position).getDayMonth());
            byte[] bytes = myData.get(position).getBitmap();
            if (bytes != null) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                holder.avatarImageView.setImageBitmap(bitmap);
            }
            holder.btn_clear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DeleteData(myData.get(position));
                }
            });
        }
    }

    private void DeleteData(Messages messages) {
        switch (messages.getAppName())
        {
            case "com.whatsapp":
            {
                Realm db = Realm.getInstance(config.WhatsappConfig());
                final Messages obj = db.where(Messages.class).equalTo("Title", messages.getTitle())
                        .equalTo("Text",messages.getText())
                        .equalTo("TimeDate",messages.getTimeDate()).findFirst();
                db.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        obj.deleteFromRealm();
                    }
                });
                Toast.makeText(context,"Data Deleted",Toast.LENGTH_SHORT).show();
                break;
            }
            case "com.facebook.orca":
            {
                Realm db = Realm.getInstance(config.MessengerConfig());
                final Messages obj = db.where(Messages.class).equalTo("Title", messages.getTitle())
                        .equalTo("Text",messages.getText())
                        .equalTo("TimeDate",messages.getTimeDate()).findFirst();
                db.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        obj.deleteFromRealm();
                    }
                });
                Toast.makeText(context,"Data Deleted",Toast.LENGTH_SHORT).show();
                break;
            }
            case "com.instagram.android":
            {
                Realm db = Realm.getInstance(config.InstagramConfig());
                final Messages obj = db.where(Messages.class).equalTo("Title", messages.getTitle())
                        .equalTo("Text",messages.getText())
                        .equalTo("TimeDate",messages.getTimeDate()).findFirst();
                db.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        obj.deleteFromRealm();
                    }
                });
                Toast.makeText(context,"Data Deleted",Toast.LENGTH_SHORT).show();
                break;
            }
            case "com.imo.android.imoim":
            {
                Realm db = Realm.getInstance(config.ImoConfig());
                final Messages obj = db.where(Messages.class).equalTo("Title", messages.getTitle())
                        .equalTo("Text",messages.getText())
                        .equalTo("TimeDate",messages.getTimeDate()).findFirst();
                db.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        obj.deleteFromRealm();
                    }
                });
                Toast.makeText(context,"Data Deleted",Toast.LENGTH_SHORT).show();
                break;
            }
            case "com.skype.raider":
            {
                Realm db = Realm.getInstance(config.SkypeConfig());
                final Messages obj = db.where(Messages.class).equalTo("Title", messages.getTitle())
                        .equalTo("Text",messages.getText())
                        .equalTo("TimeDate",messages.getTimeDate()).findFirst();
                db.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        obj.deleteFromRealm();
                    }
                });
                Toast.makeText(context,"Data Deleted",Toast.LENGTH_SHORT).show();
                break;
            }
            case "com.viber.voip":
            {
                Realm db = Realm.getInstance(config.ViberConfig());
                final Messages obj = db.where(Messages.class).equalTo("Title", messages.getTitle())
                        .equalTo("Text",messages.getText())
                        .equalTo("TimeDate",messages.getTimeDate()).findFirst();
                db.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        obj.deleteFromRealm();
                    }
                });
                Toast.makeText(context,"Data Deleted",Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return myData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_title,txt_message,txt_timedate,txt_MonthDay;
        CircleImageView avatarImageView;
        ImageView btn_clear;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_title = itemView.findViewById(R.id.txtView_Title);
            txt_message = itemView.findViewById(R.id.txtView_Messages);
            txt_timedate = itemView.findViewById(R.id.txtView_MessageTime);
            txt_MonthDay = itemView.findViewById(R.id.txtView_MessageDate);
            avatarImageView = itemView.findViewById(R.id.imgView_Avatar);
            btn_clear = itemView.findViewById(R.id.imgView_MessageDeleteBtn);
        }
    }
}
