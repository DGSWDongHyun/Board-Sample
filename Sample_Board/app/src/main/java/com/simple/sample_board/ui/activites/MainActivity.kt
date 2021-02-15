package com.simple.sample_board.ui.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.simple.sample_board.R
import com.simple.sample_board.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

       setUpLayout()
    }

    private fun setUpLayout(){
        mainBinding.writeFab.setOnClickListener {

        }
    }
}