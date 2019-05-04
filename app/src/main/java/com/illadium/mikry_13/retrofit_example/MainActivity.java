package com.illadium.mikry_13.retrofit_example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    EditText et;
    Button translateBt;
    Call<YaTranslate> translation;
    YandexTranslateInterface service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        et = findViewById(R.id.et);
        translateBt = findViewById(R.id.translateBt);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://translate.yandex.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(YandexTranslateInterface.class);


        translateBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("");

                if (et.getText() != null)
                    setTranslation(et.getText().toString(), "en-ru");
                else
                    tv.setText("We can't translate this, sorry.");
            }
        });

    }

    private void setTranslation(String TextToTranslate, String TranslateDirection)
    {

        translation = service.translation(TextToTranslate, TranslateDirection);

        translation.enqueue(new Callback<YaTranslate>() {
            @Override
            public void onResponse(Call<YaTranslate> call, Response<YaTranslate> response) {
                YaTranslate translation_ = response.body();
                //tv.append(response.toString());

                try {
                    for (String tr : translation_.getText())
                        tv.append(tr);
                }catch (NullPointerException e)
                {
                    tv.setText("Something went wrong, sorry");
                }
            }

            @Override
            public void onFailure(Call<YaTranslate> call, Throwable t) {

            }
        });
    }

}