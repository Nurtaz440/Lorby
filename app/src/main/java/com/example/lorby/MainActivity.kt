package com.example.lorby

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.lorby.databinding.ActivityMainBinding
import com.example.lorby.utils.NavigationHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var navigationHelper: NavigationHelper
    private val viewBinding by lazy(LazyThreadSafetyMode.NONE) { ActivityMainBinding.inflate(layoutInflater) }
    private val navController by lazy(LazyThreadSafetyMode.NONE) { viewBinding.navHostFragment.findNavController() }

    override fun onCreate(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Set the status bar background color to white
            window.statusBarColor = ContextCompat.getColor(this, R.color.white)

            // Set the status bar text color to dark
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        navigationHelper.navigationBuffer
            .onEach { it(navController) }
            .launchIn(lifecycleScope)
    }
}