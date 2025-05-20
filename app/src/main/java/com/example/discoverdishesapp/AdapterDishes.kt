package com.example.discoverdishesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.discoverdishesapp.databinding.ItemFoodBinding

class AdapterDishes(val item: List<Dish>,val onItemClick: (Int) ->Unit): Adapter<DishesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishesViewHolder {
        val binding = ItemFoodBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DishesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DishesViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
       return item.size
    }
}

class DishesViewHolder(view: View) :RecyclerView.ViewHolder(view){

}