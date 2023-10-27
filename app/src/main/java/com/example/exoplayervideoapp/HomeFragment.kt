package com.example.exoplayervideoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exoplayervideoapp.databinding.HomeFragmentBinding

class HomeFragment : Fragment() {
    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!
    private val exoPlayerManager = ExoPlayerManager(requireContext()) // Need to Inject
    private val replayManager = ReplayManager() // Need to Inject
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeFragmentBinding.inflate(
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
        setupRecyclerViews()
        findNavController().let {
            navController = it
        }
    }

    private fun setupRecyclerViews() {
        setupHorizontalRecyclerView()
        setupVerticalRecyclerView()
    }

    private fun setupHorizontalRecyclerView() {
        val loopingVideoAdapter = HorizontalAdapter(videoClickListener = { url ->
            url?.let {
                navigateToVideoDetail(it)
            }
        }, exoPlayerManager, replayManager)

        binding.videoListRecyclerViewLoop.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.videoListRecyclerViewLoop.adapter = loopingVideoAdapter
    }

    private fun setupVerticalRecyclerView() {
        val withoutLoopingVideoAdapter =
            VerticalAdapter(videoClickListener = { url ->
                url?.let {
                    navigateToVideoDetail(it)
                }
            }, exoPlayerManager)

        binding.videoListRecyclerViewWithoutLoop.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.videoListRecyclerViewWithoutLoop.adapter = withoutLoopingVideoAdapter
    }

    private fun navigateToVideoDetail(videoUrl: String) {
        val action = R.id.action_homeFragment_to_videoDetailFragment
        val bundle = Bundle().apply {
            putString("videoUrl", videoUrl)
        }
        navController.navigate(action, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}