package com.example.discoverdishesapp.MyDishes

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.discoverdishesapp.R

import com.example.discoverdishesapp.databinding.ItemMyDishBinding



class MyDishesAdapter(var items: List<MyDish>, val itemOnClick: (position:Int) -> Unit): Adapter<MyDishesViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyDishesViewHolder {
        val binding = ItemMyDishBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyDishesViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MyDishesViewHolder,
        position: Int
    ) {
        holder.itemView.setOnClickListener {
            itemOnClick(position)
        }
        val myDish = items[position]
        holder.render(myDish)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateItems(items: List<MyDish>) {
        this.items = items
        notifyDataSetChanged()
    }
}

class MyDishesViewHolder(val binding: ItemMyDishBinding) :RecyclerView.ViewHolder(binding.root){
    fun render(myDish: MyDish){
        binding.nameItemMyDish.text = myDish.name
        binding.difficultItemMyDish.text = myDish.difficult
        binding.timeItemMyDish.text = "${myDish.time} min."
        binding.ratingItemMyDish.text = "${myDish.rating}/5"

        if (myDish.image.isNotEmpty()) {
            val bitmap = byteArrayToBitmap(myDish.image)
            binding.myDishImage.setImageBitmap(bitmap)
        } else {
            // Puedes poner una imagen por defecto si no hay foto
            binding.myDishImage.setImageResource(R.drawable.ic_launcher_background)
        }

    }
}
private fun byteArrayToBitmap(image: ByteArray): Bitmap {
    return BitmapFactory.decodeByteArray(image, 0, image.size)
}