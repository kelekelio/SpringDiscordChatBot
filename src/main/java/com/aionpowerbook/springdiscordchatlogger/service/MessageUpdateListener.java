package com.aionpowerbook.springdiscordchatlogger.service;

import com.aionpowerbook.springdiscordchatlogger.component.LavaPlayerAudioProvider;
import com.aionpowerbook.springdiscordchatlogger.listener.EventListener;
import com.aionpowerbook.springdiscordchatlogger.listener.MessageListener;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import discord4j.core.event.domain.message.MessageUpdateEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class MessageUpdateListener extends MessageListener implements EventListener<MessageUpdateEvent> {

    public MessageUpdateListener(LavaPlayerAudioProvider provider, AudioPlayerManager manager, TrackScheduler trackScheduler) {
        super(provider, manager, trackScheduler);
    }

    @Override
    public Class<MessageUpdateEvent> getEventType() {
        return MessageUpdateEvent.class;
    }

    @Override
    public Mono<Void> execute(MessageUpdateEvent event) {
        return Mono.just(event)
                .filter(MessageUpdateEvent::isContentChanged)
                .flatMap(MessageUpdateEvent::getMessage)
                .flatMap(super::processCommand);
    }
}
