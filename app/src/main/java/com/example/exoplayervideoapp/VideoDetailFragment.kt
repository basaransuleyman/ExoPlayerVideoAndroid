package com.example.exoplayervideoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.exoplayervideoapp.databinding.VideoDetailFragmentBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem

class VideoDetailFragment : Fragment() {

    private var _binding: VideoDetailFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = VideoDetailFragmentBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        initializePlayer()
        findNavController().let {
            navController = it
        }
    }

    private fun initializePlayer() {
        val videoUrl = arguments?.getString("videoUrl")
        val player = ExoPlayer.Builder(requireContext()).build()

        binding.playerView.player = player

        val mediaItem = videoUrl?.let { MediaItem.fromUri(it) }
        if (mediaItem != null) {
            player.setMediaItem(mediaItem)
        }
        player.prepare()
        player.play()

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}