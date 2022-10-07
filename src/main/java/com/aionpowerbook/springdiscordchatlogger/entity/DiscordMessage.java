package com.aionpowerbook.springdiscordchatlogger.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.Instant;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiscordMessage {

    @Id
    private Long idValue;

    private Long channelIdValue;

    private Long guildIdValue;

    private boolean guildIdAbsent;

    private String content;

    private Instant timestamp;

    @Column(name = "user_id", insertable = false, updatable = false)
    private Long userId;

    @ManyToOne
    private DiscordUser user;

}
