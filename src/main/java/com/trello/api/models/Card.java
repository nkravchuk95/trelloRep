package com.trello.api.models;

import java.util.Date;
import java.util.List;

public class Card {
    public String id;
    public String desc;
    public String idBoard;
    public String idList;
    public String name;
    public String url;
    public String due;
    public List<String> idMembers;
    public List<Label> labels;
    public List<CheckItem> checkItemStates;
    public boolean closed;
    public Date dateLastActivity;
    public List<String> idChecklists;
    public List<String> idMembersVoted;
    public String idShort;
    public String idAttachmentCover;
    public boolean manualCoverAttachment;
    public int pos;
    public String shortLink;
    public String shortUrl;
    public boolean subscribed;

    public Card(){}

    public Card(String name){
        this.name = name;
    }
    @Override
    public String toString() {
        return "Card{" +
                "id='" + id + '\'' +
                ", desc='" + desc + '\'' +
                ", idBoard='" + idBoard + '\'' +
                ", idList='" + idList + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
