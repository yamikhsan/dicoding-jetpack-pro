package com.studio.yami.ajpfinal.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.studio.yami.ajpfinal.R
import com.studio.yami.ajpfinal.ui.fragment.MovieFragment
import com.studio.yami.ajpfinal.ui.fragment.TvFragment
import com.studio.yami.ajpfinal.ui.fragment.favorite.FavoriteFragment

class MainActivity : AppCompatActivity() {

    companion object{
        const val MOVIE = "movie"
        const val TV = "tv"
    }

    private val container: Int = R.id.fr_container

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_movies -> {
                replaceFragment(MovieFragment(), "movie")
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_tv -> {
                replaceFragment(TvFragment(), "tv")
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_favorite -> {
                replaceFragment(FavoriteFragment(), "favorite")
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        if(savedInstanceState == null){
            navView.selectedItemId = R.id.nav_movies
        }

    }

    private fun replaceFragment(fragment: Fragment, tag: String){
        supportFragmentManager.beginTransaction().replace(container, fragment, tag).commit()
    }

}
