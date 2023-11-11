package com.example.konekin

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.konekin.databinding.ItemLayoutBinding
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.konekin.model.ResultItem
import org.w3c.dom.Text


typealias onClickData = (ResultItem) -> Unit

class ItemAdapter (private val context: Context, private val listData: List<ResultItem>, private val
onClickData: onClickData): RecyclerView.Adapter<ItemAdapter.ItemDataViewHolder>(){
    inner class ItemDataViewHolder (private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind (data:ResultItem){
            with(binding){
                titleTextView.text = data.title
                itemView.setOnClickListener{
                    onClickData(data)
                }
                Glide.with(context)
                    .load(data.image).centerCrop().into(imageView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemDataViewHolder {
        val binding = ItemLayoutBinding.inflate(
            LayoutInflater.from(
            parent.context), parent, false
        )
        return ItemDataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemDataViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size


}