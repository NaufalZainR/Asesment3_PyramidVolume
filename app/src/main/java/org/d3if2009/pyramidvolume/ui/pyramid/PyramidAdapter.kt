package org.d3if2009.pyramidvolume.ui.pyramid

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.d3if2009.pyramidvolume.R
import org.d3if2009.pyramidvolume.databinding.ItemPyramidBinding
import org.d3if2009.pyramidvolume.model.Pyramid
import org.d3if2009.pyramidvolume.network.PyramidApi

class PyramidAdapter : RecyclerView.Adapter<PyramidAdapter.ViewHolder>() {
    private val data = mutableListOf<Pyramid>()
    fun updateData(newData: List<Pyramid>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }
    class ViewHolder(
        private val binding: ItemPyramidBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pyramid: Pyramid) = with(binding) {
            namaTextView.text = pyramid.nama
            lokasiTextView.text = pyramid.lokasi
            Glide.with(imageView.context)
                .load(PyramidApi.getPyramidUrl(pyramid.image))
                .error(R.drawable.ic_launcher_foreground)
                .into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPyramidBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}