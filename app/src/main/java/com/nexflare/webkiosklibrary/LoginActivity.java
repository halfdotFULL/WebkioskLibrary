package com.nexflare.webkiosklibrary;

import android.app.ProgressDialog;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.nexflare.webkiosklibrary.Apis.Thapar.Login.LoginResult;
import com.nexflare.webkiosklibrary.Interface.ResultCallback;
import com.nexflare.webkiosklibrary.Utils.Colleges;

import info.hoang8f.widget.FButton;


public class LoginActivity extends AppCompatActivity {
    Spinner collegeSelect;
    EditText enroll,password,dob;
    TextInputLayout dobTil;
    FButton loginButton;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        collegeSelect= (Spinner) findViewById(R.id.colg_select);
        enroll= (EditText) findViewById(R.id.enroll_num);
        password= (EditText) findViewById(R.id.password);
        dob= (EditText) findViewById(R.id.dob_select);
        dobTil= (TextInputLayout) findViewById(R.id.dobTil);
        loginButton= (FButton) findViewById(R.id.login);
        progressDialog=new ProgressDialog(this,ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Please wait...");
        ArrayAdapter<String> collegeAdapter=new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                new String[]{Colleges.JIIT,Colleges.J128,"THAPAR"});
        collegeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        collegeSelect.setAdapter(collegeAdapter);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                if(collegeSelect.getSelectedItem().toString().equals("THAPAT")){
                    ThaparWebkiosk.getLoginApi().login(enroll.getText().toString(),
                            dob.getText().toString(),
                            password.getText().toString(),
                            collegeSelect.getSelectedItem().toString())
                            .addResultCallback(new ResultCallback<LoginResult>() {
                                @Override
                                public void onResult(LoginResult result) {
                                    progressDialog.dismiss();
                                    Toast.makeText(LoginActivity.this, ""+result.isValidCredentials(), Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onError(Exception e) {
                                    progressDialog.dismiss();
                                    Toast.makeText(LoginActivity.this, "Some error occurred "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                }
                else{
                    JaypeeWebkiosk.getLoginApi().login(enroll.getText().toString(),
                            dob.getText().toString(),
                            password.getText().toString(),
                            collegeSelect.getSelectedItem().toString())
                            .addResultCallback(new ResultCallback<com.nexflare.webkiosklibrary.Apis.Jiit.Login.LoginResult>() {
                                @Override
                                public void onResult(com.nexflare.webkiosklibrary.Apis.Jiit.Login.LoginResult result) {
                                    progressDialog.dismiss();
                                    Toast.makeText(LoginActivity.this, ""+result.isValidCredentials(), Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onError(Exception e) {
                                    progressDialog.dismiss();
                                    Toast.makeText(LoginActivity.this, "Some error occurred "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });
    }
}
