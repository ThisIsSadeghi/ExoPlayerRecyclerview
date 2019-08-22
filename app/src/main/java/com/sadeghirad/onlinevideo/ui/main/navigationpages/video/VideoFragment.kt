package com.sadeghirad.onlinevideo.ui.main.navigationpages.video


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sadeghirad.onlinevideo.R
import com.sadeghirad.onlinevideo.constants.AppConstants
import com.sadeghirad.onlinevideo.http.apimodel.Video
import com.sadeghirad.onlinevideo.ui.base.BaseViewFragment
import com.sadeghirad.onlinevideo.ui.main.navigationpages.video.adapter.VideosListAdapter
import com.sadeghirad.onlinevideo.ui.main.navigationpages.video.adapter.VideosListPresenter
import kotlinx.android.synthetic.main.fragment_video.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 *
 */
class VideoFragment : BaseViewFragment(), VideoMVP.View {
    @Inject
    lateinit var presenter: VideoMVP.Presenter

    private var adapterVideos: VideosListAdapter? = null
    private var videosListPresenter: VideosListPresenter? = null

    private var mView: View? = null

    companion object {
        fun getInstance(): VideoFragment {
            return VideoFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (mView == null)
            mView = inflater.inflate(R.layout.fragment_video, container, false)

        return mView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getApplicationComponent().inject(this)

        presenter.setView(this)
        presenter.getPageData()
    }

    override fun onResume() {
        super.onResume()
        videosListPresenter?.resumeCurrentVideoOnBackFromFullScreen()
    }

    override fun onStop() {
        super.onStop()
        if (!AppConstants.isNavigatingToFullScreen) {
            videosListPresenter?.releaseAllPlayers()
        }
    }

    override fun showNetworkError() {
        showLongSnackbar(getString(R.string.fail_try_again))
    }

    override fun loadData(videos: Video) {
        val layoutManager = LinearLayoutManager(context)
        if (recyclerViewVideos != null) {
            if (adapterVideos == null && videos != null) {
                videosListPresenter =
                    VideosListPresenter(videos)
                adapterVideos = context?.let {
                    VideosListAdapter(
                        it,
                        videosListPresenter!!
                    )
                }
            } else if (adapterVideos != null && videos != null) {
                videosListPresenter!!.setAdapterData(videos)
            }
            recyclerViewVideos.layoutManager = layoutManager
            recyclerViewVideos.adapter = adapterVideos

        }

        recyclerViewVideos.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)


                videosListPresenter?.handlePauseCondition(
                    dy,
                    layoutManager.findFirstVisibleItemPosition(),
                    layoutManager.findLastVisibleItemPosition()
                )


            }
        })

    }
}
