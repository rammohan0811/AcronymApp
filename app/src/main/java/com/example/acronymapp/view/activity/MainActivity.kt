package com.example.acronymapp.view.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.acronymapp.R
import com.example.acronymapp.databinding.ActivityMainBinding
import com.example.acronymapp.model.Lfs
import com.example.acronymapp.view.adapter.MainAdapter
import com.example.acronymapp.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(dataBinding.root)

        initAdapter()

        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        dataBinding.buttonSearch.setOnClickListener {
            hideKeyBoard(dataBinding.txtNoData)
            if (dataBinding.edSearch.text.isNullOrBlank()) {
                dataBinding.edSearch.error = getString(R.string.search_error)
            } else {
                dataBinding.pBar.visibility = View.VISIBLE
                fetchAllResultData(dataBinding.edSearch.text.toString())
            }
        }
    }

    private fun hideKeyBoard(view: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun fetchAllResultData(sf: String) {
        dataBinding.pBar.visibility = View.GONE
        viewModel.fetchAllPosts(sf).observe(this) { list ->
            list?.let {
                if (it.isNotEmpty()) {
                    dataBinding.txtNoData.visibility = View.GONE
                    dataBinding.resultList.visibility = View.VISIBLE
                    adapter.setData(it as ArrayList<Lfs> /* = java.util.ArrayList<com.example.acronymapp.model.Lfs> */)
                } else {
                    dataBinding.txtNoData.visibility = View.VISIBLE
                    dataBinding.resultList.visibility = View.GONE
                }
                return@observe
            }
            dataBinding.txtNoData.visibility = View.VISIBLE
            dataBinding.resultList.visibility = View.GONE
        }
    }


    private fun initAdapter() {
        adapter = MainAdapter()
        dataBinding.resultList.layoutManager = LinearLayoutManager(this)
        dataBinding.resultList.adapter = adapter
    }
}