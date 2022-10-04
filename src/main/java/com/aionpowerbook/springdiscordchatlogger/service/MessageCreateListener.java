package com.aionpowerbook.springdiscordchatlogger.service;

import com.aionpowerbook.springdiscordchatlogger.component.LavaPlayerAudioProvider;
import com.aionpowerbook.springdiscordchatlogger.listener.EventListener;
import com.aionpowerbook.springdiscordchatlogger.listener.MessageListener;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import discord4j.core.event.domain.message.MessageCreateEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class MessageCreateListener extends MessageListener implements EventListener<MessageCreateEvent> {

    public MessageCreateListener(LavaPlayerAudioProvider provider, AudioPlayerManager manager, TrackScheduler trackScheduler) {
        super(provider, manager, trackScheduler);
    }

    @Override
    public Class<MessageCreateEvent> getEventType() {
        return MessageCreateEvent.class;
    }

    @Override
    public Mono<Void> execute(MessageCreateEvent event) {
        return processCommand(event);
    }
}
