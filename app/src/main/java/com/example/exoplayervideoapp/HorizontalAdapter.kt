package com.example.exoplayervideoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exoplayervideoapp.databinding.RecyclerVideoItemBinding
import com.google.android.exoplayer2.ExoPlayer

class HorizontalAdapter(
    private val videoClickListener: (String?) -> Unit,
    private val exoPlayerManager: ExoPlayerManager,
    private val replayManager: ReplayManager
) : RecyclerView.Adapter<HorizontalAdapter.VideoCardViewHolder>() {

    val videoUris = listOf(
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4",
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4",
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WeAreGoingOnBullrun.mp4"
    )
    private var currentPlayer: ExoPlayer? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoCardViewHolder {
        val binding =
            RecyclerVideoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VideoCardViewHolder(binding)
    }

    override fun getItemCount(): Int = videoUris.size

    override fun onBindViewHolder(holder: VideoCardViewHolder, position: Int) {
        val currentItem = videoUris[position]
        holder.bind(currentItem, videoClickListener)
    }

    inner class VideoCardViewHolder(private val binding: RecyclerVideoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var isVideoStarted: Boolean = false

        fun bind(url: String, videoClickListener: (String) -> Unit) {
            with(binding) {
                if (!this@VideoCardViewHolder.isVideoStarted) {
                    val player = exoPlayerManager.createPlayer()
                    exoPlayerManager.prepareAndPlay(player, url)
                    player.play()
                    playerView.player = player
                    playerView.hideController()
                    playerView.controllerAutoShow = false

                    playerView.setControllerVisibilityListener { visibility ->
                        if (visibility == View.VISIBLE) {
                            binding.playerView.hideController()
                        }
                    }

                    currentPlayer = player
                    replayManager.stopReplay()
                    replayManager.startReplay(player)
                    this@VideoCardViewHolder.isVideoStarted = true
                    currentPlayer = player

                    root.setOnClickListener {
                        videoClickListener.invoke(url)
                    }
                }
            }
        }

    }

}