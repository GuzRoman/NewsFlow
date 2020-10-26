package com.example.newsflow.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.newsflow.R
import com.example.newsflow.model.network.api.NetService
import com.example.newsflow.viewmodel.AllNewsViewModel
import kotlinx.android.synthetic.main.all_news_fragment.*
import kotlinx.coroutines.launch

class AllNewsFragment : ScopedFragment() {

    private lateinit var viewModel: AllNewsViewModel
    private lateinit var netService: NetService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        netService = NetService()
        viewModel = ViewModelProvider(this).get(AllNewsViewModel::class.java)
        val view = inflater.inflate(R.layout.all_news_fragment, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getNews()

    }

    private fun getNews() = launch {
//        val news = viewModel.newsData
//        news.observe(viewLifecycleOwner, Observer {
//            textViewTest.text = it!!.articles.toString()
//        })
        textViewTest.text = netService.getAllStreetJornal().toString()
    }

}