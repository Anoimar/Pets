package com.thernat.pets.binding

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.thernat.pets.ui.master.adapter.PetsAdapter
import com.thernat.pets.vo.Pet
import timber.log.Timber

/**
 * Created by m.rafalski on 22/06/2019.
 */
@BindingAdapter("visibleGone")
fun View.setVisibleOrGone( isVisible: Boolean){
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

@BindingAdapter("list")
fun RecyclerView.replaceList(items: List<Pet>?){
    if(adapter is PetsAdapter){
        items?.let { (adapter as PetsAdapter).replaceData(it)}
    }
   }