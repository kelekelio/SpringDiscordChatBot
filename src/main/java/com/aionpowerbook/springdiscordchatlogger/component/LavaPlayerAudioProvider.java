package com.aionpowerbook.springdiscordchatlogger.component;


import com.sedmelluq.discord.lavaplayer.format.StandardAudioDataFormats;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.playback.MutableAudioFrame;
import discord4j.voice.AudioProvider;
import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;

@Component
public final class LavaPlayerAudioProvider extends AudioProvider {

    private final AudioPlayer audioPlayer;
    private final MutableAudioFrame frame = new MutableAudioFrame();

    public LavaPlayerAudioProvider(AudioPlayer audioPlayer) {
        super(ByteBuffer.allocate(StandardAudioDataFormats.DISCORD_OPUS.maximumChunkSize()));
        this.frame.setBuffer(getBuffer());
        this.audioPlayer = audioPlayer;
    }

    @Override
    public boolean provide() {
        final boolean didProvide = audioPlayer.provide(frame);
        if (didProvide) {
            getBuffer().flip();
        }
        return didProvide;
    }
}
