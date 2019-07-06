package com.sadeghirad.onlinevideo.ui.video.adapter

import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player
import com.sadeghirad.onlinevideo.http.apimodel.Video
import com.sadeghirad.onlinevideo.player.MediaPlayerImpl

class VideosListPresenter(private val videos: Video) : VideosListMVP.Presenter {


    override fun handleItemViewClick(holder: VideosListAdapter.VideosViewHolder, position: Int) {
        val url = videos.clips!![position].source
        if (url != null) {
            play(holder, url)
        }
    }

    private val mediaPlayer = MediaPlayerImpl()

    override fun deactivate() {
    }

    override fun getPlayer() = mediaPlayer

    override fun play(holder: VideosListAdapter.VideosViewHolder, url: String) {

        mediaPlayer.play((object : Player.EventListener {
            override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                when (playbackState) {
                    ExoPlayer.STATE_READY -> {
                        holder.showVideoAndHideThumbnail()
                    }

                    ExoPlayer.STATE_ENDED -> {
                        holder.showThumbnailAndHideVideo()
                    }
                }
            }

            override fun onPlayerError(error: ExoPlaybackException?) {

            }
        }), url)
    }

    override fun releasePlayer() = mediaPlayer.releasePlayer()

    override fun setMediaSessionState(isActive: Boolean) {
        mediaPlayer.setMediaSessionState(isActive)
    }

    override fun onBindVideoRow(holder: VideosListAdapter.VideosViewHolder, position: Int) {
        val currentClip = videos.clips!![position]
        holder.setThumbnailImage(currentClip.thumb!!)
        holder.setVideoTitle(currentClip.title!!)
    }

    override fun getItemCount(): Int {
        return videos.clips!!.size
    }
}