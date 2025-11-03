package com.vazch.mvvmtemplate.ui.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.vazch.mvvmtemplate.databinding.ActivityMainBinding
import com.vazch.mvvmtemplate.ui.viewmodel.QuoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val quoteViewModel: QuoteViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        quoteViewModel.onCreate()
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.root_container)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
        quoteViewModel.quoteItemLiveData.observe(this, Observer { quoteModel ->
            binding.quoteText.text = quoteModel.quote
            binding.authorText.text = quoteModel.author
        })

        quoteViewModel.isLoadingLiveData.observe(this, Observer{
            binding.loadingIndicator.isVisible = it
        })

        binding.rootContainer.setOnClickListener {
            quoteViewModel.randomQuote()
        }
    }
}

