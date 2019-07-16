package com.trello.api.models;

import java.util.List;

public class Checklist {

    public String id;
    public String name;
    public String idBoard;
    public String idCard;
    public int position;
    public List<CheckItem> checkItems;
    public List<Card> cards;
    public int pos;

}
