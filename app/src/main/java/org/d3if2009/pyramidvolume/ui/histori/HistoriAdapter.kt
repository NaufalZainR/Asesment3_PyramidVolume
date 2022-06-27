package org.d3if2009.pyramidvolume.ui.histori

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.d3if2009.pyramidvolume.databinding.ItemHistoriBinding
import org.d3if2009.pyramidvolume.db.DataEntity
import org.d3if2009.pyramidvolume.model.convert
import java.text.SimpleDateFormat
import java.util.*

class HistoriAdapter :
    ListAdapter<DataEntity, HistoriAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<DataEntity>() {
                override fun areItemsTheSame(
                    oldData: DataEntity, newData: DataEntity
                ): Boolean {
                    return oldData.id == newData.id
                }
                override fun areContentsTheSame(
                    oldData: DataEntity, newData: DataEntity
                ): Boolean {
                    return oldData == newData
                }
            }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoriBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemHistoriBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private val dateFormatter = SimpleDateFormat(
            "dd MMMM yyyy",
            Locale("id", "ID")
        )

        fun bind(item: DataEntity) = with(binding) {
            val hasilConvert = item.convert()

            tanggalTextView.text = dateFormatter.format(Date(item.tanggal))
            outputTextView.text = hasilConvert.hasil.toString()
        }
    }
}