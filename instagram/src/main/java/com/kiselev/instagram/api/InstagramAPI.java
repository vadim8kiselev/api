package com.kiselev.instagram.api;

import com.kiselev.instagram.api.cache.InstagramCache;
import com.kiselev.instagram.api.internal.InstagramInternalAPI;
import org.brunocvcunha.instagram4j.requests.payload.*;

import java.util.List;
import java.util.stream.Collectors;

public class InstagramAPI {

    private final InstagramInternalAPI internalAPI;

    private final InstagramCache<String, InstagramUser> users;

    private final InstagramCache<Long, List<InstagramUser>> followers;

    private final InstagramCache<Long, List<InstagramUser>> following;

    private final InstagramCache<Long, List<InstagramFeedItem>> posts;

    private final InstagramCache<Long, List<InstagramStoryItem>> stories;

    private final InstagramCache<String, List<InstagramUser>> storyViewers;

    private final InstagramCache<Long, List<InstagramUserSummary>> likers;

    private final InstagramCache<String, List<InstagramComment>> commentaries;

    public InstagramAPI(InstagramInternalAPI internalAPI) {
        this.internalAPI = internalAPI;

        this.users = new InstagramCache<>();
        this.followers = new InstagramCache<>();
        this.following = new InstagramCache<>();
        this.posts = new InstagramCache<>();
        this.stories = new InstagramCache<>();
        this.storyViewers = new InstagramCache<>();
        this.likers = new InstagramCache<>();
        this.commentaries = new InstagramCache<>();
    }

    public void authenticate() {
        internalAPI.authenticate();
    }

    public boolean isThatMe(String someone) {
        return internalAPI.isThatMe(someone);
    }

    public InstagramUser findProfile(String username) {
        return users.cache(
                username,
                () -> internalAPI.findProfileInternal(username)
        );
    }

    public List<InstagramUser> findFollowers(Long profilePk) {
        return followers.cache(
                profilePk,
                () -> internalAPI.findFollowersInternal(profilePk).stream()
                        .map(InstagramUserSummary::getUsername)
                        .map(this::findProfile)
                        .collect(Collectors.toList())
        );
    }

    public List<InstagramUser> findFollowing(Long profilePk) {
        return following.cache(
                profilePk,
                () -> internalAPI.findFollowingInternal(profilePk).stream()
                        .map(InstagramUserSummary::getUsername)
                        .map(this::findProfile)
                        .collect(Collectors.toList())
        );
    }

    public List<InstagramFeedItem> findPosts(Long profilePk) {
        return posts.cache(
                profilePk,
                () -> internalAPI.findPostsInternal(profilePk)
        );
    }

    public List<InstagramUserSummary> findLikers(Long mediaId) {
        return likers.cache(
                mediaId,
                () -> internalAPI.findLikersInternal(mediaId)
        );
    }

    public List<InstagramComment> findCommentaries(String mediaId) {
        return commentaries.cache(
                mediaId,
                () -> internalAPI.findCommentariesInternal(mediaId)
        );
    }

    // Also check for InstagramGetReelsTrayFeedRequest
    public List<InstagramStoryItem> findStories(Long profilePk) {
        return stories.cache(
                profilePk,
                () -> internalAPI.findStoriesInternal(profilePk)
        );
    }

    public List<InstagramUser> findStoryViewers(String storyPk) {
        return storyViewers.cache(
                storyPk,
                // Re-read to get full information
                () -> internalAPI.findStoryViewersInternal(storyPk).stream()
                        .map(InstagramUser::getUsername)
                        .map(this::findProfile)
                        .collect(Collectors.toList())
        );
    }
}
