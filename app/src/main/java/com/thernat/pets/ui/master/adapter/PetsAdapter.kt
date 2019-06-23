package com.thernat.pets.ui.master.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thernat.pets.vo.Pet
import com.thernat.pets.databinding.ItemPetBinding

/**
 * Created by m.rafalski on 24/06/2019.
 */
class PetsAdapter (private var context: Context, private var pets: List<Pet>):
    RecyclerView.Adapter<PetsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPetBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = pets.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(pets[position])

    inner class ViewHolder(val binding: ItemPetBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(model: Pet){
            with(binding){
                pet = model
                executePendingBindings()
//                GlideApp.with(context)
//                    .load(ImageUrlCreator().createProductImageUrl(viewModel.imageUrl))
//                    .into(binding.imageProduct)
            }
        }
    }

    fun replaceData(pets: List<Pet>){
        this.pets = pets
        notifyDataSetChanged()
    }
}