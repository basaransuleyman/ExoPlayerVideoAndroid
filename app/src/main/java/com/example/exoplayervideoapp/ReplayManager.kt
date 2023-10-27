package com.example.exoplayervideoapp

import com.google.android.exoplayer2.ExoPlayer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class ReplayManager {
    private var job: Job? = null
    fun startReplay(player: ExoPlayer) {
        job = CoroutineScope(Dispatchers.Main).launch {
            while (isActive) {
                delay(15000)
                player.seekTo(0)
                player.play()
            }
        }
    }

    fun cancelReplay() {
        job?.cancel()
    }
}