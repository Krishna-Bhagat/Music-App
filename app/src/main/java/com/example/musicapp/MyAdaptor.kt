package com.example.musicapp

import android.app.Activity
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MyAdaptor(val context : Activity, val dataList : List<Data>) : RecyclerView.Adapter<MyAdaptor.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val title: TextView
        val image : ImageView
        val pause : ImageButton
        val play : ImageButton
        init {
            image = itemView.findViewById(R.id.musicimage)
            title = itemView.findViewById(R.id.musictitle)
            pause = itemView.findViewById(R.id.btnpause)
            play = itemView.findViewById(R.id.btnplay)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.each_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //Populate the data into the views
        val currentItem = dataList[position]
        val mediaplayer = MediaPlayer.create(context, currentItem.preview.toUri())

        holder.title.text = currentItem.title
        Picasso.get().load(currentItem.album.cover).into(holder.image)

        holder.pause.setOnClickListener {
            mediaplayer.pause()
        }
        holder.play.setOnClickListener {
            mediaplayer.start()
        }

    }


}