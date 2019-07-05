package com.sadeghirad.onlinevideo.ui.video


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sadeghirad.onlinevideo.R
import com.sadeghirad.onlinevideo.http.apimodel.Video
import com.sadeghirad.onlinevideo.ui.base.BaseViewFragment
import com.sadeghirad.onlinevideo.ui.video.adapter.VideosListAdapter
import com.sadeghirad.onlinevideo.ui.video.adapter.VideosListPresenter
import kotlinx.android.synthetic.main.fragment_video.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 *
 */
class VideoFragment : BaseViewFragment(), VideoMVP.View {
    @Inject
    lateinit var presenter: VideoMVP.Presenter

    companion object {
        fun getInstance(): VideoFragment {
            return VideoFragment()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getApplicationComponent().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun showNetworkError() {
        showLongSnackbar(getString(R.string.fail_try_again))
    }

    override fun onResume() {
        super.onResume()
        presenter.setView(this)
    }

    override fun loadData(videos: Video) {
        val adapter = context?.let { VideosListAdapter(it, VideosListPresenter(videos)) }
        recyclerViewVideos.layoutManager = LinearLayoutManager(activity)
        recyclerViewVideos.adapter = adapter
    }
}
