package com.if4b.aplikasiquote.API;

import com.if4b.aplikasiquote.Model.QuoteModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIRequestData {
    @GET("quotes")
    Call<List<QuoteModel>> ardGet();
}
