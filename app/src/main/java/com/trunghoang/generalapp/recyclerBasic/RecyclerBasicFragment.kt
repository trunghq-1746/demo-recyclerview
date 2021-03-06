package com.trunghoang.generalapp.recyclerBasic


import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.trunghoang.generalapp.MovieRepository
import com.trunghoang.generalapp.MoviesRecyclerAdapter

import com.trunghoang.generalapp.R
import kotlinx.android.synthetic.main.fragment_recycler_basic.*

class RecyclerBasicFragment : Fragment() {
    private val repo: MovieRepository by lazy {
        MovieRepository()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recycler_basic, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = MoviesRecyclerAdapter(repo.getAFewMovies())
        val linearLayout = object : LinearLayoutManager(context) {
            override fun supportsPredictiveItemAnimations(): Boolean {
                return false
            }
        }
        recyclerBasic.adapter = adapter
        recyclerBasic.layoutManager = linearLayout
        recyclerBasic.setRecyclerListener {
            Log.d("RecyclerView", "Put $it")
        }
        buttonRemove.setOnClickListener {
            adapter.removeItem()
        }
        buttonChange.setOnClickListener {
            adapter.changeItem()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = RecyclerBasicFragment()
    }
}
