package academy.bangkit.digitalpolice.ui.home

import academy.bangkit.digitalpolice.core.data.source.remote.models.City
import academy.bangkit.digitalpolice.core.data.source.remote.models.History
import academy.bangkit.digitalpolice.databinding.ItemHistoryBinding
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class HistoryAdapter() : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
    private val listHistory = ArrayList<History>()

    inner class HistoryViewHolder(private val binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(history: History){
            with(binding){
                cctvName.text = history.cctv.name
                locationName.text = history.cctv.city.cityName
                time.text = history.createdAt
                type.text = history.anomalyType

            }
        }
    }

    fun setData(history: List<History>?) {
        if (history == null) return
        listHistory.clear()
        listHistory.addAll(history)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val itemsHistoryBinding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return HistoryViewHolder(itemsHistoryBinding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val history = listHistory[position]
        holder.bind(history)
    }

    override fun getItemCount(): Int = listHistory.size
}