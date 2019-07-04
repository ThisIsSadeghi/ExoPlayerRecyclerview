package com.sadeghirad.onlinevideo.ui.video.adapter

import com.sadeghirad.onlinevideo.http.apimodel.Video

class VideosListPresenter(private val videos:Video) : VideosListMVP.Presenter{


    override fun onBindVideoRow(holder: VideosListAdapter.VideosViewHolder, position: Int) {
        val currentClip = videos.clips!![position]
        holder.setThumbnailImage(currentClip.thumb!!)
    }

    override fun getItemCount(): Int {
        return videos.clips!!.size
    }
}