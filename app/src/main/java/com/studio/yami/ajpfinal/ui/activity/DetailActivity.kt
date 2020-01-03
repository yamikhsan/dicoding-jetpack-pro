package com.studio.yami.ajpfinal.ui.activity

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.studio.yami.ajpfinal.R
import com.studio.yami.ajpfinal.data.local.Favorite
import com.studio.yami.ajpfinal.databinding.ActivityDetailBinding
import com.studio.yami.ajpfinal.viewmodel.FilmViewModel
import com.studio.yami.ajpfinal.viewmodel.ViewModelFactory

class DetailActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_DATA = "EXTRA DATA"
    }

    private var favorite = false
    private lateinit var favoriteSave: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        val film: Favorite = intent.getParcelableExtra(EXTRA_DATA)
        val id = film.id
        val category = film.category

        val loading: ProgressBar = binding.proDetail
        val layoutDetail: ScrollView = binding.layoutDetail
        favoriteSave = binding.floatingSaveFavorite

        val factory = ViewModelFactory(application, category!!)
        val model = ViewModelProviders.of(this, factory).get(FilmViewModel::class.java)

        model.getServerDetail(id!!).observe(this, Observer {
            if(it != null){
                loading.visibility = View.GONE
                layoutDetail.visibility = View.VISIBLE
                favoriteSave.show()

                binding.film = it
            }
        })

        model.getFavoriteById(id).observe(this, Observer {
            favorite = if(it != null){
                favoriteSave.setImageResource(R.drawable.ic_favorite_pink_24dp)
                true
            }else{
                favoriteSave.setImageResource(R.drawable.ic_favorite_border_pink_24dp)
                false
            }
        })

        favoriteSave.setOnClickListener {
            if (favorite){
                model.deleteFavorite(film)
            }else{
                model.insertFavorite(film)
            }
        }

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == android.R.id.home){
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
