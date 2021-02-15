package com.simple.sample_board.ui.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.FirebaseDatabase
import com.simple.sample_board.R
import com.simple.sample_board.data.board.BoardData
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
            FirebaseDatabase.getInstance().reference.child("list").push().setValue(BoardData("title","contents","dateTime","userName"))
        }
    }
}