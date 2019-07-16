package com.trello.api.services;

import com.trello.api.models.CheckItem;
import com.trello.api.models.Checklist;
import com.trello.api.models.TrelloList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CheckListService {

    @GET("checklists/{id}")
    Call<Checklist> getCheckList(@Path("id") String id);

    @GET("checklists/{id}/checkItems/{idCheckItem}")
    Call<CheckItem> getCheckItem(@Path("id") String id, @Path("id") String idCheckItem);
}
