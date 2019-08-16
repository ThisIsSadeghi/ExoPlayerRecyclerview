package com.sadeghirad.onlinevideo.player

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.sadeghirad.onlinevideo.R
import com.sadeghirad.onlinevideo.constants.AppConstants
import kotlinx.android.synthetic.main.activity_fullscreen_video.*

// Fullscreen related code taken from Android Studio blueprint
class FullscreenVideoActivity : AppCompatActivity() {
    private val mHideHandler = Handler()
    private val mHidePart2Runnable = Runnable {
        // Delayed removal of status and navigation bar

        // Note that some of these constants are new as of
        // API 19 (KitKat). It is safe to use them, as they are inlined
        // at compile-time and do nothing on earlier devices.
        enclosing_layout!!.systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }
    private val mHideRunnable = Runnable { hide() }

    private lateinit var mVideoUri: String

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_fullscreen_video)

        mVideoUri = intent.getStringExtra(ExoPlayerViewManager.EXTRA_VIDEO_URI)
        val playerManagerInstance = ExoPlayerViewManager.getInstance(this, player_view, mVideoUri)
        playerManagerInstance.play(null)

        // Set the fullscreen button to "close fullscreen" icon
        val controlView = player_view.findViewById<View>(R.id.exo_controller)
        val fullscreenIcon = controlView.findViewById<ImageView>(R.id.exo_fullscreen_icon)
        fullscreenIcon.setImageResource(R.drawable.exo_controls_fullscreen_exit)

        controlView.findViewById<View>(R.id.exo_fullscreen_button)
            .setOnClickListener { finish() }
    }

    public override fun onResume() {
        super.onResume()
//        ExoPlayerViewManager.getInstance(this, player_view, mVideoUri).goToForeground()
    }

    public override fun onPause() {
        super.onPause()
//        ExoPlayerViewManager.getInstance(this, player_view, mVideoUri).goToBackground()
        AppConstants.isNavigatingToFullScreen = false
    }

    public override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide()
    }

    private fun hide() {
        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY.toLong())
    }

    /**
     * Schedules a call to hide() in delay milliseconds, canceling any
     * previously scheduled calls.
     */
    private fun delayedHide() {
        mHideHandler.removeCallbacks(mHideRunnable)
        mHideHandler.postDelayed(mHideRunnable, 100)
    }

    companion object {

        /**
         * Some older devices needs a small delay between UI widget updates
         * and a change of the status and navigation bar.
         */
        private val UI_ANIMATION_DELAY = 300
    }
}