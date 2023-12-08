package com.muvi.androidtvexp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.ui.PlayerView;

import android.os.Bundle;

public class PlayerActivity extends AppCompatActivity {

    private PlayerView playerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        playerView = findViewById(R.id.playerView);

        ExoPlayer player = new ExoPlayer.Builder(this).build();
        player.addMediaItem(MediaItem.fromUri("https://cdn.theoplayer.com/video/big_buck_bunny/big_buck_bunny.m3u8"));
        player.prepare();
        player.play();

        playerView.setPlayer(player);
    }
}