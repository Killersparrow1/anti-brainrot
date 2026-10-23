package com.brainrot.android.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.ComposeView
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class AntiBrainRotBaseActivity<VM : BaseViewModel> : AppCompatActivity() {

    protected lateinit var composeView: ComposeView
    protected val viewModel: VM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        composeView = ComposeView(this).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        }
        setContentView(composeView)
    }
}