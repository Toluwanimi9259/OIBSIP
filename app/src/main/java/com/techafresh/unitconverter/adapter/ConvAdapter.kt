package com.techafresh.unitconverter.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.techafresh.unitconverter.ConverterActivity
import com.techafresh.unitconverter.databinding.RItemBinding
import com.techafresh.unitconverter.model.Category


class ConvAdapter(private val categories : List<Category>) : RecyclerView.Adapter<ConvAdapter.ConvViewHolder>() {

    inner class ConvViewHolder(val binding: RItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(category : Category){
            binding.imageView.setImageResource(category.image)
            binding.textViewCategory.text = category.category
            binding.textViewCategoryItems.text = category.categoryItems
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConvViewHolder {
        val binding = RItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ConvViewHolder(binding)
    }

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: ConvViewHolder, position: Int) {
        holder.bind(categories[position])
        holder.binding.cardView.setOnClickListener {
            val intent = Intent(holder.binding.cardView.context, ConverterActivity::class.java)
                .putExtra("Category" , holder.binding.textViewCategory.text)
            holder.binding.cardView.context.startActivity(intent)
        }
    }
}