package com.joshkupka.development;


import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;

import java.util.List;

public class Twitch {
    Database database = new Database();

    public TwitchClient twitchInit() {
        OAuth2Credential oAuth2Credential = new OAuth2Credential("twitch", database.getData("apiKey").toString());
        TwitchClient twitchClient = TwitchClientBuilder.builder()
                .withEnableHelix(true)
                .withClientId("v2g4ds9qldblz50i4qic8zzxfsja1n")
                .withEnablePubSub(true)
                .withEnableKraken(true)
                .withEnableChat(true)
                .withChatAccount(oAuth2Credential)
                .withClientSecret(database.getData("apiKey").toString())
                .build();
        return twitchClient;
    }
    /*
    public void getTwitchChat() {
        TwitchClient twitchClient = twitchInit();
        twitchClient.getChat().joinChannel("joshkupka");
        twitchClient.getEventManager().onEvent(ChannelMessageEvent.class).subscribe(event -> {
            System.out.println("[" + event.getChannel().getName() + "] " + event.getUser().getName() + ": " + event.getMessage());
        });
    }*/

    public List getUser(String apiKey) {
        List userData = twitchInit().getHelix().getUsers(apiKey, null, null).execute().getUsers();
        return userData;
    }
}