package com.jcog.utils;

import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.twitch4j.pubsub.TwitchPubSub;
import com.github.twitch4j.pubsub.TwitchPubSubBuilder;
import com.github.twitch4j.pubsub.events.ChannelBitsEvent;
import com.github.twitch4j.pubsub.events.ChannelSubGiftEvent;
import com.github.twitch4j.pubsub.events.RewardRedeemedEvent;

public abstract class TwitchPubSubClient {

    public TwitchPubSubClient(
            String streamerId,
            String authToken,
            boolean listenForBits,
            boolean listenForChannelPoints,
            boolean listenForSubGifts
    ) {
        TwitchPubSub client = TwitchPubSubBuilder.builder().build();
        OAuth2Credential oAuth2Credential = new OAuth2Credential("twitch", authToken);

        if (listenForBits) {
            client.listenForCheerEvents(oAuth2Credential, streamerId);
            client.getEventManager().onEvent(ChannelBitsEvent.class, this::onBitsEvent);
        }

        if (listenForChannelPoints) {
            client.listenForChannelPointsRedemptionEvents(oAuth2Credential, streamerId);
            client.getEventManager().onEvent(RewardRedeemedEvent.class, this::onChannelPointsEvent);
        }

        if (listenForSubGifts) {
            client.listenForChannelSubGiftsEvents(oAuth2Credential, streamerId);
            client.getEventManager().onEvent(ChannelSubGiftEvent.class, this::onSubGiftsEvent);
        }
    }



    public abstract void onBitsEvent(ChannelBitsEvent event);

    public abstract void onChannelPointsEvent(RewardRedeemedEvent event);

    public abstract void onSubGiftsEvent(ChannelSubGiftEvent event);
}
