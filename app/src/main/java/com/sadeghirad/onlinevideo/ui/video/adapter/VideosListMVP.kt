package com.sadeghirad.onlinevideo.ui.video.adapter

interface VideosListMVP {
    interface Model {

    }

    interface View {
        fun setThumbnailImage(url: String)

        fun setVideoTitle(title: String)
    }

    interface Presenter {
        fun onBindVideoRow(holder: VideosListAdapter.VideosViewHolder, position: Int)

        fun getItemCount(): Int
    }
}