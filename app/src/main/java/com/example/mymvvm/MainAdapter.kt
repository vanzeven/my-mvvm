package com.example.mymvvm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mymvvm.databinding.ItemContentBinding
import com.example.mymvvm.model.GetAllCarResponseItem

class MainAdapter(private val onItemClick: OnClickListener) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    private val diffCallback = object : DiffUtil.ItemCallback<GetAllCarResponseItem>() {
        override fun areContentsTheSame(
            oldItem: GetAllCarResponseItem,
            newItem: GetAllCarResponseItem
        ): Boolean =
            oldItem.hashCode() == newItem.hashCode()

        override fun areItemsTheSame(
            oldItem: GetAllCarResponseItem,
            newItem: GetAllCarResponseItem
        ): Boolean =
            oldItem.id == newItem.id
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitData(value: List<GetAllCarResponseItem>?) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemContentBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = differ.currentList[position]
        data.let { holder.bind(data) }
    }

    inner class ViewHolder(private val binding: ItemContentBinding) :
            RecyclerView.ViewHolder(binding.root) {
                fun bind(data: GetAllCarResponseItem) {
                    binding.apply {
                        tvTitle.text = data.name
                        tvPrice.text = data.price.toString()
                        root.setOnClickListener {
                            onItemClick.onClickItem(data)
                        }
                    }
                }
            }

    interface  OnClickListener {
        fun onClickItem(data: GetAllCarResponseItem)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}