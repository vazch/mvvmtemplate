package com.vazch.mvvmtemplate.ui.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.vazch.mvvmtemplate.databinding.ActivityMainBinding
import com.vazch.mvvmtemplate.ui.viewmodel.QuoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val quoteViewModel: QuoteViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                quoteViewModel.uiState.collect { state ->
                    binding.loadingIndicator.isVisible = state.isLoading
                    binding.quoteText.text = state.current?.quote.orEmpty()
                    binding.authorText.text = state.current?.author.orEmpty()
                }
            }
        }

        binding.rootContainer.setOnClickListener { quoteViewModel.randomQuote() }
    }
}

