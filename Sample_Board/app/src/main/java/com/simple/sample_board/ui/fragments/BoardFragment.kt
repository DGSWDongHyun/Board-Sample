package com.simple.sample_board.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.simple.sample_board.R
import com.simple.sample_board.data.board.BoardData
import com.simple.sample_board.databinding.FragmentBoardBinding
import com.simple.sample_board.ui.adapters.BoardAdapter
import com.simple.sample_board.ui.adapters.listener.onClickItemListener


class BoardFragment : Fragment() {

    private lateinit var boardBinding : FragmentBoardBinding
    private lateinit var boardDataList : BoardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        boardBinding = FragmentBoardBinding.inflate(layoutInflater)
        boardDataList = BoardAdapter(requireContext(), object : onClickItemListener {
            override fun onClickItemListener(position: Int?, data: BoardData) {

            }

        })

        return boardBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        boardBinding.recyclerList.layoutManager = LinearLayoutManager(requireContext())
        boardBinding.recyclerList.adapter = boardDataList

        dummyDataCreate()

        super.onViewCreated(view, savedInstanceState)
    }

    fun dummyDataCreate(){
        var listOfDummy = ArrayList<BoardData>()

        listOfDummy.add(BoardData("제목", "내용", "시간", "이름", 1))

        boardDataList.setData(listOfDummy)
    }

}