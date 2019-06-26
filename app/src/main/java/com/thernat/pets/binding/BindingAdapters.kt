package com.thernat.pets.binding

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.recyclerview.widget.RecyclerView
import com.thernat.pets.ui.master.adapter.PetsAdapter
import com.thernat.pets.vo.Pet

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

@InverseBindingAdapter(attribute = "selectedValue", event = "selectedValueAttrChanged")
fun Spinner.getSelectedValue(): String? {
    return selectedItem.toString()
}

@BindingAdapter("selectedValue")
fun Spinner.setSelectedValue(selectedValue: String?) {
    setSpinnerValue(selectedValue)
}

@BindingAdapter("selectedValueAttrChanged")
fun Spinner.setInverseBindingListener(listener: InverseBindingListener?) {
    if (listener == null) {
        onItemSelectedListener = null
    } else {
        onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                listener.onChange()
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                listener.onChange()
            }
        }
    }
}



fun Spinner.setSpinnerValue(value: String?) {
    if (adapter != null ) {
        val position = (adapter as ArrayAdapter<String>).getPosition(value)
        setSelection(position, false)
        tag = position
    }
}