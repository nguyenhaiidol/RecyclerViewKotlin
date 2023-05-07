package com.example.listviewtutorial

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listviewtutorial.databinding.LayoutItemPhimBinding


class PhimAdapter(
    var listPhim: List<MovieData>,
    public val onItemClick: ((phim: MovieData) -> Unit),
    public var onLongClick: ((phim: MovieData) -> Unit),
    public val listener: onListener
) : RecyclerView.Adapter<PhimAdapter.PhimViewHolder>() {

    var click: Int = 0

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
            //onItemClick(phim)
            //listener.onDoubleClickListener(phim)

            Log.e("hai.nv4", " Click " + click)
            p = phim
            handleClick(phim)
        }

        lateinit var p: MovieData
        var handler = Handler(Looper.getMainLooper())

        fun handleClick(phim: MovieData) {
            click++
            if (click == 1) {
                //handler.postDelayed(runnable, 1000)
            } else {
                //handler.removeCallbacks(runnable)
                listener.onDoubleClickListener(phim)
                click = 0;
            }
        }

    }

    inner class RunnableWithParam : Runnable {
        var param: MovieData

        constructor(param: MovieData) {
            this.param = param
        }

        override fun run() {
            click = 0
            onItemClick(param)
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