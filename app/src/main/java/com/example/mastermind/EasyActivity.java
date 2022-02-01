package com.example.mastermind;

import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.executor.TaskExecutor;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.util.TypedValue;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class EasyActivity extends AppCompatActivity {
    ImageButton exit,help,back;
    String url = "https://magisterial-locatio.000webhostapp.com/easylevel.php";

    Button btn1,btn2,btn3,btn4,btn5,block1,block2,block3,block4,block5,
            btn1previousblock,btn2previousblock,btn3previousblock,
            btn4previousblock,btn5previousblock,previoustarget;
    TextView timer,score;
    int scores=0;
    String previoustext;
    int blockcheck1 = 0;
    int blockcheck2 = 0;
    int blockcheck3 = 0;
    int blockcheck4 = 0;
    int blockcheck5 = 0;
    static int[] BUTTON_IDS;
    static int[] Target_IDS;
    String dbword;
    MediaPlayer tick;
    CountDownTimer resume;
    boolean isRunning = false;
    private long timeRemaining = 0;
    long duration = TimeUnit.MINUTES.toMillis(2);
   String word = "nh aya";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy);
        getSupportActionBar().hide();
        tick = MediaPlayer.create(EasyActivity.this,R.raw.clocktick);
        exit = findViewById(R.id.exit);
        help = findViewById(R.id.help);
        back = findViewById(R.id.back);
        timer = findViewById(R.id.timer);
        score = findViewById(R.id.score);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        block1 = findViewById(R.id.block1);
        block2 = findViewById(R.id.block2);
        block3 = findViewById(R.id.block3);
        block4 = findViewById(R.id.block4);
        block5 = findViewById(R.id.block5);


        BUTTON_IDS =new int[] {
           block1.getId(),
                block2.getId(),
                block3.getId(), block4.getId(), block5.getId()

        };
        Target_IDS =new int[] {
                btn1.getId(),
                btn2.getId(),
                btn3.getId(), btn4.getId(), btn5.getId()

        };

        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                   public void onResponse(String response) {
                       try {
                        JSONObject jsonObject = new JSONObject(response);
                           JSONArray jsonArray = jsonObject.getJSONArray()

                    }
                       catch (JSONException e){}

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
        } );
            RequestQueue requestQueue = Volley.newRequestQueue(EasyActivity.this);
