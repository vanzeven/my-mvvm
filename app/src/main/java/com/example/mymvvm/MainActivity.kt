package com.example.mymvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.mymvvm.databinding.ActivityMainBinding
import com.example.mymvvm.model.GetAllCarResponse
import com.example.mymvvm.model.GetAllCarResponseItem
import com.example.mymvvm.service.ApiClient
import com.example.mymvvm.service.ApiHelper
import com.example.mymvvm.utils.Status

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(ApiHelper(ApiClient.instance))
        )[MainViewModel::class.java]

        setupObservers()
    }

    private fun setupObservers() {
        viewModel.getAllCarData().observe(this@MainActivity) { resources ->
            when (resources.status) {
                Status.LOADING -> {
                    binding.pbLoading.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    showRecyclerList(resources.data)
                    binding.pbLoading.visibility = View.GONE
                }
                Status.ERROR -> {
                    binding.pbLoading.visibility = View.GONE
                }
            }
        }
    }

    private fun showRecyclerList(data: GetAllCarResponse?) {
        val adapter = MainAdapter(object : MainAdapter.OnClickListener {
            override fun onClickItem(data: GetAllCarResponseItem) {
                Toast.makeText(this@MainActivity, "Menekan ${data.name}", Toast.LENGTH_SHORT).show()
            }
        })

        adapter.submitData(data)
        binding.rvList.adapter = adapter
    }
}