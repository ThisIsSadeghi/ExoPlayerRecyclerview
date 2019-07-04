package com.sadeghirad.onlinevideo.ui.video.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sadeghirad.onlinevideo.R


class VideosListAdapter(val presenter: VideosListPresenter) :
    RecyclerView.Adapter<VideosListAdapter.VideosViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideosViewHolder {
        return VideosViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_videos, parent, false)
        );
    }

    override fun getItemCount(): Int {
        return presenter.getItemCount()
    }

    override fun onBindViewHolder(holder: VideosViewHolder, position: Int) {
        presenter.onBindVideoRow(holder, position)
    }


    inner class VideosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), VideosListMVP.View {

        override fun setThumbnailImage(url: String) {


        }


    }
}