//                requestQueue.add(request);

        // COUNTDOWN FOR 2 MINUES //
        CountDownTimer clock =   new CountDownTimer(duration, 1000){
            public void onTick(long l){

                String sDuration = String.format(Locale.ENGLISH,"%02d : %02d",TimeUnit.MILLISECONDS.toMinutes(l)
                        ,TimeUnit.MILLISECONDS.toSeconds(l) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l)) );

                timer.setText(sDuration);
                if(l < 30000){
                    timer.setTextColor(getResources().getColor(R.color.error));
                    tick.start();
                }
                timeRemaining = l;
            }
            public  void onFinish(){
                MediaPlayer ring= MediaPlayer.create(EasyActivity.this,R.raw.gameover);
                ring.start();
                tick.stop();
                AlertDialog.Builder builder1 = new AlertDialog.Builder(EasyActivity.this);
                LayoutInflater inflater =getLayoutInflater();
                View dialogView= inflater.inflate(R.layout.gameover, null);

                builder1.setView(dialogView);
                builder1.setCancelable(false);
                Button retry = (Button)dialogView.findViewById(R.id.Retry);
                Button quit = (Button)dialogView.findViewById(R.id.quit);
                TextView scored = (TextView)dialogView.findViewById(R.id.score);
                scored.setText(String.valueOf(scores));

                retry.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MediaPlayer ring= MediaPlayer.create(EasyActivity.this,R.raw.wordclick);
                        ring.start();

                        EasyActivity.this.recreate();
                    }
                });
                quit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MediaPlayer ring= MediaPlayer.create(EasyActivity.this,R.raw.wordclick);
                        ring.start();
                        Intent intent = new Intent(EasyActivity.this,SelectLevelActivity.class);
                        startActivity(intent);
                        finish();

                    }
                });
                AlertDialog alert11 = builder1.create();
                alert11.show();
            }

        }.start();

        //HELP BUTTON LISTNER
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MediaPlayer ring= MediaPlayer.create(EasyActivity.this,R.raw.wordclick);
                ring.start();
                if (isRunning)
                {

                    resume.cancel();
                    isRunning = false;
                }
                else {
                    clock.cancel();
                }
                AlertDialog.Builder builder1 = new AlertDialog.Builder(EasyActivity.this);

                builder1.setView(R.layout.popupview);
                builder1.setCancelable(false);


                builder1.setNegativeButton(
                        "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id){

                                isRunning = true;
                                dialog.cancel();


                                resume = new CountDownTimer(timeRemaining, 1000){
                                    public void onTick(long l){

                                        String sDuration = String.format(Locale.ENGLISH,"%02d : %02d",TimeUnit.MILLISECONDS.toMinutes(l)
                                                ,TimeUnit.MILLISECONDS.toSeconds(l) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l)) );

                                        timer.setText(sDuration);
                                        if(l < 20000){
                                            timer.setTextColor(getResources().getColor(R.color.error));
                                            tick.start();
                                        }
                                        timeRemaining = l;

                                    }
                                    public  void onFinish(){
                                        AlertDialog.Builder builder1 = new AlertDialog.Builder(EasyActivity.this);
                                        LayoutInflater inflater =getLayoutInflater();
                                        View dialogView= inflater.inflate(R.layout.gameover, null);
                                        MediaPlayer ring= MediaPlayer.create(EasyActivity.this,R.raw.gameover);
                                        tick.stop();
                                        ring.start();

                                        builder1.setView(dialogView);
                                        builder1.setCancelable(false);
                                        Button retry = (Button)dialogView.findViewById(R.id.Retry);
                                        Button quit = (Button)dialogView.findViewById(R.id.quit);
                                        TextView scored = (TextView)dialogView.findViewById(R.id.score);
                                        scored.setText(String.valueOf(scores));
                                        retry.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                MediaPlayer ring= MediaPlayer.create(EasyActivity.this,R.raw.wordclick);
                                                ring.start();
                                                EasyActivity.this.recreate();
                                            }
                                        });
                                        quit.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                MediaPlayer ring= MediaPlayer.create(EasyActivity.this,R.raw.wordclick);
                                                ring.start();
                                                Intent intent = new Intent(EasyActivity.this,SelectLevelActivity.class);
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

        //EXIT BUTTON LISTNER
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer ring= MediaPlayer.create(EasyActivity.this,R.raw.wordclick);
                ring.start();

                if (isRunning)
                {

                    resume.cancel();
                    isRunning = false;
                }
                else {
                    clock.cancel();
                }
                AlertDialog.Builder builder1 = new AlertDialog.Builder(EasyActivity.this);
                builder1.setMessage("Scores will not save.Are you sure you want to exit?");
                builder1.setCancelable(false);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                tick.stop();
                                MediaPlayer ring= MediaPlayer.create(EasyActivity.this,R.raw.wordclick);
                                ring.start();
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
                                            tick.start();
                                        }
                                        timeRemaining = l;

                                    }
                                    public  void onFinish(){

                                        MediaPlayer ring= MediaPlayer.create(EasyActivity.this,R.raw.gameover);
                                        tick.stop();
                                        ring.start();
                                        AlertDialog.Builder builder1 = new AlertDialog.Builder(EasyActivity.this);
                                        LayoutInflater inflater =getLayoutInflater();
                                        View dialogView= inflater.inflate(R.layout.gameover, null);

                                        builder1.setView(dialogView);
                                        builder1.setCancelable(false);
                                        Button retry = (Button)dialogView.findViewById(R.id.Retry);
                                        Button quit = (Button)dialogView.findViewById(R.id.quit);
                                        TextView scored = (TextView)dialogView.findViewById(R.id.score);
                                        scored.setText(String.valueOf(scores));
                                        retry.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                MediaPlayer ring= MediaPlayer.create(EasyActivity.this,R.raw.wordclick);
                                                ring.start();
                                                EasyActivity.this.recreate();
                                            }
                                        });
                                        quit.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                MediaPlayer ring= MediaPlayer.create(EasyActivity.this,R.raw.wordclick);
                                                ring.start();
                                                Intent intent = new Intent(EasyActivity.this,SelectLevelActivity.class);
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

        //BACK BUTTON LISTNER
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MediaPlayer ring= MediaPlayer.create(EasyActivity.this,R.raw.wordclick);
                ring.start();
                if (isRunning)
                {

                    resume.cancel();
                    isRunning = false;
                }
                else {
                    clock.cancel();
                }
                AlertDialog.Builder builder1 = new AlertDialog.Builder(EasyActivity.this);
                builder1.setMessage("Scores will not save.Are you sure you want to go back?");
                builder1.setCancelable(false);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                tick.stop();
                                Intent intent = new Intent(EasyActivity.this, SelectLevelActivity.class);
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
                                            tick.start();
                                        }
                                        timeRemaining = l;

                                    }
                                    public  void onFinish(){
                                        AlertDialog.Builder builder1 = new AlertDialog.Builder(EasyActivity.this);
                                        LayoutInflater inflater =getLayoutInflater();
                                        View dialogView= inflater.inflate(R.layout.gameover, null);
                                        MediaPlayer ring= MediaPlayer.create(EasyActivity.this,R.raw.gameover);
                                        tick.stop();
                                        ring.start();
                                        builder1.setView(dialogView);
                                        builder1.setCancelable(false);
                                        Button retry = (Button)dialogView.findViewById(R.id.Retry);
                                        Button quit = (Button)dialogView.findViewById(R.id.quit);
                                        TextView scored = (TextView)dialogView.findViewById(R.id.score);
                                        scored.setText(String.valueOf(scores));
                                        retry.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                MediaPlayer ring= MediaPlayer.create(EasyActivity.this,R.raw.wordclick);
                                                ring.start();
                                                EasyActivity.this.recreate();
                                            }
                                        });
                                        quit.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                MediaPlayer ring= MediaPlayer.create(EasyActivity.this,R.raw.wordclick);
                                                ring.start();
                                                Intent intent = new Intent(EasyActivity.this,SelectLevelActivity.class);
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
        //HINT DIALOGE
        LayoutInflater inflater =getLayoutInflater();
        AlertDialog.Builder hintdialoge = new AlertDialog.Builder(EasyActivity.this);
        View dialogView= inflater.inflate(R.layout.showhint, null);
        hintdialoge.setView(dialogView);
        hintdialoge.setCancelable(false);
        AlertDialog  alert11 = hintdialoge.create();
        String hintvalue = "Large body of water";

        //HINT TIMER
        CountDownTimer hint = new CountDownTimer(5000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {




                TextView hinttext = dialogView.findViewById(R.id.hint);
                TextView hinttimer = dialogView.findViewById(R.id.timer);
                hinttext.setText(hintvalue);
                String finish = String.valueOf(millisUntilFinished/1000 + 1);
                hinttimer.setText(finish);

                alert11.show();
            }

            @Override
            public void onFinish() {
                alert11.dismiss();
            }
        }.start();


        btn1.setOnDragListener(dragListener);
        btn2.setOnDragListener(dragListener);
        btn3.setOnDragListener(dragListener);
        btn4.setOnDragListener(dragListener);
        btn5.setOnDragListener(dragListener);

        block1.setOnLongClickListener(longClickListener);
        block2.setOnLongClickListener(longClickListener);
        block3.setOnLongClickListener(longClickListener);
        block4.setOnLongClickListener(longClickListener);
        block5.setOnLongClickListener(longClickListener);

       dbword = "ocean";



    }

    //LONGCLICK LISNE
    View.OnLongClickListener longClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            MediaPlayer ring= MediaPlayer.create(EasyActivity.this,R.raw.wordclick);
            ring.start();
            ClipData data = ClipData.newPlainText("","");
            View.DragShadowBuilder myshadowbuilder = new View.DragShadowBuilder(v);
            v.startDrag(data,myshadowbuilder,v,0)  ;
            return true;
        }
    };

    //DRAG LISTNER
     View.OnDragListener dragListener = new View.OnDragListener() {
       @Override
       public boolean onDrag(View v, DragEvent event) {
           int dragevent = event.getAction();
           final View view =(View) event.getLocalState();
         Button btn = findViewById(v.getId() ) ;
            Button block = findViewById(view.getId() ) ;

          switch (dragevent){
           case DragEvent.ACTION_DRAG_ENTERED:
               previoustext =(String)btn.getText();
                btn.setText("");
              btn.setBackground(getResources().getDrawable(R.drawable.btn));
                 break;
          case DragEvent.ACTION_DRAG_EXITED:
          if(previoustext.isEmpty())
          {
              btn.setText("");
              btn.setBackground(getResources().getDrawable(R.drawable.btn));
          }
          else {
              btn.setBackground(getResources().getDrawable(R.drawable.background));
              btn.setText(previoustext);}
              break;

          case DragEvent.ACTION_DROP:
//              if(blockcheck1 > 1)
//                  btn1previousblock.setVisibility(View.VISIBLE);
//              else if(blockcheck2> 1)
//                  btn2previousblock.setVisibility(View.VISIBLE);
//              else if(blockcheck3> 1)
//                  btn3previousblock.setVisibility(View.VISIBLE);
//              else if(blockcheck4> 1)
//                  btn4previousblock.setVisibility(View.VISIBLE);
//              else
//                  btn5previousblock.setVisibility(View.VISIBLE);

              btn.setText(block.getText());
              btn.setBackground(getResources().getDrawable(R.drawable.background));
              int ids = v.getId();
//              if(ids == R.id.btn1)
//              {
//              btn1previousblock = findViewById(view.getId());
//              blockcheck1++;
//
//              }
//              if(ids == R.id.btn2)
//              {
//                  btn2previousblock = findViewById(view.getId());
//                  blockcheck2++;
//              }
//              if(ids == R.id.btn3)
//              {
//                  btn3previousblock = findViewById(view.getId());
//                  blockcheck3++;
//              }
//              if(ids == R.id.btn4)
//              {
//                  btn4previousblock = findViewById(view.getId());
//                  blockcheck4++;
//              }
//            if(ids == R.id.btn5)
//              {
//                  btn5previousblock = findViewById(view.getId());
//                  blockcheck5++;
//              }
//
//              block.setVisibility(View.INVISIBLE);

              word = (String)btn1.getText()+btn2.getText()+btn3.getText()+btn4.getText()+btn5.getText();
              Toast.makeText(EasyActivity.this, word, Toast.LENGTH_SHORT).show();

              if(word.equals("ocean")){
                  for (int i=0; i<Target_IDS.length; i++) {
                      int id = Target_IDS[i];
                      Button target = findViewById(id);
                      target.setBackground(getResources().getDrawable(R.drawable.correct));
                      target.setTextColor(getResources().getColor(R.color.white));
                  }

                  scores +=10;

                  score.setText(String.valueOf(scores));
                  CountDownTimer scorecolor = new CountDownTimer(1000,1000) {
                      @Override
                      public void onTick(long millisUntilFinished) {
                          score.setTextColor(getResources().getColor(R.color.correct));
                          score.setTextSize(TypedValue.COMPLEX_UNIT_SP,25);



                      }

                      @Override
                      public void onFinish() {
                          score.setTextColor(getResources().getColor(R.color.white));
                          score.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
                      }
                  }.start();
                  MediaPlayer ring= MediaPlayer.create(EasyActivity.this,R.raw.correct);
                  ring.start();

              }
              else {
                  if(word.length() == 5)
                  {
                      CountDownTimer error = new CountDownTimer(1000,1000) {
                          @Override
                          public void onTick(long millisUntilFinished) {
                              for (int i=0; i<Target_IDS.length; i++) {
                                  int id = Target_IDS[i];
                                  Button target = findViewById(id);
                                  target.setBackground(getResources().getDrawable(R.drawable.incorrect));
                                  target.setTextColor(getResources().getColor(R.color.white));
                              }
                              Vibrator vib = (Vibrator) getSystemService(EasyActivity.this.VIBRATOR_SERVICE);
                              vib.vibrate(500);
                              MediaPlayer ring= MediaPlayer.create(EasyActivity.this,R.raw.errorsound);
                              ring.start();
                          }

                          @Override
                          public void onFinish() {
                              for (int i=0; i<Target_IDS.length; i++) {
                                  int id = Target_IDS[i];
                                  Button target = findViewById(id);
                                  target.setBackground(getResources().getDrawable(R.drawable.btn));
                                  target.setText("");
                                  target.setTextColor(getResources().getColor(R.color.orange));
                              }
                          }
                      }.start();




                      String[] strSplit = dbword.split("");

                      // Now convert string into ArrayList
                      ArrayList<String> strList = new ArrayList<String>(Arrays.asList(strSplit));
                      Collections.shuffle(strList);
                      for (String a : strList) {
                          int index= strList.indexOf(a);
                          int id = BUTTON_IDS[index];
                          Button target = findViewById(id);
                          target.setText(a);
                      }


                      }

              }
              break;
            }
           return true;
        }
   };
}