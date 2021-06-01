package academy.bangkit.digitalpolice.ui.notifications

import academy.bangkit.digitalpolice.core.data.source.remote.models.History
import academy.bangkit.digitalpolice.databinding.ItemNotificationBinding
import academy.bangkit.digitalpolice.ui.home.HistoryAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class NotificationsAdapter : RecyclerView.Adapter<NotificationsAdapter.NotificationsViewHolder>() {
    private val listHistory = ArrayList<History>()
    inner class NotificationsViewHolder(private val binding: ItemNotificationBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(history: History){
            with(binding){
                event.text = history.anomalyType
                time.text = history.createdAt
            }
        }

    }

    fun setData(history: List<History>?) {
        if (history == null) return
        listHistory.clear()
        listHistory.addAll(history)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationsViewHolder {
        val itemNotificationsBinding = ItemNotificationBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return NotificationsViewHolder(itemNotificationsBinding)
    }

    override fun onBindViewHolder(holder: NotificationsViewHolder, position: Int) {
        val history = listHistory[position]
        holder.bind(history)
    }

    override fun getItemCount(): Int = listHistory.size
}