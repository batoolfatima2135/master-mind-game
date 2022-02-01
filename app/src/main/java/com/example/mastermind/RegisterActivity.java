package com.example.mastermind;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
EditText name,age,username;
Button btn;
String url="https://magisterial-locatio.000webhostapp.com/usernamecheck.php";
String inserturl="https://magisterial-locatio.000webhostapp.com/insert.php";

TextInputLayout agelayout, namelayout,usernamelayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register);
        name = findViewById(R.id.nametext);
        namelayout =  findViewById(R.id.name);
        usernamelayout = findViewById(R.id.username);
        age = findViewById(R.id.agetext);
        agelayout = findViewById(R.id.age);
        username = findViewById(R.id.usernametext);
        btn = findViewById(R.id.button);


        age.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > agelayout.getCounterMaxLength())
                    agelayout.setError("Max character length is " + agelayout.getCounterMaxLength());
                else
                    agelayout.setError(null);
            }
        });
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > usernamelayout.getCounterMaxLength())
                    usernamelayout.setError("Max character length is " + usernamelayout.getCounterMaxLength());
//                final ProgressBar simpleProgressBar = (ProgressBar) findViewById(R.id.usernameprogressbar);
//                simpleProgressBar.setVisibility(View.VISIBLE);
//                String Uusername = username.getText().toString();
//                StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        simpleProgressBar.setVisibility(View.INVISIBLE);
//                        usernamelayout.setError(response);
//                        btn.setEnabled(false);
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        usernamelayout.setError(error.getMessage());
//                    }
//                }){
//
//                    @Nullable
//                    @Override
//                    protected Map<String, String> getParams() throws AuthFailureError {
//
//                        Map<String,String> prams=new HashMap<String,String>();
//                        prams.put("username",Uusername);
//
//                        return prams;
//
//                    }
//                };
//
//                RequestQueue requestQueue = Volley.newRequestQueue(RegisterActivity.this);
//                requestQueue.add(request);






            }
        });
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > namelayout.getCounterMaxLength())
                    namelayout.setError("Max character length is " + namelayout.getCounterMaxLength());
                else
                    namelayout.setError(null);
            }
        });
    }


    public void AddUser(View view) {
        String Uname = name.getText().toString();
        String Uage = age.getText().toString();
        String Uusername = username.getText().toString();
        if(Uname.length()==0)
        {
            name.setError("Name is required");
        }
        else if(Uage.length()==0)
        {
            age.setError("age is required");
        }
        else if(Uusername.length()==0)
        {
            username.setError("username is required");
        }
        else
        {



                final ProgressBar simpleProgressBar = (ProgressBar) findViewById(R.id.progressBar);
                simpleProgressBar.setVisibility(View.VISIBLE);
                StringRequest requests=new StringRequest(Request.Method.POST, inserturl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                       if(response.equals("a")){
                           simpleProgressBar.setVisibility(View.INVISIBLE);
                           usernamelayout.setError("Username already exist");
                       }
                       else{
                           simpleProgressBar.setVisibility(View.INVISIBLE);

                           SharedPreferences data = getSharedPreferences("data",MODE_PRIVATE);
                           SharedPreferences.Editor editor = data.edit();
                           editor.putString("Username",Uusername);
                           editor.commit();


                        Intent intent = new Intent(RegisterActivity.this,SelectLevelActivity.class);

                          startActivity(intent);
                          finish();

                       }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        simpleProgressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){

                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String,String> prams=new HashMap<String,String>();
                        prams.put("name",Uname);
                        prams.put("username",Uusername);
                        prams.put("age",Uage);
                        return prams;

                    }
                };

                RequestQueue requestsQueue = Volley.newRequestQueue(RegisterActivity.this);
                requestsQueue.add(requests);
            


        }

}

    public void back(View view) {
        Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
        startActivity(intent);
    }
}