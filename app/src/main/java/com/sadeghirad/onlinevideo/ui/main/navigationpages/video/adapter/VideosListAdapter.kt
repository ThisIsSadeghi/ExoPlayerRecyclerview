package com.sadeghirad.onlinevideo.ui.main.navigationpages.video.adapter

import android.content.Context
import android.content.Intent
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
import com.sadeghirad.onlinevideo.constants.AppConstants
import com.sadeghirad.onlinevideo.constants.URLs
import com.sadeghirad.onlinevideo.http.apimodel.Clip
import com.sadeghirad.onlinevideo.player.ExoPlayerViewManager
import com.sadeghirad.onlinevideo.ui.fullscreen.FullscreenVideoActivity
import kotlinx.android.synthetic.main.adapter_videos.view.*
import kotlinx.android.synthetic.main.exo_playback_control_view.view.*
import kotlinx.android.synthetic.main.exo_simple_player_view.view.*


class VideosListAdapter(val context: Context, val presenter: VideosListPresenter) :
    RecyclerView.Adapter<VideosListAdapter.VideosViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideosViewHolder {
        return VideosViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_videos, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return presenter.getItemCount()
    }

    override fun onBindViewHolder(holder: VideosViewHolder, position: Int) {
        presenter.onBindVideoRow(holder, position)
    }

    inner class VideosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        VideosListMVP.View {

        init {
            itemView.imgThumbnail.setOnClickListener {

                presenter.handleItemViewClick(this, adapterPosition)
            }

            val fullScreenButton = itemView.exoplayer.exo_controller.exo_fullscreen_button
            fullScreenButton.setOnClickListener {
                presenter.handlePlayerFullScreenClick(this, adapterPosition)
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
            itemView.exoplayer.visibility = View.GONE
        }

        override fun showVideoAndHideThumbnail() {
            itemView.imgThumbnail.visibility = View.GONE
            itemView.exoplayer.visibility = View.VISIBLE
        }

        override fun showLoading() {
            itemView.prg.visibility = View.VISIBLE
        }

        override fun hideLoading() {
            itemView.prg.visibility = View.GONE
        }

        override fun showFullScreenActivity(url: String) {
            AppConstants.isNavigatingToFullScreen = true

            val intent = Intent(context, FullscreenVideoActivity::class.java)
            intent.putExtra(ExoPlayerViewManager.EXTRA_VIDEO_URI, url)
            context.startActivity(intent)
        }

        override fun makeVideoPlayerInstance(clip: Clip) {
            ExoPlayerViewManager.getInstance(context, this.itemView.exoplayer, clip.source)
        }
    }
}