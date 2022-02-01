package com.example.mastermind;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

   Button help , close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        help = findViewById(R.id.help);
        help.setBackground(getResources().getDrawable(R.drawable.background));



        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);

                builder1.setView(R.layout.popupview);
                builder1.setCancelable(true);



                builder1.setNegativeButton(
                        "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });

    }

    public void play(View view) {
        SharedPreferences data = getSharedPreferences("data",MODE_PRIVATE);
        String username = data.getString("Username" , "nothing");
        if(username.equals("nothing"))
        {
            Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            Intent intent = new Intent(MainActivity.this,SelectLevelActivity.class);
            startActivity(intent);
            finish();
        }

    }


    public void Exit(View view) {

        finish();
        moveTaskToBack(true);
    }

    public void level(View view) {
        SharedPreferences data = getSharedPreferences("data",MODE_PRIVATE);
        String username = data.getString("Username" , "nothing");
        if(username.equals("nothing"))
        {
            Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            Intent intent = new Intent(MainActivity.this,SelectLevelActivity.class);
            startActivity(intent);
            finish();
        }

    }
}