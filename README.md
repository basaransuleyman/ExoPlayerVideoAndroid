 # ExoPlayerVideoAndroid

For more details, please check out my article on [Medium](https://medium.com/@basaransuleyman/android-video-playback-a-comprehensive-guide-with-exoplayer2-acb2220fbd50).

This repository provides a hands-on approach to implementing ExoPlayer 2 in Android applications. The project focuses on setting up ExoPlayer for various use-cases including looping media, creating Instagram story-like features, and managing playback states.

## Introduction
ExoPlayer is an application-level media player built on top of Android's low-level media APIs. Not only is it versatile, but it also offers high performance and numerous customization options. It supports functionalities such as DASH (Dynamic Adaptive Streaming over HTTP), SmoothStreaming, and advanced HLS (HTTP Live Streaming) which are not natively available in Android's MediaPlayer.

## Quick Start
Add the following implementation to your `build.gradle`:

```gradle
implementation("com.google.android.exoplayer:exoplayer:2.19.1")
```

```
Basic Implementation:

val player = ExoPlayer.Builder(context).build()
binding.playerView.player = player
val mediaItem = MediaItem.fromUri("yourURL")
player.setMediaItem(mediaItem)
player.prepare()
player.play()
```

```
Detailed Features
Looping Media with ExoPlayer
To create a seamless looping playback experience:

private var job: Job? = null
val player = ExoPlayer.Builder(root.context).build()
binding.playerView.player = player
val mediaItem = MediaItem.fromUri(url)
player.setMediaItem(mediaItem)
player.prepare()
player.play()

job?.cancel()
job = CoroutineScope(Dispatchers.Main).launch {
    while (isActive) {
        delay(15000)
        player.seekTo(0) // loop
        player.play()
    }
}
```

```
Tips
Playback States

player.addListener(object : Player.Listener {
    override fun onPlaybackStateChanged(playbackState: Int) {
        when (playbackState) {
            Player.STATE_IDLE -> {}
            Player.STATE_BUFFERING -> {}
            Player.STATE_READY -> {}
            Player.STATE_ENDED -> {}
        }
    }
})
```

## Additional Features

- **AnalyticsListener and EventLogger**: Useful for analysis and logging.

- **Dynamic Playlist Management**: Add, remove, or rearrange media items.

- **Repeat and Shuffle Modes**: Control playlist playback.

- **Ads Configuration**: Implement ads in your player.

- **Live Streams Management**: Handle live streams and related features.

- **Adjustable Playback Speed**: Control playback speed dynamically.

 ![SS1](https://s6.gifyu.com/images/S8wjV.gif) 
