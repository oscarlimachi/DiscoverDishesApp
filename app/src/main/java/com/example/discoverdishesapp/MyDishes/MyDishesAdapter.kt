package com.example.discoverdishesapp.MyDishes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter

import com.example.discoverdishesapp.databinding.ItemMyDishBinding



class MyDishesAdapter(var items: List<MyDish>): Adapter<MyDishesViewHolder>() {
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
        val myDish = items[position]
        holder.render(myDish)
    }

    override fun getItemCount(): Int {
        return items.size
    }

}

class MyDishesViewHolder(val binding: ItemMyDishBinding) :RecyclerView.ViewHolder(binding.root){
    fun render(myDish: MyDish){
        binding.nameItemMyDish.text = myDish.name
        binding.ingredientsItemMyDish.text = myDish.ingredients
        binding.instructionsItemMyDish.text = myDish.instructions
        binding.difficultItemMyDish.text = myDish.difficult
        binding.timeItemMyDish.text = myDish.time
        binding.ratingItemMyDish.text = myDish.rating
    }
}
