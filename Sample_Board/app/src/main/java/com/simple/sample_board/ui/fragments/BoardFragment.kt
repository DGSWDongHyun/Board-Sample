package com.simple.sample_board.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.simple.sample_board.R
import com.simple.sample_board.data.board.BoardData
import com.simple.sample_board.databinding.FragmentBoardBinding
import com.simple.sample_board.ui.activites.ReadActivity
import com.simple.sample_board.ui.adapters.BoardAdapter
import com.simple.sample_board.ui.adapters.listener.onClickItemListener


class BoardFragment : Fragment() {

    private lateinit var boardBinding : FragmentBoardBinding
    private lateinit var boardDataAdapter : BoardAdapter
    private val boardDataList = ArrayList<BoardData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        boardBinding = FragmentBoardBinding.inflate(layoutInflater)
        boardDataAdapter = BoardAdapter(requireContext(), object : onClickItemListener {
            override fun onClickItemListener(position: Int?, data: BoardData) {
                val intent = Intent(requireContext(), ReadActivity::class.java)

                intent.putExtra("detailTitle", data.title)
                intent.putExtra("detailTitle", data.contents)
                intent.putExtra("detailTitle", data.dateTime)
                intent.putExtra("detailTitle", data.userName)

                startActivity(intent)
            }

        })

        return boardBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        boardBinding.recyclerList.layoutManager = LinearLayoutManager(requireContext())
        boardBinding.recyclerList.adapter = boardDataAdapter

        getDataFromDB()

        super.onViewCreated(view, savedInstanceState)
    }

    private fun getDataFromDB(){
        FirebaseDatabase.getInstance().reference.child("list").addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {

                val boardObject = snapshot.getValue(BoardData::class.java)
                boardDataList.add(BoardData(boardObject!!.title, boardObject.contents, boardObject.dateTime, boardObject.userName))
                boardDataAdapter.setData(boardDataList)

            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })


    }


}