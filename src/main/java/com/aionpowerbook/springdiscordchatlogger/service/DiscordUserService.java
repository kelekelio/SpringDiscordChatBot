package com.aionpowerbook.springdiscordchatlogger.service;

import com.aionpowerbook.springdiscordchatlogger.entity.DiscordUser;
import com.aionpowerbook.springdiscordchatlogger.repository.DiscordUserRepository;
import discord4j.discordjson.json.UserData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DiscordUserService {

    private final DiscordUserRepository discordUserRepository;

    @Transactional
    public DiscordUser getUser(UserData userData) {
        return discordUserRepository.findById(userData.id().asLong())
                .orElse(createUser(userData));
    }

    private DiscordUser createUser(UserData userData) {
        DiscordUser newUser = DiscordUser.builder()
                .id(userData.id().asLong())
                .username(userData.username())
                .build();
        return discordUserRepository.save(newUser);
    }
}
