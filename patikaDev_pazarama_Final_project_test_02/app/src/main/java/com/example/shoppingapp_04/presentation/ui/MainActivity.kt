package com.example.shoppingapp_04.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.shoppingapp_04.R
import com.example.shoppingapp_04.databinding.ActivityMainBinding
import com.example.shoppingapp_04.presentation.viewmodel.MainUiState
import com.example.shoppingapp_04.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()
    companion object {
        const val KEY_NAVIGATE_HOME = "KEY_NAVIGATE_HOME"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lifecycleScope.launchWhenResumed {
            launch {
                viewModel.uiState.collect {
                    when (it) {
                        is MainUiState.Success -> {
                            initNavController(it.isNavigateHome)
                        }
                    }
                }
            }
        }
    }
    private fun initNavController(isNavigateToHome: Boolean) {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)
        if (isNavigateToHome.not()) {
            navController.navigate(R.id.login_graph)
        }
        binding.isVisibleBar = isNavigateToHome

    }
}