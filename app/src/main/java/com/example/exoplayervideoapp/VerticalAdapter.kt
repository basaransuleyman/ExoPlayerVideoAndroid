package com.example.exoplayervideoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exoplayervideoapp.databinding.RecyclerVideoItem2Binding
import com.google.android.exoplayer2.ExoPlayer

class VerticalAdapter(
    private val videoClickListener: (String?) -> Unit,
    private val exoPlayerManager: ExoPlayerManager,
) : RecyclerView.Adapter<VerticalAdapter.VideoCardViewHolder2>() {

    val videoUrisSecond = listOf(
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4",
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4",
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WeAreGoingOnBullrun.mp4"
    )

    private var currentPlayer: ExoPlayer? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoCardViewHolder2 {
        val binding =
            RecyclerVideoItem2Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VideoCardViewHolder2(binding)
    }

    override fun getItemCount(): Int = videoUrisSecond.size

    override fun onBindViewHolder(holder: VideoCardViewHolder2, position: Int) {
        val currentItem = videoUrisSecond[position]
        holder.bind(currentItem, videoClickListener)
    }

    inner class VideoCardViewHolder2(private val binding: RecyclerVideoItem2Binding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(url: String, videoClickListener: (String) -> Unit) {
            with(binding) {
                val player = exoPlayerManager.createPlayer()
                exoPlayerManager.prepareAndPlay(player, url)

                playerView.player = player
                playerView.hideController()
                playerView.controllerAutoShow = false

                playerView.setControllerVisibilityListener { visibility ->
                    if (visibility == View.VISIBLE) {
                        binding.playerView.hideController()
                    }
                }

                currentPlayer = player

                root.setOnClickListener {
                    videoClickListener.invoke(url)
                }
            }
        }
    }

}