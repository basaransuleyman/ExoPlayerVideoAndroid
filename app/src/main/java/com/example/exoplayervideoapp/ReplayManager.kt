package com.example.exoplayervideoapp

import android.os.Handler
import com.google.android.exoplayer2.ExoPlayer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class ReplayManager {
    /*
    If you have thread issues please use second option
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
     */

    private val handler = Handler()
    private var replayRunnable: Runnable? = null

    fun startReplay(player: ExoPlayer) {
        replayRunnable = object : Runnable {
            override fun run() {
                player.seekTo(0)
                player.play()
                handler.postDelayed(this, 15000)
            }
        }
        handler.post(replayRunnable!!)
    }

    fun stopReplay() {
        replayRunnable?.let {
            handler.removeCallbacks(it)
        }
    }

}