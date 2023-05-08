package com.example.listviewtutorial

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.listviewtutorial.databinding.LayoutItemPhimBinding
import com.example.listviewtutorial.handlelick.DoubleClick
import com.example.listviewtutorial.handlelick.DoubleClickListener


class PhimAdapter(
    var listPhim: List<MovieData>,
    public val onItemClick: ((phim: MovieData) -> Unit),
    public var onLongClick: ((phim: MovieData) -> Unit),
    public val listener: onListener
) : RecyclerView.Adapter<PhimAdapter.PhimViewHolder>() {

    var click: Int = 0
    private val handler = Handler()

    inner class PhimViewHolder(val binding: LayoutItemPhimBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnLongClickListener, View.OnClickListener {

        fun init(phim: MovieData) = binding.apply {
            tvTenPhim.text = phim.tenPhim
            tvMoTa.text = phim.moTa
            imgPhim.setImageResource(phim!!.image)

        }

        fun bind() {
            binding.root.setOnClickListener(this)
            binding.root.setOnLongClickListener(this)
        }

        override fun onLongClick(v: View?): Boolean {
            val phim: MovieData = listPhim[adapterPosition]
            onLongClick(phim)
            // listener.onLongClickListener(phim)
            Log.e("hai.nv4", "Long Click!!!")
            return true
        }

        override fun onClick(v: View?) {
            val phim: MovieData = listPhim[adapterPosition]

            handleClick(phim)
        }

        private fun handleClick(phim: MovieData) {
            click++
            handler.postDelayed({
                if (click == 1) {
                    onItemClick(phim)
                }
                if (click >= 2) {
                    listener.onDoubleClickListener(phim)
                }
                click = 0
            }, 200L)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhimViewHolder {
        val binding =
            LayoutItemPhimBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhimViewHolder(binding)
    }


    override fun onBindViewHolder(holder: PhimViewHolder, position: Int) {
        holder.init(listPhim[position])
        holder.bind()

    }

    override fun getItemCount(): Int {
        return listPhim.size
    }


}