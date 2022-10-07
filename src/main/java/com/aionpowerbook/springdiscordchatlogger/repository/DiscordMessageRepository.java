package com.aionpowerbook.springdiscordchatlogger.repository;

import com.aionpowerbook.springdiscordchatlogger.entity.DiscordMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscordMessageRepository extends JpaRepository<DiscordMessage, Long> {
}
