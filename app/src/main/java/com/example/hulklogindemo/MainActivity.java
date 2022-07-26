package com.example.hulklogindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextView tv,tv2;
    EditText uname,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.tv);
        tv2=findViewById(R.id.tv3);
        uname=findViewById(R.id.name);
        pass=findViewById(R.id.pass);




    }

    public void process() {
        Call<ModelClass> call=ApiController
                .getInstance()
                .getApi()
                .getData();
        call.enqueue(new Callback<ModelClass>() {
            @Override
            public void onResponse(Call<ModelClass> call, Response<ModelClass> response) {

                if(!response.isSuccessful()) return;

                ModelClass data=response.body();
                String nonce=data.getNonce();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText("nonce id "+nonce);
                    }
                });
                process2();
            }
            @Override
            public void onFailure(Call<ModelClass> call, Throwable t) {
            }
        });
    }
    public void process2() {
        String username= uname.getText().toString();
        String password=pass.getText().toString();
        Call<ModelClassLogin> call= ApiController
                .getInstance()
                .getApi()
                .getLoginInformation(username,password);
        call.enqueue(new Callback<ModelClassLogin>() {
            @Override
            public void onResponse(Call<ModelClassLogin> call, Response<ModelClassLogin> response) {
                ModelClassLogin data2=response.body();
                String status=data2.getStatus();
                String error=data2.getError();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv2.setText("status "+status+" \n"+error);
                    }
                });
            }

            @Override
            public void onFailure(Call<ModelClassLogin> call, Throwable t) {

            }
        });
    }

    public void login(View view) {

        process();

    }
}