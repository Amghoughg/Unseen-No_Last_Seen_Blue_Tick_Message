package com.example.unseenmessagesfha.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.unseenmessagesfha.R;

public class ExitScreen extends AppCompatActivity {

    ImageButton Yes,No;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exitscreen);

        Yes = findViewById(R.id.btn_Like);
        No = findViewById(R.id.btn_DisLike);

        Yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finishAffinity();
            }
        });
        No.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExitScreen.super.onBackPressed();
            }
        });
    }
}
