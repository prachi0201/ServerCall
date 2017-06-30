package com.example.dell_.servercall;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class signupActivity extends AppCompatActivity {
    EditText phone, email, pswd;
    Button btn1, btn2;

    String strUrl="http://192.168.1.101:5022/api/login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        phone=(EditText)findViewById(R.id.editText);
        email=(EditText)findViewById(R.id.editText2);
        pswd=(EditText)findViewById(R.id.editText3);

        btn1=(Button)findViewById(R.id.btn1);
        btn2=(Button)findViewById(R.id.btn2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ph=phone.getText().toString();
                String pass= pswd.getText().toString();
                String mailid=email.getText().toString();
                new registrationViaServer().execute();
               /* Toast.makeText(getApplicationContext(),"Successfully registered",Toast.LENGTH_LONG).show();
*/
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(signupActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

    }

    public class registrationViaServer extends AsyncTask<String, String, String>{

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
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }


    }







}

