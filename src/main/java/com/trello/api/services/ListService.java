package com.trello.api.services;

import com.trello.api.models.TrelloList;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface ListService {

    @GET("lists/{id}")
    Call<TrelloList> getList(@Path("id") String id);

    @POST("lists")
    Call<TrelloList> createList(@Query("name") String name, @Query("idBoard") String idBoard);

    @DELETE("lists/{id}")
    Call<ResponseBody> deleteList(@Path("id") String id);

    @PUT("lists/{id}")
    Call<TrelloList> updateList(@Path("id") String id, @Body TrelloList trelloList);


}
