package com.simple.sample_board.ui.adapters.listener

import com.simple.sample_board.data.board.BoardData

interface onClickItemListener {
    public fun onClickItemListener(position : Int?= 0, data : BoardData);
}