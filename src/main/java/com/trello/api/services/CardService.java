package com.trello.api.services;

import com.trello.api.models.*;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface CardService {

    @GET("cards/{id}")
    Call<Card> getCard(@Path("id") String id);

    @GET("cards/{id}/{field}")
    Call<Card> getField(@Path("id") String id, @Query("field") String field);

    @GET("cards/{id}/members")
    Call<List<Member>> getMembers(@Path("id") String id);

    @GET("cards/{id}/checklists")
    Call<List<Checklist>> getChecklists(@Path("id") String id);

    @GET("cards/{id}/checkItem/{idCheckItem}")
    Call<CheckItem> getCheckItem(@Path("id") String id, @Path("id") String idCheckItem);

    @GET("cards/{id}/attachments/{idAttachment}")
    Call<Attachment> getAttachment(@Path("id") String id, @Path("id") String idAttachment);

    @POST("cards")
    Call<Card> createCard(@Query("name") String name, @Query("idList") String idList);

    @POST("cards")
    Call<Card> createCardNew(@Query("idList") String idList, @Body Card card );

    @POST("cards/{id}/labels")
    Call<ResponseBody> addLabelToCard(@Path("id") String id, @Query("value") String value);

    @DELETE("cards/{id}")
    Call<ResponseBody> deleteCard(@Path("id") String id);

    @PUT("cards/{id}")
    Call<Card> updateCard(@Path("id") String id, @Body Card card);

}
