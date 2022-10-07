package com.aionpowerbook.springdiscordchatlogger.service;

import com.aionpowerbook.springdiscordchatlogger.entity.DiscordMessage;
import com.aionpowerbook.springdiscordchatlogger.repository.DiscordMessageRepository;
import discord4j.core.object.entity.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DiscordMessageService {

    private final DiscordMessageRepository discordMessageRepository;
    private final DiscordUserService userService;

    @Transactional
    public void saveMessage(Message message) {

        DiscordMessage discordMessage = DiscordMessage.builder()
                .idValue(message.getId().asLong())
                .channelIdValue(message.getChannelId().asLong())
                .guildIdValue(message.getChannelId().asLong())
                .content(message.getContent())
                .timestamp(message.getTimestamp())
                .user(userService.getUser(message.getUserData()))
                .build();

        log.info("Message {} logged.", discordMessage.getContent());
        discordMessageRepository.save(discordMessage);
    }
}
