package com.example.project;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class MusicService extends Service {
    private MediaPlayer bgmusic;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        bgmusic = MediaPlayer.create(this, R.raw.Soundtrack);
        bgmusic.setLooping(true);
        bgmusic.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        bgmusic.stop();
        super.onDestroy();
    }
}