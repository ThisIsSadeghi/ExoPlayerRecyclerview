package com.sadeghirad.onlinevideo.ui.fullscreen

import android.os.Bundle
import android.os.Handler
import android.view.View
import com.sadeghirad.onlinevideo.R
import com.sadeghirad.onlinevideo.player.ExoPlayerViewManager
import com.sadeghirad.onlinevideo.ui.base.BaseViewActivity
import kotlinx.android.synthetic.main.activity_fullscreen_video.*
import kotlinx.android.synthetic.main.exo_playback_control_view.*
import kotlinx.android.synthetic.main.exo_simple_player_view.*
import javax.inject.Inject

// Fullscreen related code taken from Android Studio blueprint
class FullscreenVideoActivity : BaseViewActivity(), FullscreenMVP.View {


    @Inject
    lateinit var presenter: FullscreenMVP.Presenter

    private lateinit var mVideoUri: String

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen_video)

        getApplicationComponent().inject(this)
        presenter.setView(this)

        setupPlayer()
        changeFullscreenButtonToExit()

        exo_controller.findViewById<View>(R.id.exo_fullscreen_button)
            .setOnClickListener { presenter.handleFullscreenButtonClick() }
    }

    override fun setupPlayer() {
        mVideoUri = intent.getStringExtra(ExoPlayerViewManager.EXTRA_VIDEO_URI)
        ExoPlayerViewManager.getInstance(this, player_view, mVideoUri).play(null)
    }

    override fun changeFullscreenButtonToExit() {
        exo_fullscreen_icon.setImageResource(R.drawable.exo_controls_fullscreen_exit)
    }

    override fun closeFullscreenActivity() {
        finish()
    }

    override fun makeActivityFullscreen() {
        enclosing_layout!!.systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }

    public override fun onPause() {
        super.onPause()
        presenter.setNavigatiingToFullscreenFalse()
    }

    public override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide()
    }

    override fun hide() {
        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.postDelayed(mHidePart2Runnable, animationDelay.toLong())
    }

    /**
     * Schedules a call to hide() in delay milliseconds, canceling any
     * previously scheduled calls.
     */
    override fun delayedHide() {
        mHideHandler.removeCallbacks(mHideRunnable)
        mHideHandler.postDelayed(mHideRunnable, 100)
    }

    private val animationDelay = 300

    private val mHideHandler = Handler()
    private val mHidePart2Runnable = Runnable {
        // Delayed removal of status and navigation bar
        makeActivityFullscreen()
    }


    private val mHideRunnable = Runnable { hide() }
}