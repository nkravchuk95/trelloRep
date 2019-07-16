package com.trello.api.models;

import java.util.Date;

public class Attachment {

    private String id;
    private int bytes;
    private Date date;
    private String idMember;
    private boolean isUpload;
    private String mimeType;
    private String name;
    private String url;

    public Attachment() {}

    public Attachment(String url) {
        this.url = url;
    }

}
