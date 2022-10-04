package com.aionpowerbook.springdiscordchatlogger.listener;

import com.aionpowerbook.springdiscordchatlogger.component.LavaPlayerAudioProvider;
import com.aionpowerbook.springdiscordchatlogger.service.TrackScheduler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.VoiceState;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.Message;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@RequiredArgsConstructor
public abstract class MessageListener {

    private final LavaPlayerAudioProvider provider;
    private final AudioPlayerManager manager;
    private final TrackScheduler trackScheduler;
    public Mono<Void> processCommand(MessageCreateEvent event) {

        var content = event.getMessage().getContent();

        if (event.getMessage().getContent().equalsIgnoreCase("!ping")) {
            return event.getMessage().getChannel()
                    .flatMap(channel -> channel.createMessage("pong!"))
                    .then();
        }

        if (event.getMessage().getContent().equalsIgnoreCase("!join")) {
            return Mono.just(event.getMessage())
                    .flatMap(Message::getAuthorAsMember)
                    .flatMap(m -> m.getVoiceState()
                            .flatMap(VoiceState::getChannel))
                    .flatMap(c -> c.join(spec -> spec.setProvider(provider)))
                    .then();
        }

        if (content.startsWith("!play")) {
            return Mono.justOrEmpty(content)
                    .map(c -> Arrays.asList(c.split(" ")))
                    .doOnNext(command -> manager.loadItem(command.get(1), trackScheduler)).then();
        }

        return Mono.empty()
                .then();
    }

    public Mono<Void> processCommand(Message eventMessage) {

        if (eventMessage.getContent().equalsIgnoreCase("!ping")) {
            return eventMessage.getChannel()
                    .flatMap(channel -> channel.createMessage("pong!"))
                    .then();
        }

        return Mono.empty()
                .then();
    }
}
