package com.sadeghirad.onlinevideo.ui.main.navigationpages.video.adapter

import com.sadeghirad.onlinevideo.http.apimodel.Clip
import com.sadeghirad.onlinevideo.http.apimodel.Video

interface VideosListMVP {

    interface Model

    interface View {
        fun setThumbnailImage(url: String)

        fun setVideoTitle(title: String)

        fun showVideoAndHideThumbnail()

        fun showThumbnailAndHideVideo()

        fun showFullScreenActivity(url: String)

        fun makeVideoPlayerInstance(clip: Clip)

        fun showLoading()

        fun hideLoading()
    }

    interface Presenter {
        fun setView(holder: VideosListAdapter.VideosViewHolder)

        fun onBindVideoRow(holder: VideosListAdapter.VideosViewHolder, position: Int)

        fun getItemCount(): Int

        fun deactivate()

        fun play(holder: VideosListAdapter.VideosViewHolder, clip: Clip, position: Int)

        fun releaseAllPlayers()

        fun pauseAllPlayers()

        fun handleItemViewClick(holder: VideosListAdapter.VideosViewHolder, position: Int)

        fun handlePlayerFullScreenClick(holder: VideosListAdapter.VideosViewHolder, position: Int)

        fun setAdapterData(videos: Video?)

        fun resumeCurrentVideoOnBackFromFullScreen()

        fun setClip(clipModel: Clip)

        fun setHolder(holder: VideosListAdapter.VideosViewHolder)

        fun setCurrentHolderAndClip(clipModel: Clip, holder: VideosListAdapter.VideosViewHolder)

        fun getClipModelByPosition(position: Int): Clip

        fun getVideoUrlByPosition(position: Int): String?

        fun handlePauseCondition(dy: Int, firstVisiblePosition: Int, lastVisiblePosition: Int)

        fun pauseOutOfScreenVideo(position: Int)
    }
}