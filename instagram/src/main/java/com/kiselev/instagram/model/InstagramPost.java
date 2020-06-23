package com.kiselev.instagram.model;

import lombok.Data;
import org.brunocvcunha.instagram4j.requests.payload.*;

import java.util.List;
import java.util.Map;

@Data
public class InstagramPost {

    private Long pk;
    private String id;

    private String takenAt;
    private Long deviceTimestamp;
    
    private Integer mediaType;
    
    private Integer filterType;
    private Boolean hasAudio;
    private Double videoDuration;
    
    private Map<String, Object> attribution;

    private InstagramPhoto mainPhoto;
    
    private List<InstagramPhoto> photos;
    
    private List<InstagramProfile> userTags;
    
    private FeedItemLocation location;
    private Float lng;
    private Float lat;
    
    private Integer originalWidth;
    private Integer originalHeight;
    
    private Integer viewCount;
    
    private List<InstagramPost> carouselMedia;

    private Integer likeCount;
    private Integer commentCount;
    
    private List<InstagramProfile> likers;
    private List<InstagramCommentary> commentaries;
    
    private InstagramCommentary caption;

    private List<String> topLikers;
    private Long nextMaxId;
    private Integer maxNumVisiblePreviewComments;
    private Boolean hasLiked;
    private Boolean commentLikesEnabled;
    private Boolean hasMoreComments;
    private Boolean canViewerReshare;
    private Boolean captionIsEdited;
    private Boolean photoOfYou;
    private Boolean commentsDisabled;
    private Boolean canViewerSave;
    private Boolean hasViewerSaved;

    private String code;
    private String clientCacheKey;
    private String organicTrackingToken;
}
