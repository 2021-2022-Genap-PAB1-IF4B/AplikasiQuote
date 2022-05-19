package com.if4b.aplikasiquote.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.if4b.aplikasiquote.API.APIRequestData;
import com.if4b.aplikasiquote.API.RetroServer;
import com.if4b.aplikasiquote.Adapter.AdapterQuote;
import com.if4b.aplikasiquote.Model.QuoteModel;
import com.if4b.aplikasiquote.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvQuote;
    private ProgressBar pbQuote;
    private List<QuoteModel> listQuote;
    private AdapterQuote adapterQuote;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        rvQuote.setLayoutManager(linearLayoutManager);

        retrieveQuote();
    }

    private void initView(){
        rvQuote = findViewById(R.id.rv_quote);
        pbQuote = findViewById(R.id.pb_quote);
    }

    private void retrieveQuote(){
        pbQuote.setVisibility(View.VISIBLE);

        APIRequestData ardData = RetroServer.connectRetrofit().create(APIRequestData.class);
        Call<List<QuoteModel>> process = ardData.ardGet();

        process.enqueue(new Callback<List<QuoteModel>>() {
            @Override
            public void onResponse(Call<List<QuoteModel>> call, Response<List<QuoteModel>> response) {
                listQuote = response.body();
                adapterQuote = new AdapterQuote(listQuote);
                rvQuote.setAdapter(adapterQuote);
                pbQuote.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<QuoteModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Gagal Menghubungi Server: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                pbQuote.setVisibility(View.GONE);
            }
        });
    }
}