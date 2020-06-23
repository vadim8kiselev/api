package com.kiselev.instagram.model;

import lombok.Data;

@Data
public class InstagramCommentary {

    private Long pk;

    private Long userId;

    private Long mediaId;

    private Integer type;

    private String text;

    private String createdAt;

    private String createdAtUTC;

    private Integer commentLikeCount;

    private String contentType;

    private String status;

    private Integer bitFlags;

    private Boolean didReportAsSpam;

    private Boolean shareEnabled;
}
