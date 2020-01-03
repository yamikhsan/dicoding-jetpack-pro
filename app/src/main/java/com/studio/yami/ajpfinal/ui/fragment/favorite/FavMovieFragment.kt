package com.studio.yami.ajpfinal.ui.fragment.favorite


import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.studio.yami.ajpfinal.R
import com.studio.yami.ajpfinal.adapter.FilmPagedList
import com.studio.yami.ajpfinal.ui.activity.MainActivity.Companion.MOVIE
import com.studio.yami.ajpfinal.viewmodel.FilmViewModel
import com.studio.yami.ajpfinal.viewmodel.ViewModelFactory

class FavMovieFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvMain: RecyclerView = view.findViewById(R.id.rv_fav_movie)

        val factory = ViewModelFactory(activity!!.application, MOVIE)
        val model: FilmViewModel = ViewModelProviders.of(this, factory).get(FilmViewModel::class.java)

        model.getFavoriteAll().observe(this, Observer {
            val adapter = FilmPagedList()
            adapter.submitList(it)
            rvMain.adapter = adapter

        })

        if(activity?.resources?.configuration?.orientation == Configuration.ORIENTATION_PORTRAIT){
            rvMain.layoutManager = GridLayoutManager(context, 2)
        }else{
            rvMain.layoutManager = GridLayoutManager(context, 4)
        }

    }

}
