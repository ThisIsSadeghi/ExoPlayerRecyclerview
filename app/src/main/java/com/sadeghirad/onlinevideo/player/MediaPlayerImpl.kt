/*
 * Copyright (c) 2018 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.sadeghirad.onlinevideo.player

import android.content.Context
import android.net.Uri
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.sadeghirad.onlinevideo.R

class MediaPlayerImpl : MediaPlayer {

  companion object {
    private const val TAG = "MediaPlayerTag"
  }

  private lateinit var exoPlayer: ExoPlayer
  private lateinit var context: Context
  private lateinit var mediaSession: MediaSessionCompat
  private lateinit var stateBuilder: PlaybackStateCompat.Builder

  override fun play(listener: Player.EventListener, url: String) {

    val userAgent = Util.getUserAgent(context, context.getString(R.string.app_name))

    val mediaSource = ExtractorMediaSource.Factory(DefaultDataSourceFactory(context, userAgent))
        .setExtractorsFactory(DefaultExtractorsFactory())
        .createMediaSource(Uri.parse(url))

    exoPlayer.prepare(mediaSource)

    exoPlayer.playWhenReady = true

    exoPlayer.addListener(listener)
  }

  override fun getPlayerImpl(context: Context): ExoPlayer {
    this.context = context
    initializePlayer()
    initializeMediaSession()
    return exoPlayer
  }

  override fun releasePlayer() {
    exoPlayer.stop()
    exoPlayer.release()
  }

  override fun setMediaSessionState(isActive: Boolean) {
    mediaSession.isActive = isActive
  }

  private fun initializePlayer() {

    val trackSelector = DefaultTrackSelector()
    val loadControl = DefaultLoadControl()
    val renderersFactory = DefaultRenderersFactory(context)

    exoPlayer = ExoPlayerFactory.newSimpleInstance(context,renderersFactory, trackSelector, loadControl)
  }

  private fun initializeMediaSession() {
    mediaSession = MediaSessionCompat(context, TAG)
    mediaSession.setFlags(
        MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS or
            MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS
    )
    mediaSession.setMediaButtonReceiver(null)

    stateBuilder = PlaybackStateCompat.Builder()
        .setActions(
            PlaybackStateCompat.ACTION_PLAY or
                PlaybackStateCompat.ACTION_PAUSE or
                PlaybackStateCompat.ACTION_PLAY_PAUSE or
                PlaybackStateCompat.ACTION_FAST_FORWARD or
                PlaybackStateCompat.ACTION_REWIND
        )

    mediaSession.setPlaybackState(stateBuilder.build())

    mediaSession.setCallback(SessionCallback())

    mediaSession.isActive = true
  }

  private inner class SessionCallback : MediaSessionCompat.Callback() {

    private val SEEK_WINDOW_MILLIS = 10000

    override fun onPlay() {
      exoPlayer.playWhenReady = true
    }

    override fun onPause() {
      exoPlayer.playWhenReady = false
    }

    override fun onRewind() {
      exoPlayer.seekTo(exoPlayer.currentPosition - SEEK_WINDOW_MILLIS)
    }

    override fun onFastForward() {
      exoPlayer.seekTo(exoPlayer.currentPosition + SEEK_WINDOW_MILLIS)
    }
  }
}