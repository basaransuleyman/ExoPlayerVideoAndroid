package com.example.exoplayervideoapp

import android.content.Context
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem

class ExoPlayerManager(private val context: Context) {
    fun createPlayer(): ExoPlayer {
        return ExoPlayer.Builder(context).build()
    }

    fun prepareAndPlay(player: ExoPlayer, url: String) {
        val mediaItem = MediaItem.fromUri(url)
        player.setMediaItem(mediaItem)
        player.prepare()
    }
}