package com.example.newsflow.view.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.newsflow.R
import com.example.newsflow.model.network.api.NetService
import com.example.newsflow.viewmodel.AllNewsAdapter
import com.example.newsflow.viewmodel.AllNewsViewModel
import kotlinx.android.synthetic.main.all_news_fragment.*
import kotlinx.android.synthetic.main.all_news_fragment.view.*

class AllNewsFragment : Fragment() {

    private lateinit var viewModel: AllNewsViewModel
    private lateinit var adapter: AllNewsAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.all_news_fragment, container, false)
        adapter = AllNewsAdapter()
        viewModel = ViewModelProvider(this).get(AllNewsViewModel::class.java)
        recyclerView = view.allNewsRecyclerWiew
        recyclerView.adapter = adapter

        setNews()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setNews()

    }

    private fun setNews() {
        val news = viewModel.newsData
        news.observe(viewLifecycleOwner, Observer {
            adapter.setData(it.articles)
        })
    }

}