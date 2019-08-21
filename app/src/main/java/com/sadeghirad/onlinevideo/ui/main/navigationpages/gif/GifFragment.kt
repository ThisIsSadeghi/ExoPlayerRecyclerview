package com.sadeghirad.onlinevideo.ui.main.navigationpages.gif


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sadeghirad.onlinevideo.R
import com.sadeghirad.onlinevideo.ui.base.BaseViewFragment
import javax.inject.Inject

class GifFragment : BaseViewFragment(), GifMVP.View {

    @Inject
    lateinit var presenter: GifMVP.Presenter

    companion object {
        fun getInstance(): GifFragment {
            return GifFragment()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gif, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getApplicationComponent().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        presenter.setView(this)
    }
}
