package com.jcog.utils;

import com.github.twitch4j.helix.TwitchHelix;
import com.github.twitch4j.helix.TwitchHelixBuilder;
import com.github.twitch4j.helix.domain.*;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class TwitchApi {
    private final TwitchHelix helixClient;
    private final String authToken;

    public TwitchApi(String authToken, String clientId) {
        this.authToken = authToken;
        helixClient = TwitchHelixBuilder.builder()
                .withClientId(clientId)
                .build();
    }

    @Nullable
    public Clip getClipById(String id) throws HystrixRuntimeException {
        ClipList clipList = helixClient.getClips(
                authToken,
                null,
                null,
                id,
                null,
                null,
                1,
                null,
                null
        ).execute();
        if (clipList.getData().isEmpty()) {
            return null;
        }
        return clipList.getData().get(0);
    }

    @Nullable
    public Follow getFollow(String fromId, String toId) throws HystrixRuntimeException {
        FollowList followList = helixClient.getFollowers(
                authToken,
                fromId,
                toId,
                null,
                1
        ).execute();
        if (followList.getFollows().isEmpty()) {
            return null;
        }
        return followList.getFollows().get(0);
    }

    public int getFollowerCount(String userId) throws HystrixRuntimeException {
        FollowList followList = helixClient.getFollowers(
                authToken,
                null,
                userId,
                null,
                null
        ).execute();
        return followList.getTotal();
    }

    public List<Follow> getFollowers(String userId) throws HystrixRuntimeException {
        String cursor = null;
        List<Follow> followsOutput = new ArrayList<>();

        do {
            FollowList followList = helixClient.getFollowers(
                    authToken,
                    null,
                    userId,
                    cursor,
                    100
            ).execute();
            cursor = followList.getPagination().getCursor();
            followsOutput.addAll(followList.getFollows());
        } while (cursor != null);
        return followsOutput;
    }

    public List<Follow> getFollowList(String userId) throws HystrixRuntimeException {
        String cursor = null;
        List<Follow> followsOutput = new ArrayList<>();

        do {
            FollowList followList = helixClient.getFollowers(
                    authToken,
                    userId,
                    null,
                    cursor,
                    100
            ).execute();
            cursor = followList.getPagination().getCursor();
            followsOutput.addAll(followList.getFollows());
        } while (cursor != null);
        return followsOutput;
    }

    @Nullable
    public Game getGameById(String gameId) throws HystrixRuntimeException {
        GameList gameList = helixClient.getGames(
                authToken,
                Collections.singletonList(gameId),
                null
        ).execute();
        if (gameList.getGames().isEmpty()) {
            return null;
        }
        return gameList.getGames().get(0);
    }

    public List<Moderator> getMods(String userId) throws HystrixRuntimeException {
        String cursor = null;
        List<Moderator> modsOutput = new ArrayList<>();

        do {
            ModeratorList moderatorList = helixClient.getModerators(
                    authToken,
                    userId,
                    null,
                    cursor
            ).execute();
            cursor = moderatorList.getPagination().getCursor();
            modsOutput.addAll(moderatorList.getModerators());
        } while (cursor != null);
        return modsOutput;
    }

    @Nullable
    public Stream getStream(String streamer) throws HystrixRuntimeException {
        StreamList streamList = helixClient.getStreams(
                authToken,
                "",
                "",
                1,
                null,
                null,
                null,
                Collections.singletonList(streamer)).execute();
        if (streamList.getStreams().isEmpty()) {
            return null;
        }
        return streamList.getStreams().get(0);
    }

    public List<Subscription> getSubList(String userId) throws HystrixRuntimeException {
        String cursor = null;
        List<Subscription> subscriptionsOutput = new ArrayList<>();

        do {
            SubscriptionList subscriptionList = helixClient.getSubscriptions(
                    authToken,
                    userId,
                    cursor,
                    null,
                    100
            ).execute();
            cursor = subscriptionList.getPagination().getCursor();
            subscriptionsOutput.addAll(subscriptionList.getSubscriptions());
        } while (cursor != null);
        return subscriptionsOutput;
    }

    @Nullable
    public User getUserById(String userId) throws HystrixRuntimeException {
        UserList userList = helixClient.getUsers(
                authToken,
                Collections.singletonList(userId),
                null
        ).execute();
        if (userList.getUsers().isEmpty()) {
            return null;
        }
        return userList.getUsers().get(0);
    }

    @Nullable
    public User getUserByUsername(String username) throws HystrixRuntimeException {
        UserList userList = helixClient.getUsers(
                authToken,
                null,
                Collections.singletonList(username)
        ).execute();
        if (userList.getUsers().isEmpty()) {
            return null;
        }
        return userList.getUsers().get(0);
    }

    public List<User> getUserListByIds(Collection<String> userIdList) throws HystrixRuntimeException {
        Iterator<String> iterator = userIdList.iterator();
        List<String> usersHundred = new ArrayList<>();
        List<User> output = new ArrayList<>();
        while (iterator.hasNext()) {
            while (usersHundred.size() < 100 && iterator.hasNext()) {
                usersHundred.add(iterator.next());
            }
            UserList resultList = helixClient.getUsers(
                    authToken,
                    usersHundred,
                    null
            ).execute();
            output.addAll(resultList.getUsers());
            usersHundred.clear();
        }
        return output;
    }

    public List<User> getUserListByUsernames(Collection<String> usernameList) throws HystrixRuntimeException {
        Iterator<String> iterator = usernameList.iterator();
        List<String> usersHundred = new ArrayList<>();
        List<User> output = new ArrayList<>();
        while (iterator.hasNext()) {
            while (usersHundred.size() < 100 && iterator.hasNext()) {
                usersHundred.add(iterator.next());
            }
            UserList resultList = helixClient.getUsers(
                    authToken,
                    null,
                    usersHundred
            ).execute();
            output.addAll(resultList.getUsers());
            usersHundred.clear();
        }
        return output;
    }

    @Nullable
    public Video getVideoById(String videoId) throws HystrixRuntimeException {
        VideoList videoList = helixClient.getVideos(
                authToken,
                videoId,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                1
        ).execute();
        if (videoList.getVideos().isEmpty()) {
            return null;
        }
        return videoList.getVideos().get(0);
    }
}
