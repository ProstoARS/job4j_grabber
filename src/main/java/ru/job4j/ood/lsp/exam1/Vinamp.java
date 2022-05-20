package ru.job4j.ood.lsp.exam1;

public class Vinamp extends MediaPlayer {

    public void playVideo() {
        throw new IllegalStateException("Плеер не проигрывает видео");
    }
}
