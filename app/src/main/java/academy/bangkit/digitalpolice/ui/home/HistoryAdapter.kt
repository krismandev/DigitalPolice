package academy.bangkit.digitalpolice.ui.home

import academy.bangkit.digitalpolice.core.data.source.remote.models.History
import academy.bangkit.digitalpolice.databinding.ItemHistoryBinding
import academy.bangkit.digitalpolice.ui.detail.DetailActivity
import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.text.ParseException
import java.text.SimpleDateFormat


class HistoryAdapter() : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
    private val listHistory = ArrayList<History>()
    companion object{
        const val REQUEST_ADAPTER = 2
    }

    inner class HistoryViewHolder(private val binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(history: History){
            with(binding){

                val list = history.createdAt.split("\\.")
                var strDateTime = list[0]
                strDateTime = strDateTime.replace("T"," ")


                cctvName.text = history.cctv.name
                locationName.text = history.cctv.city.cityName
                time.text = modifyDateLayout(strDateTime)

                itemView.setOnClickListener {
                    val intentDetail = Intent(itemView.context,DetailActivity::class.java)
                    intentDetail.putExtra(DetailActivity.EXTRA_HISTORY_ID,history.id)
                    itemView.context.startActivity(intentDetail)
                }

            }
        }

    }

    @Throws(ParseException::class)
    private fun modifyDateLayout(inputDate: String): String? {
        val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(inputDate)
        return SimpleDateFormat("dd MMMM yyyy, HH:mm:ss").format(date)
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