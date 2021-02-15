package com.simple.sample_board.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.simple.sample_board.R
import com.simple.sample_board.data.board.BoardData
import com.simple.sample_board.ui.adapters.listener.onClickItemListener
import java.text.SimpleDateFormat


class BoardAdapter(private val aContext: Context, private val listener: onClickItemListener) : RecyclerView.Adapter<RecyclerView.ViewHolder?>() {
    private var positionCheck = 0
    private var isStartViewCheck = true
    var database : DatabaseReference?= null
    private var boardData: List<BoardData>? = null

    fun setData(boardData: ArrayList<BoardData>?) {
        this.boardData = boardData
        notifyDataSetChanged()
    }

    fun getData() : List<BoardData>{
        return boardData!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var root : View ?= null

        when(viewType){
            BOARD -> {

                root = LayoutInflater.from(parent.context).inflate(R.layout.board_item, parent, false)
                return WriteViewHolder(root!!)

            }

            REFRESH -> {

                root = LayoutInflater.from(parent.context).inflate(R.layout.board_item, parent, false)
                return RefreshViewHolder(root!!)

            }

            else -> {

                root = LayoutInflater.from(parent.context).inflate(R.layout.board_item, parent, false)
                return WrongViewHolder(root!!)

            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return boardData!![position].viewType!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val (title, contents, dateTime, userName, viewType) = boardData!![position]

        if(holder is WriteViewHolder){
            holder.title.text = title
            holder.userName.text = "${userName}님"

            if (isStartViewCheck) {
                if (position > 6) isStartViewCheck = false
            } else {
                if (position > positionCheck) {
                    holder.viewAnimation.animation = AnimationUtils.loadAnimation(aContext, R.anim.fall_down)
                } else {
                    holder.viewAnimation.animation = AnimationUtils.loadAnimation(aContext, R.anim.raise_up)
                }
            }

            // 해당 주석은 이미지 처리 관련 코드, 차후에 이미지 코드와 함께 업로드 예

//            if (!image_url.isNullOrEmpty()) {
//                holder.imagePreview.visibility = View.VISIBLE
//                val storage : FirebaseStorage?= FirebaseStorage.getInstance()
//                val storageRef: StorageReference = storage!!.reference.child("$image_url")
//                storageRef.downloadUrl.addOnCompleteListener {
//                    if(it.isSuccessful){
//                        GlideApp.with(aContext)
//                            .load(storageRef)
//                            .centerCrop()
//                            .into(holder.imagePreviews)
//                    }else{
//                        Toast.makeText(aContext, "이미지 로딩에 실패하였습니다.", Toast.LENGTH_LONG).show()
//                    }
//                }
//            }else{
//                holder.imagePreview.visibility = View.GONE
//            }
            holder.itemView.setOnClickListener { v: View? -> listener.onClickItemListener(position, boardData!![position]) }
            positionCheck = position
        }else if(holder is RefreshViewHolder){
        }

    }

    override fun getItemCount(): Int {
        return if (boardData != null) boardData!!.size else 0
    }

    inner class WrongViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){ }

    inner class RefreshViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) { }

    inner class WriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.titleText)
        val userName : TextView = itemView.findViewById(R.id.userNameText)
        val viewAnimation: ConstraintLayout = itemView.findViewById(R.id.viewAnimation)
    }

    companion object {
        const val REFRESH = 0
        const val BOARD = 1

    }
}