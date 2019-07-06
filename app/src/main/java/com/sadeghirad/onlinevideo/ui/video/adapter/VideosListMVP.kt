package com.sadeghirad.onlinevideo.ui.video.adapter

import com.sadeghirad.onlinevideo.player.MediaPlayer

interface VideosListMVP {
    interface Model {

    }

    interface View {
        fun setThumbnailImage(url: String)

        fun setVideoTitle(title: String)

        fun showVideoAndHideThumbnail()

        fun showThumbnailAndHideVideo()
    }

    interface Presenter {
        fun onBindVideoRow(holder: VideosListAdapter.VideosViewHolder, position: Int)

        fun getItemCount(): Int

        fun deactivate()

        fun getPlayer(): MediaPlayer

        fun play(holder: VideosListAdapter.VideosViewHolder, url: String)

        fun releasePlayer()

        fun setMediaSessionState(isActive: Boolean)

        fun handleItemViewClick(holder: VideosListAdapter.VideosViewHolder,position: Int)
    }
}