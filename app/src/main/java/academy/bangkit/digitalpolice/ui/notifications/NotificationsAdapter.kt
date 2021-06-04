package academy.bangkit.digitalpolice.ui.notifications

import academy.bangkit.digitalpolice.R
import academy.bangkit.digitalpolice.core.data.source.remote.models.History
import academy.bangkit.digitalpolice.databinding.ItemNotificationBinding
import academy.bangkit.digitalpolice.ui.detail.DetailActivity
import academy.bangkit.digitalpolice.ui.home.HistoryAdapter
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.text.ParseException
import java.text.SimpleDateFormat

class NotificationsAdapter : RecyclerView.Adapter<NotificationsAdapter.NotificationsViewHolder>() {
    private val listHistory = ArrayList<History>()
    inner class NotificationsViewHolder(private val binding: ItemNotificationBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(history: History){
            with(binding){

                val list = history.createdAt.split("\\.")
                var strDateTime = list[0]
                strDateTime = strDateTime.replace("T"," ")


                event.text = "Crime Detected in ${history.cctv.city.cityName}"
                time.text = modifyDateLayout(strDateTime)

                itemView.setOnClickListener {
                    val intentDetail = Intent(itemView.context, DetailActivity::class.java)
                    intentDetail.putExtra(DetailActivity.EXTRA_HISTORY_ID,history.id)
                    itemView.context.startActivity(intentDetail)
                }
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

    @Throws(ParseException::class)
    private fun modifyDateLayout(inputDate: String): String? {
        val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(inputDate)
        return SimpleDateFormat("dd MMMM yyyy, HH:mm:ss").format(date)
    }
}