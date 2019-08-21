package com.sadeghirad.onlinevideo.ui.main.navigationpages.video.adapter

import com.sadeghirad.onlinevideo.http.apimodel.customized.ClipModel
import com.sadeghirad.onlinevideo.http.apimodel.customized.VideoDataModel

interface VideosListMVP {

    interface Model

    interface View {
        fun setThumbnailImage(url: String)

        fun setVideoTitle(title: String)

        fun showVideoAndHideThumbnail()

        fun showThumbnailAndHideVideo()

        fun showFullScreenActivity(url: String)

        fun makeVideoPlayerInstance(clip: ClipModel)

        fun showLoading()

        fun hideLoading()

        fun adapterNotifyDataSetChanged()

    }

    interface Presenter {
        fun setView(holder: VideosListAdapter.VideosViewHolder)

        fun onBindVideoRow(holder: VideosListAdapter.VideosViewHolder, position: Int)

        fun getItemCount(): Int

        fun deactivate()

        fun play(holder: VideosListAdapter.VideosViewHolder, clip: ClipModel, position: Int)

        fun releaseAllPlayers()

        fun setMediaSessionState(isActive: Boolean)

        fun handleItemViewClick(holder: VideosListAdapter.VideosViewHolder, position: Int)

        fun handlePlayerFullScreenClick(holder: VideosListAdapter.VideosViewHolder, position: Int)

        fun setAdapterData(videos: VideoDataModel?)

        fun resumeCurrentVideoOnBackFromFullScreen()

        fun tellVideoManagerThatThereIsANewVideoHaHa()

        fun setClip(clipModel: ClipModel)

        fun setHolder(holder: VideosListAdapter.VideosViewHolder)

        fun setCurrentHolderAndClip(clipModel: ClipModel, holder: VideosListAdapter.VideosViewHolder)

        fun getClipModelByPosition(position: Int): ClipModel

        fun getVideoUrlByPosition(position: Int): String?

/*        fun getPlayerManagerInstance(): ExoPlayerViewManager?

        fun setPlayerManagerInstance(instance: ExoPlayerViewManager?)*/

        fun handlePauseCondition(dy: Int, firstVisiblePosition: Int, lastVisiblePosition: Int)

        fun pauseOutOfScreenVideo(position: Int)
    }
}