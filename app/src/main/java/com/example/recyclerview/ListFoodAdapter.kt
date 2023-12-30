package com.example.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyclerview.data.Food
import com.example.recyclerview.databinding.ItemRowSeafoodBinding

class ListFoodAdapter(private val listFood: ArrayList<Food>) : RecyclerView.Adapter<ListFoodAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding = ItemRowSeafoodBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
//        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_seafood, parent, false)
//        return ListViewHolder(view)
//    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, photo) = listFood[position]
        // Menambahkan animasi pada item
        val animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.item_animation)
        holder.itemView.startAnimation(animation)
        Glide.with(holder.itemView.context)
            .load(photo)
            .into(holder.binding.imgItemPhoto)
        holder.binding.tvItemName.text = name
        holder.binding.tvItemDescription.text = description
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listFood[holder.adapterPosition]) }
    }

//    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
//        val (name, description, photo) = listFood[position]
//        // Menambahkan animasi pada item
//        val animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.item_animation)
//        holder.itemView.startAnimation(animation)
////        holder.imgPhoto.setImageResource(photo)
//        Glide.with(holder.itemView.context)
//            .load(photo)
//            .into(holder.imgPhoto)
//        holder.binding.tvItemName.text = name
//        holder.binding.tvItemDescription.text = description
////        holder.tvName.text = name
////        holder.tvDescription.text = description
//        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listFood[holder.adapterPosition]) }
//    }

    override fun getItemCount(): Int = listFood.size

    class ListViewHolder(var binding: ItemRowSeafoodBinding) : RecyclerView.ViewHolder(binding.root)
//    class ListViewHolder(itemView: ItemRowSeafoodBinding) : RecyclerView.ViewHolder(itemView) {
//        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
//        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
//        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)
//    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Food)
    }
}