package com.swamp.tank.sound;

import javafx.scene.media.AudioClip;

public class SoundEffect {
    
    public static void play(String path) {
        AudioClip audioClip = new AudioClip(SoundEffect.class.getResource(path).toString());
        audioClip.play();
    }

}
