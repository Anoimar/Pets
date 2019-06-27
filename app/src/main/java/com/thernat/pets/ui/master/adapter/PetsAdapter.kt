package com.thernat.pets.ui.master.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thernat.pets.GlideApp
import com.thernat.pets.vo.Pet
import com.thernat.pets.databinding.ItemPetBinding
import com.thernat.pets.utils.image.ImageUrlProvider

/**
 * Created by m.rafalski on 24/06/2019.
 */
class PetsAdapter (private var context: Context, private var pets: List<Pet>,private val imageUrlProvider: ImageUrlProvider):
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
               GlideApp.with(context)
                   .load(imageUrlProvider.getPetTypeImage(model.type))
                  .into(binding.imagePetPicture)
            }
        }
    }

    fun replaceData(pets: List<Pet>){
        this.pets = pets
        notifyDataSetChanged()
    }
}