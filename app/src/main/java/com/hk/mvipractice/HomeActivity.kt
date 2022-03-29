package com.hk.mvipractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    private lateinit var numberTv: TextView
    private lateinit var addNumber: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        numberTv = findViewById(R.id.number_tv)
        addNumber = findViewById(R.id.add_number_btn)
        render()
        addNumber.setOnClickListener {
            lifecycleScope.launch {
                viewModel.intent.emit(HomeIntent.AddNumber)
            }
        }
    }

    private fun render() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    when(it) {
                        is HomeViewState.Error -> {
                            Toast.makeText(this@HomeActivity, it.error, Toast.LENGTH_SHORT).show()
                        }
                        is HomeViewState.Idle -> {
                            numberTv.text = "idle"
                        }
                        is HomeViewState.Result -> numberTv.text = it.number.toString()
                    }
                }
            }

        }

    }
}