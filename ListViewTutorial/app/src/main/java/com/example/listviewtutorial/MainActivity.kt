package com.example.listviewtutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.listviewtutorial.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), onListener{

    lateinit var mMainBinding: ActivityMainBinding
    var phimAdapter: PhimAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mMainBinding.root)

        val listPhim: List<MovieData> = listOf(
            MovieData(R.drawable.img_1, "Hoang Phi Hong 1", "Hoang Phi hong Va Ngu dai de tu"),
            MovieData(R.drawable.img_2, "Hoang Phi Hong 2", "Hoang Phi hong Va Ngu dai de tu"),
            MovieData(R.drawable.img_3, "Hoang Phi Hong 3", "Hoang Phi hong Va Ngu dai de tu"),
            MovieData(R.drawable.img_4, "Hoang Phi Hong 4", "Hoang Phi hong Va Ngu dai de tu"),
            MovieData(R.drawable.img_1, "Hoang Phi Hong 5", "Hoang Phi hong Va Ngu dai de tu"),
            MovieData(R.drawable.img_2, "Hoang Phi Hong 6", "Hoang Phi hong Va Ngu dai de tu"),
            MovieData(R.drawable.img_3, "Hoang Phi Hong 7", "Hoang Phi hong Va Ngu dai de tu"),
            MovieData(R.drawable.img_4, "Hoang Phi Hong 8", "Hoang Phi hong Va Ngu dai de tu")
        )

        phimAdapter = PhimAdapter(
            listPhim,
            onItemClick = {onItemClickCallback(it)},
            onLongClick = {onLongClickCallback(it)},
            this
        )

        mMainBinding.rcvPhim.adapter = phimAdapter

//        mMainBinding.rcvPhim.layoutManager = LinearLayoutManager(
//            this, LinearLayoutManager.VERTICAL,false)

        mMainBinding.rcvPhim.layoutManager = GridLayoutManager(
            this,
            1,
            GridLayoutManager.VERTICAL,
            false
        )
    }

    private fun onLongClickCallback(phim: MovieData) {
        Log.e("hai.nv4","Long Click Callback: " + phim.tenPhim)
        Toast.makeText(this,"LongClick Callback : "+phim.tenPhim,Toast.LENGTH_SHORT).show()
    }

    private fun onItemClickCallback(phim: MovieData) {
        Log.e("hai.nv4", "Click Item Callback: " + phim.tenPhim)
        Toast.makeText(this,"Click Item Callback: "+phim.tenPhim,Toast.LENGTH_SHORT).show()

        phim.tenPhim = "clicked"

        phimAdapter?.notifyDataSetChanged()
    }

    override fun onDoubleClickListener(phim:MovieData) {
        Log.e("hai.nv4", "Double Click Callback: " + phim.tenPhim)
        Toast.makeText(this,"Double Click Callback: "+phim.tenPhim,Toast.LENGTH_SHORT).show()

        phim.tenPhim = "DoubleClicked"

        phimAdapter?.notifyDataSetChanged()
    }

    override fun onLongClickListener(phim: MovieData) {
        Log.e("hai.nv4","Long Click Listener: " + phim.tenPhim)
        Toast.makeText(this, "Long Click "+ phim.tenPhim, Toast.LENGTH_SHORT).show()
    }
}