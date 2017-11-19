package com.nexflare.webkiosklibrary.Activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.nexflare.webkiosklibrary.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
    }
    public class task extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }

        @Override
        protected Void doInBackground(Void... params) {


            for(int i=1;i<=3;i++){
                Long time= System.currentTimeMillis();
                while(System.currentTimeMillis()-time<1000);
                Log.e("HELLO",""+System.currentTimeMillis());
            }


            return null;
        }

    }

}
