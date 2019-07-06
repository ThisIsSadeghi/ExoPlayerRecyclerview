package com.sadeghirad.onlinevideo.ui.video.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.sadeghirad.onlinevideo.R
import com.sadeghirad.onlinevideo.constants.URLs
import kotlinx.android.synthetic.main.adapter_videos.view.*


class VideosListAdapter(val context: Context, val presenter: VideosListPresenter) :
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

        init {
            itemView.imgThumbnail.setOnClickListener {
                itemView.videoPlayer.player = presenter.getPlayer().getPlayerImpl(context)
                presenter.handleItemViewClick(this, adapterPosition)
            }
        }

        override fun setVideoTitle(title: String) {
            itemView.txtTitle.text = title
        }

        override fun setThumbnailImage(url: String) {

            val imageUrl = URLs.THUMBNAILS_BASE_URL + url

            Glide
                .with(context)
                .load(imageUrl)
                .apply(
                    RequestOptions()
                        .transform(MultiTransformation(CenterCrop()))
                )
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(itemView.imgThumbnail)

        }

        override fun showThumbnailAndHideVideo() {
            itemView.imgThumbnail.visibility = View.VISIBLE
            itemView.videoPlayer.visibility = View.GONE
        }

        override fun showVideoAndHideThumbnail() {
            itemView.imgThumbnail.visibility = View.GONE
            itemView.videoPlayer.visibility = View.VISIBLE
        }
    }
}