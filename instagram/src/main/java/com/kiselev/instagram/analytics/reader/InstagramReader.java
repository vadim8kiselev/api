package com.kiselev.instagram.analytics.reader;

import com.google.common.collect.Lists;
import com.kiselev.instagram.api.InstagramAPI;
import com.kiselev.instagram.model.*;
import com.kiselev.instagram.model.mapper.InstagramMapper;
import com.kiselev.instagram.utils.InstagramUtils;
import lombok.RequiredArgsConstructor;
import org.brunocvcunha.instagram4j.requests.payload.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class InstagramReader {

    private final InstagramAPI api;

    private final InstagramMapper mapper;

    public InstagramProfile read(String username) {
        InstagramUser instagramUser = readInstagramUser(username);

        InstagramProfile profile = readProfile(instagramUser);

        profile.setFollowers(
                readFollowers(instagramUser)
        );
        profile.setFollowing(
                readFollowing(instagramUser)
        );
//        profile.setPosts(
//                readPosts(instagramUser)
//        );

        return profile;
    }

    private InstagramUser readInstagramUser(String username) {
        return api.findProfile(username);
    }

    private List<InstagramProfile> readProfiles(List<InstagramUser> instagramUsers) {
        return instagramUsers.stream()
                .map(this::readProfile)
                .sorted(Comparator.comparing(InstagramProfile::getPk))
                .collect(Collectors.toList());
    }

    private InstagramProfile readProfile(InstagramUser instagramUser) {
        InstagramProfile profile = mapper.profile(
                instagramUser
        );

        /*List<InstagramPhoto> photos = readPhotos(
                instagramUser
        );
        profile.setPhotos(
                photos
        );
        profile.setMainPhoto(
                InstagramUtils.selectMainPhoto(photos)
        );*/

        profile.setStories(
                readStories(instagramUser)
        );

        return profile;
    }

    private List<InstagramProfile> readFollowers(InstagramUser instagramUser) {
        return readProfiles(
                api.findFollowers(instagramUser.getPk())
        );
    }

    private List<InstagramProfile> readFollowing(InstagramUser instagramUser) {
        return readProfiles(
                api.findFollowing(instagramUser.getPk())
        );
    }

    private List<InstagramPhoto> readPhotos(InstagramUser instagramUser) {
        List<InstagramProfilePic> instagramProfilePics = Lists.newArrayList();

        List<InstagramProfilePic> hd_profile_pic_versions = instagramUser.getHd_profile_pic_versions();
        if (hd_profile_pic_versions != null) {
            instagramProfilePics.addAll(hd_profile_pic_versions);
        }

        InstagramProfilePic hd_profile_pic_url_info = instagramUser.getHd_profile_pic_url_info();
        if (hd_profile_pic_url_info != null) {
            instagramProfilePics.add(hd_profile_pic_url_info);
        }

        return instagramProfilePics.stream()
                .map(mapper::photo)
                .sorted(Comparator.comparing(InstagramPhoto::getUrl))
                .collect(Collectors.toList());
    }

    private List<InstagramPost> readPosts(InstagramUser instagramUser) {
        List<InstagramFeedItem> posts = api.findPosts(instagramUser.getPk());

        return posts.stream()
                .map(this::readPost)
                .sorted(Comparator.comparing(InstagramPost::getPk))
                .collect(Collectors.toList());
    }

    private InstagramPost readPost(InstagramFeedItem instagramFeedItem) {
        InstagramPost post = mapper.post(
                instagramFeedItem
        );

        /*List<InstagramPhoto> photos = readPhotos(instagramFeedItem);

        post.setPhotos(
                photos
        );

        post.setMainPhoto(
                InstagramUtils.selectMainPhoto(
                        photos
                )
        );*/

        post.setLikers(readLikers(instagramFeedItem));

        post.setCommentaries(readCommentaries(instagramFeedItem));

        post.setCaption(mapper.commentary(instagramFeedItem.getCaption()));

        post.setUserTags(readUserTags(instagramFeedItem));

        post.setCarouselMedia(readCarouselMedia(instagramFeedItem));

        return post;
    }

    private List<InstagramPhoto> readPhotos(InstagramFeedItem instagramFeedItem) {
        List<ImageMeta> imageMetas = Lists.newArrayList();

        Optional.ofNullable(instagramFeedItem)
                .map(InstagramFeedItem::getVideo_versions)
                .ifPresent(
                        imageMetas::addAll
                );

        Optional.ofNullable(instagramFeedItem)
                .map(InstagramFeedItem::getImage_versions2)
                .map(ImageVersions::getCandidates)
                .ifPresent(
                        imageMetas::addAll
                );

        return imageMetas.stream()
                .map(mapper::photo)
                .sorted(Comparator.comparing(InstagramPhoto::getUrl))
                .collect(Collectors.toList());
    }

    private List<InstagramProfile> readLikers(InstagramFeedItem instagramFeedItem) {
        List<InstagramUserSummary> rawLikers = api.findLikers(instagramFeedItem.getPk());

        List<InstagramUser> likers = rawLikers.stream()
                .map(InstagramUserSummary::getUsername)
                .map(this::readInstagramUser)
                .collect(Collectors.toList());

        return readProfiles(
                likers
        );
    }

    private List<InstagramCommentary> readCommentaries(InstagramFeedItem instagramFeedItem) {
        List<InstagramComment> comments = api.findCommentaries(instagramFeedItem.getId());

        return comments.stream()
                .map(mapper::commentary)
                .collect(Collectors.toList());
    }

    private List<InstagramProfile> readUserTags(InstagramFeedItem instagramFeedItem) {
        Stream<InstagramFeedUserTag> userTagStream = Optional.ofNullable(instagramFeedItem)
                .map(InstagramFeedItem::getUsertags)
                .map(InstagramUserTagsContainer::getIn)
                .map(InstagramUtils::safeStream)
                .orElseGet(Stream::empty);

        List<InstagramUser> userTags = userTagStream
                .map(InstagramFeedUserTag::getUser)
                .map(InstagramUserSummary::getUsername)
                .map(this::readInstagramUser)
                .collect(Collectors.toList());

        return readProfiles(
                userTags
        );
    }

    private List<InstagramPost> readCarouselMedia(InstagramFeedItem instagramFeedItem) {
        Stream<InstagramCarouselMediaItem> carouselMediaStream =
                Optional.ofNullable(instagramFeedItem)
                        .map(InstagramFeedItem::getCarousel_media)
                        .map(InstagramUtils::safeStream)
                        .orElseGet(Stream::empty);

        return carouselMediaStream
                .map(this::readPost)
                .collect(Collectors.toList());
    }

    private List<InstagramStory> readStories(InstagramUser instagramUser) {
        List<InstagramStoryItem> stories = api.findStories(instagramUser.getPk());

        return stories.stream()
                .map(this::readStory)
                .sorted(Comparator.comparing(InstagramStory::getPk))
                .collect(Collectors.toList());
    }

    private InstagramStory readStory(InstagramStoryItem instagramStoryItem) {
        InstagramStory story = mapper.story(instagramStoryItem);

        List<InstagramPhoto> photos = readPhotos(instagramStoryItem);

        story.setPhotos(
                photos
        );

        story.setMainPhoto(
                InstagramUtils.selectMainPhoto(
                        photos
                )
        );

        story.setViewers(
                readStoryViewers(instagramStoryItem)
        );

        return story;
    }

    private List<InstagramPhoto> readPhotos(InstagramStoryItem instagramStoryItem) {
        List<ImageMeta> imageMetas = Lists.newArrayList();

        Optional.ofNullable(instagramStoryItem)
                .map(InstagramStoryItem::getVideo_versions)
                .ifPresent(
                        imageMetas::addAll
                );

        Optional.ofNullable(instagramStoryItem)
                .map(InstagramStoryItem::getImage_versions2)
                .map(ImageVersions::getCandidates)
                .ifPresent(
                        imageMetas::addAll
                );

        return imageMetas.stream()
                .map(mapper::photo)
                .sorted(Comparator.comparing(InstagramPhoto::getUrl))
                .collect(Collectors.toList());
    }

    private List<InstagramProfile> readStoryViewers(InstagramStoryItem instagramStoryItem) {
        if (api.isThatMe(instagramStoryItem.getUser().getUsername())) {
            List<InstagramUser> rawStoryViewers = api.findStoryViewers(
                    instagramStoryItem.getId()
            );

            List<String> storyViewers = rawStoryViewers.stream()
                    .map(InstagramUser::getUsername)
                    .collect(Collectors.toList());

            return storyViewers.stream()
                    .map(api::findProfile)
                    .map(this::readProfile)
                    .sorted(Comparator.comparing(InstagramProfile::getPk))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
