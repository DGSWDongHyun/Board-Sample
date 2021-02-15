package com.simple.sample_board.data.board

// 데이터 클래스 입니다, 이곳에는 게시글에 들어갈 정보를 담습니다.

data class BoardData(val title : String? = null, val contents : String? = null,
                     val dateTime : String? = null, val userName : String? = null, val viewType : Int?= 0)