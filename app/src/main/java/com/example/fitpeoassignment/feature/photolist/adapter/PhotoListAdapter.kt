package com.example.fitpeoassignment.feature.photolist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fitpeoassignment.databinding.PhotoListItemBinding
import com.example.fitpeoassignment.feature.photolist.model.Photo

class PhotoListAdapter(private val photoItemClick: (Photo)-> Unit) : ListAdapter<Photo, PhotoViewHolder>(PhotoDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PhotoListItemBinding.inflate(inflater, parent, false)
        return PhotoViewHolder(binding,photoItemClick)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo = getItem(position)
        holder.bind(photo)
    }
}

class PhotoViewHolder(private val binding: PhotoListItemBinding, private val photoItemClick: (Photo) -> Unit) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(photo: Photo) {
        binding.photo = photo
        binding.executePendingBindings()
        binding.root.setOnClickListener {
            photoItemClick.invoke(photo)
        }
    }
}

class PhotoDiffCallback : DiffUtil.ItemCallback<Photo>() {

    override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem == newItem
    }
}