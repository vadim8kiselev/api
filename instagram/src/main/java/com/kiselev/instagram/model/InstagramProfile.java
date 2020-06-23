package com.kiselev.instagram.model;

import com.kiselev.instagram.model.annotation.BusinessValue;
import com.kiselev.instagram.model.annotation.type.BusinessType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class InstagramProfile {

    @BusinessValue
    private LocalDateTime timestamp;

    /* Min */
    @BusinessValue(BusinessType.CONSTANT)
    private Long pk;
    @BusinessValue(BusinessType.CONSTANT)
    private String username;
    @BusinessValue(BusinessType.CONSTANT)
    private String fullName;

    @BusinessValue(BusinessType.NUMBER)
    private Integer mediaCount;
    @BusinessValue(BusinessType.NUMBER)
    private Integer followerCount;
    @BusinessValue(BusinessType.NUMBER)
    private Integer followingCount;
    @BusinessValue(BusinessType.NUMBER)
    private Integer geoMediaCount;
    @BusinessValue(BusinessType.NUMBER)
    private Integer userTagsCount;

    @BusinessValue(BusinessType.TEXT)
    private String biography;
    @BusinessValue(BusinessType.TEXT)
    private String category;
    @BusinessValue
    private Float latitude;
    @BusinessValue
    private Float longitude;

    @BusinessValue(BusinessType.TEXT)
    private String externalUrl;

    @BusinessValue(BusinessType.OBJECT)
    private List<InstagramProfile> followers;
    @BusinessValue(BusinessType.OBJECT)
    private List<InstagramProfile> following;

    @BusinessValue(BusinessType.OBJECT)
    private List<InstagramPost> posts;

    @BusinessValue(BusinessType.OBJECT)
    private List<InstagramStory> stories;

    @BusinessValue(BusinessType.OBJECT)
    private InstagramPhoto mainPhoto;

    /* Full */
    private Boolean isPrivate;
    private Boolean isVerified;
    private Boolean isBusiness;

    private String publicEmail;
    private String publicPhoneNumber;
    private String publicPhoneCountryCode;

    private String addressStreet;
    private String cityName;
    private String zip;

    private String directMessaging;
    private String businessContactMethod;

    private String profilePicId;
    private String profilePicUrl;
    private List<InstagramPhoto> photos;

    private Boolean hasChaining;
    private Boolean isFavorite;

    private Boolean hasBiographyTranslation;
    private Boolean hasAnonymousProfilePicture;

    private String externalLynxUrl;
}
