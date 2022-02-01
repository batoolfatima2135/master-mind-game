package com.example.mastermind;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MediumActivity extends AppCompatActivity {
    ImageButton exit,help,back;
    CountDownTimer resume;
  Button btn1,btn2,btn3,btn5,btn6,btn7;
    TextView timer;
    EditText wordbox;
    boolean isRunning = false;
    private long timeRemaining = 0;
    long duration = TimeUnit.MINUTES.toMillis(1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medium);
        getSupportActionBar().hide();
        exit = findViewById(R.id.exit);
        help = findViewById(R.id.help);
        back = findViewById(R.id.back);
        timer = findViewById(R.id.timer);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);

      btn1.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

          }
      });
        CountDownTimer clock = new CountDownTimer(duration, 1000){
            public void onTick(long l){

                String sDuration = String.format(Locale.ENGLISH,"%02d : %02d",TimeUnit.MILLISECONDS.toMinutes(l)
                        ,TimeUnit.MILLISECONDS.toSeconds(l) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l)) );

                timer.setText(sDuration);
                if(l < 20000){
                    timer.setTextColor(getResources().getColor(R.color.error));
                }
                timeRemaining = l;
            }
            public  void onFinish(){
                AlertDialog.Builder builder1 = new AlertDialog.Builder(MediumActivity.this);
                LayoutInflater inflater =getLayoutInflater();
                View dialogView= inflater.inflate(R.layout.gameover, null);

                builder1.setView(dialogView);
                builder1.setCancelable(false);
                Button retry = (Button)dialogView.findViewById(R.id.Retry);
                Button quit = (Button)dialogView.findViewById(R.id.quit);
                TextView score = (TextView)dialogView.findViewById(R.id.score);

                retry.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        MediumActivity.this.recreate();
                    }
                });
                quit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(MediumActivity.this,SelectLevelActivity.class);
                        startActivity(intent);
                        finish();

                    }
                });
                AlertDialog alert11 = builder1.create();
                alert11.show();
            }

        }.start();
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRunning)
                {

                    resume.cancel();
                    isRunning = false;
                }
                else {
                    clock.cancel();
                }
                AlertDialog.Builder builder1 = new AlertDialog.Builder(MediumActivity.this);

                builder1.setView(R.layout.popupview);
                builder1.setCancelable(false);


                builder1.setNegativeButton(
                        "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                isRunning = true;
                                dialog.cancel();


                                resume = new CountDownTimer(timeRemaining, 1000){
                                    public void onTick(long l){

                                        String sDuration = String.format(Locale.ENGLISH,"%02d : %02d",TimeUnit.MILLISECONDS.toMinutes(l)
                                                ,TimeUnit.MILLISECONDS.toSeconds(l) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l)) );

                                        timer.setText(sDuration);
                                        if(l < 20000){
                                            timer.setTextColor(getResources().getColor(R.color.error));
                                        }
                                        timeRemaining = l;

                                    }
                                    public  void onFinish(){
                                        AlertDialog.Builder builder1 = new AlertDialog.Builder(MediumActivity.this);
                                        LayoutInflater inflater =getLayoutInflater();
                                        View dialogView= inflater.inflate(R.layout.gameover, null);

                                        builder1.setView(dialogView);
                                        builder1.setCancelable(false);
                                        Button retry = (Button)dialogView.findViewById(R.id.Retry);
                                        Button quit = (Button)dialogView.findViewById(R.id.quit);
                                        TextView score = (TextView)dialogView.findViewById(R.id.score);

                                        retry.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {

                                                MediumActivity.this.recreate();
                                            }
                                        });
                                        quit.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {

                                                Intent intent = new Intent(MediumActivity.this,SelectLevelActivity.class);
                                                startActivity(intent);
                                                finish();

                                            }
                                        });
                                        AlertDialog alert11 = builder1.create();
                                        alert11.show();
                                    }

                                }.start();

                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();

            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRunning)
                {

                    resume.cancel();
                    isRunning = false;
                }
                else {
                    clock.cancel();
                }
                AlertDialog.Builder builder1 = new AlertDialog.Builder(MediumActivity.this);
                builder1.setMessage("Scores will not save.Are you sure you want to exit?");
                builder1.setCancelable(false);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                finish();
                                moveTaskToBack(true);
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                isRunning = true;
                                dialog.cancel();

                                resume = new CountDownTimer(timeRemaining, 1000){
                                    public void onTick(long l){

                                        String sDuration = String.format(Locale.ENGLISH,"%02d : %02d",TimeUnit.MILLISECONDS.toMinutes(l)
                                                ,TimeUnit.MILLISECONDS.toSeconds(l) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l)) );

                                        timer.setText(sDuration);
                                        if(l < 20000){
                                            timer.setTextColor(getResources().getColor(R.color.error));
                                        }
                                        timeRemaining = l;

                                    }
                                    public  void onFinish(){
                                        AlertDialog.Builder builder1 = new AlertDialog.Builder(MediumActivity.this);
                                        LayoutInflater inflater =getLayoutInflater();
                                        View dialogView= inflater.inflate(R.layout.gameover, null);

                                        builder1.setView(dialogView);
                                        builder1.setCancelable(false);
                                        Button retry = (Button)dialogView.findViewById(R.id.Retry);
                                        Button quit = (Button)dialogView.findViewById(R.id.quit);
                                        TextView score = (TextView)dialogView.findViewById(R.id.score);

                                        retry.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {

                                                MediumActivity.this.recreate();
                                            }
                                        });
                                        quit.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {

                                                Intent intent = new Intent(MediumActivity.this,SelectLevelActivity.class);
                                                startActivity(intent);
                                                finish();

                                            }
                                        });
                                        AlertDialog alert11 = builder1.create();
                                        alert11.show();
                                    }

                                }.start();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRunning)
                {

                    resume.cancel();
                    isRunning = false;
                }
                else {
                    clock.cancel();
                }
                AlertDialog.Builder builder1 = new AlertDialog.Builder(MediumActivity.this);
                builder1.setMessage("Scores will not save.Are you sure you want to go back?");
                builder1.setCancelable(false);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(MediumActivity.this, SelectLevelActivity.class);
                                startActivity(intent);
                                finish();

                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                isRunning = true;
                                dialog.cancel();

                                resume = new CountDownTimer(timeRemaining, 1000){
                                    public void onTick(long l){

                                        String sDuration = String.format(Locale.ENGLISH,"%02d : %02d",TimeUnit.MILLISECONDS.toMinutes(l)
                                                ,TimeUnit.MILLISECONDS.toSeconds(l) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l)) );

                                        timer.setText(sDuration);
                                        if(l < 20000){
                                            timer.setTextColor(getResources().getColor(R.color.error));
                                        }
                                        timeRemaining = l;

                                    }
                                    public  void onFinish(){
                                        AlertDialog.Builder builder1 = new AlertDialog.Builder(MediumActivity.this);
                                        LayoutInflater inflater =getLayoutInflater();
                                        View dialogView= inflater.inflate(R.layout.gameover, null);

                                        builder1.setView(dialogView);
                                        builder1.setCancelable(false);
                                        Button retry = (Button)dialogView.findViewById(R.id.Retry);
                                        Button quit = (Button)dialogView.findViewById(R.id.quit);
                                        TextView score = (TextView)dialogView.findViewById(R.id.score);

                                        retry.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {

                                                MediumActivity.this.recreate();
                                            }
                                        });
                                        quit.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {

                                                Intent intent = new Intent(MediumActivity.this,SelectLevelActivity.class);
                                                startActivity(intent);
                                                finish();

                                            }
                                        });
                                        AlertDialog alert11 = builder1.create();
                                        alert11.show();
                                    }

                                }.start();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();

            }
        });
    }
}