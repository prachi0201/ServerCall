package com.example.dell_.servercall;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText phone, pswd;
    Button login, signup;

    String strUrl="http://192.168.1.101:5022/api/login";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        phone=(EditText)findViewById(R.id.editText);
        pswd=(EditText)findViewById(R.id.editText2);
        login=(Button)findViewById(R.id.btn1);
        signup=(Button)findViewById(R.id.btn2);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ph=phone.getText().toString();
                String pass=pswd.getText().toString();

                new authenticationViaServer().execute();

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(MainActivity.this, signupActivity.class);
                startActivity(i);
            }
        });
    }

    public class authenticationViaServer extends AsyncTask<String, String, String>{


        @Override
        protected String doInBackground(String... strings) {


            try {
                URL url = new URL(strUrl);
                HttpURLConnection con=(HttpURLConnection)url.openConnection();
                con.setRequestMethod("GET");
                con.connect();

                BufferedReader bf= new BufferedReader(new InputStreamReader(con.getInputStream()));
                String value= bf.readLine();
                System.out.println("The response is " + value);
            }
            catch(Exception e){
                System.out.println(e);

            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

        }
    }






}
