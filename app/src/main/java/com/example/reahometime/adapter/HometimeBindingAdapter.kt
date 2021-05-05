package com.example.reahometime.adapter

import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.reahometime.data.Hometime

/**
 * Bind the recyclerview's visibility with home time list
 */
@BindingAdapter("visibleWithListForRl")
fun bindVisibleWithListForRl(view: RecyclerView, items: List<Hometime>?) {
    view.visibility = if (items.isNullOrEmpty()) GONE else VISIBLE
}

/**
 * Bind the textview's visibility with home time list
 */
@BindingAdapter("visibleWithListForTv")
fun bindVisibleWithListForTv(view: TextView, items: List<Hometime>?) {
    view.visibility = if (items.isNullOrEmpty()) VISIBLE else GONE
}