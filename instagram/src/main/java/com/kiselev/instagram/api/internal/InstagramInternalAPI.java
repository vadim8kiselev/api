package com.kiselev.instagram.api.internal;

import com.google.common.collect.Lists;
import com.kiselev.instagram.api.client.InstagramClient;
import lombok.RequiredArgsConstructor;
import org.brunocvcunha.instagram4j.requests.*;
import org.brunocvcunha.instagram4j.requests.payload.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class InstagramInternalAPI {

    private final InstagramClient client;

    public void authenticate() {
        client.authenticate();
    }

    public boolean isThatMe(String someone) {
        String me = client.whoAmI();
        return me.equals(someone);
    }

    public InstagramUser findProfileInternal(String username) {
        InstagramSearchUsernameResult userResult =
                client.request(new InstagramSearchUsernameRequest(username));

        if (userResult != null) {
            InstagramUser user = userResult.getUser();
            if (user != null) {
                return user;
            } else {
                InstagramUser deletedUser = new InstagramUser();
                deletedUser.setPk(0L);
                deletedUser.setUsername(username);
                return deletedUser;
            }
        }
        return null;
    }

    public List<InstagramUserSummary> findFollowersInternal(Long profilePk) {
        List<InstagramUserSummary> followers = Lists.newArrayList();

        String next = null;
        do {
            InstagramGetUserFollowersResult followersResult =
                    client.request(new InstagramGetUserFollowersRequest(
                            profilePk,
                            next
                    ));

            if (followersResult != null) {
                followers.addAll(
                        followersResult.getUsers()
                );
            }

            next = followersResult != null
                    ? followersResult.getNext_max_id()
                    : null;
        } while (next != null);

        return followers;
    }

    public List<InstagramUserSummary> findFollowingInternal(Long profilePk) {
        List<InstagramUserSummary> following = Lists.newArrayList();

        String next = null;
        do {
            InstagramGetUserFollowersResult profileFollowing =
                    client.request(new InstagramGetUserFollowingRequest(
                            profilePk,
                            next
                    ));

            if (profileFollowing != null) {
                following.addAll(
                        profileFollowing.getUsers()
                );
            }

            next = profileFollowing != null
                    ? profileFollowing.getNext_max_id()
                    : null;
        } while (next != null);

        return following;
    }

    public List<InstagramFeedItem> findPostsInternal(Long profilePk) {
        List<InstagramFeedItem> posts = Lists.newArrayList();

        String next = null;
        do {
            InstagramFeedResult feedPosts =
                    client.request(new InstagramUserFeedRequest2(
                            profilePk,
                            next
                    ));

            if (feedPosts != null) {
                posts.addAll(
                        feedPosts.getItems()
                );
            }

            next = feedPosts != null
                    ? feedPosts.getNext_max_id()
                    : null;
        } while (next != null);

        return posts;
    }

    public List<InstagramUserSummary> findLikersInternal(Long mediaId) {
        InstagramGetMediaLikersResult likersResult = client.request(
                new InstagramGetMediaLikersRequest(
                        mediaId
                )
        );
        if (likersResult != null) {
            return likersResult.getUsers();
        } else {
            return Collections.emptyList();
        }
    }

    public List<InstagramComment> findCommentariesInternal(String mediaId) {
        List<InstagramComment> comments = Lists.newArrayList();

        String next = null;
        do {
            InstagramGetMediaCommentsResult commentsResult = client.request(
                    new InstagramGetMediaCommentsRequest(
                            mediaId,
                            next
                    )
            );

            if (commentsResult != null) {
                comments.addAll(
                        commentsResult.getComments()
                );
            }

            next = commentsResult != null
                    ? commentsResult.getNext_max_id()
                    : null;
        } while (next != null);

        return comments;
    }

    public List<InstagramStoryItem> findStoriesInternal(Long profilePk) {
        InstagramUserReelMediaFeedResult feedResult = client.request(
                new InstagramGetUserReelMediaFeedRequest(
                        profilePk
                )
        );
        if (feedResult != null) {
            return feedResult.getItems();
        } else {
            return Collections.emptyList();
        }
    }

    public List<InstagramUser> findStoryViewersInternal(String storyPk) {
        List<InstagramUser> viewers = Lists.newArrayList();

        String next = null;
        do {
            InstagramGetStoryViewersResult viewersResult = client.request(
                    new InstagramGetStoryViewersRequest2(
                            storyPk,
                            next
                    )
            );

            if (viewersResult != null) {
                viewers.addAll(
                        Optional.of(viewersResult)
                                .map(InstagramGetStoryViewersResult::getUsers)
                                .orElseGet(Lists::newArrayList)
                );
            }

            next = viewersResult != null
                    ? viewersResult.getNext_max_id()
                    : null;
        } while (next != null);

        return viewers;
    }
}
