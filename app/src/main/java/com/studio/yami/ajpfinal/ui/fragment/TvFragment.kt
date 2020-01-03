package com.studio.yami.ajpfinal.ui.fragment


import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.studio.yami.ajpfinal.R
import com.studio.yami.ajpfinal.adapter.FilmPagedList
import com.studio.yami.ajpfinal.data.server.NetworkState
import com.studio.yami.ajpfinal.ui.activity.MainActivity.Companion.TV
import com.studio.yami.ajpfinal.viewmodel.FilmViewModel
import com.studio.yami.ajpfinal.viewmodel.ViewModelFactory

class TvFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvMain: RecyclerView = view.findViewById(R.id.rv_tv)
        val loading: ProgressBar = view.findViewById(R.id.pro_tv)
        val errorMsg: TextView = view.findViewById(R.id.error_msg_tv)

        val factory = ViewModelFactory(activity!!.application, TV)
        val model: FilmViewModel = ViewModelProviders.of(this, factory).get(FilmViewModel::class.java)

        val adapter = FilmPagedList()
        rvMain.adapter = adapter

        model.getServerList().observe(this, Observer {
            adapter.submitList(it)
        })

        model.networkState().observe(this, Observer {
            when(it.status){
                NetworkState.Status.SUCCESS -> {
                    loading.visibility = View.GONE
                    errorMsg.visibility = View.GONE
                }
                NetworkState.Status.RUNNING -> {
                    loading.visibility = View.VISIBLE
                    errorMsg.visibility = View.GONE
                }
                NetworkState.Status.FAILED -> {
                    loading.visibility = View.GONE
                    errorMsg.visibility = View.VISIBLE
                    errorMsg.text = it.msg
                }
            }
        })

        if (activity?.resources?.configuration?.orientation == Configuration.ORIENTATION_PORTRAIT) {
            rvMain.layoutManager = GridLayoutManager(context, 2)
        } else {
            rvMain.layoutManager = GridLayoutManager(context, 4)
        }

    }

}
