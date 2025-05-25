package com.example.discoverdishesapp.DishesList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.discoverdishesapp.Dish
import com.example.discoverdishesapp.databinding.ItemFoodBinding
import com.squareup.picasso.Picasso

class AdapterDishes(var items: List<Dish>, val onItemClick: (Int)-> Unit): Adapter<DishesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishesViewHolder {
        val binding = ItemFoodBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DishesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DishesViewHolder, position: Int) {
        val dish = items[position]
        holder.render(dish)
        holder.itemView.setOnClickListener {
            onItemClick(position)
        }
    }

    override fun getItemCount(): Int {
       return items.size
    }
    fun updateItems(items: List<Dish>){
        this.items=items
        notifyDataSetChanged()
    }

}

class DishesViewHolder(val binding: ItemFoodBinding) :RecyclerView.ViewHolder(binding.root){
    fun render(dish: Dish){
        binding.dishNameTextView.text = dish.name
        Picasso.get().load(dish.image).into(binding.dishImageView)
    }
}