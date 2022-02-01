package com.example.mastermind;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class SelectLevelActivity extends AppCompatActivity {
ImageButton exit,help,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_level);
        getSupportActionBar().hide();
        exit = findViewById(R.id.exit);
        help = findViewById(R.id.help);
        back = findViewById(R.id.back);
//        SharedPreferences data = getSharedPreferences("data",MODE_PRIVATE);
//        String username = data.getString("Username" , "nothing");
//        Toast.makeText(SelectLevelActivity.this, username, Toast.LENGTH_SHORT).show();
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                moveTaskToBack(true);
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(SelectLevelActivity.this);

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
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectLevelActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void hard(View view) {
        Intent intent = new Intent(SelectLevelActivity.this,HardActivity.class);
        startActivity(intent);
        finish();
    }

    public void medium(View view) {
        Intent intent = new Intent(SelectLevelActivity.this,MediumActivity.class);
        startActivity(intent);
        finish();
    }

    public void easy(View view) {
        Intent intent = new Intent(SelectLevelActivity.this,EasyActivity.class);
        startActivity(intent);
        finish();
    }
}