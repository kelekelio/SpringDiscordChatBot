package com.aionpowerbook.springdiscordchatlogger.repository;

import com.aionpowerbook.springdiscordchatlogger.entity.DiscordUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscordUserRepository extends JpaRepository<DiscordUser, Long> {
}
