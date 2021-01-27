package com.example.paltica.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.paltica.R

class SimpleCardAdapter<T> (var list: MutableList<T>, val populateItem: (view: View, item: T) -> Unit): RecyclerView.Adapter<SimpleCardAdapter<T>.SimpleViewHolder>() {

    inner class SimpleViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        fun populate(item: T) = populateItem(view, item)
    }

    fun update(list: MutableList<T>) {
        this.list.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_item, parent, false)

        return SimpleViewHolder(view)
    }

    override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) {
        holder.populate(list[position])
    }

    override fun getItemCount(): Int = list.size

}