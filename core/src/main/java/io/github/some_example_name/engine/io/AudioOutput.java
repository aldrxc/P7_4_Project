package io.github.some_example_name.engine.io;
// package main.java.io.github.some_example_name.engine.io;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Disposable;
import java.util.HashMap;
import java.util.Map;

public class AudioOutput implements Disposable {

    // Cache sounds to prevent disk lag
    private Map<String, Sound> soundEffects;
    private Music backgroundMusic;
    private float volume = 1.0f;

    public AudioOutput() {
        soundEffects = new HashMap<>();
    }

    public void playSound(String fileName) {
        if (!soundEffects.containsKey(fileName)) {
            if (Gdx.files.internal(fileName).exists()) {
                Sound s = Gdx.audio.newSound(Gdx.files.internal(fileName));
                soundEffects.put(fileName, s);
            } else {
                System.err.println("Audio Missing: " + fileName);
                return;
            }
        }
        soundEffects.get(fileName).play(volume);
    }

    public void playMusic(String fileName) {
        stopMusic();

        if (Gdx.files.internal(fileName).exists()) {
            backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal(fileName));
            backgroundMusic.setVolume(volume);
            backgroundMusic.setLooping(true);
            backgroundMusic.play();
        }
    }

    public void stopMusic() {
        if (backgroundMusic != null) {
            backgroundMusic.stop();
            backgroundMusic.dispose();
            backgroundMusic = null;
        }
    }

    public void setVolume(float v) {
        this.volume = v;
        if (backgroundMusic != null) {
            backgroundMusic.setVolume(volume);
        }
    }

    @Override
    public void dispose() {
        for (Sound s : soundEffects.values()) {
            s.dispose();
        }
        soundEffects.clear();
        stopMusic();
    }
}