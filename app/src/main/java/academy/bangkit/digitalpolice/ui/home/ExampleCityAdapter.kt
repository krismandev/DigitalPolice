package academy.bangkit.digitalpolice.ui.home

import academy.bangkit.digitalpolice.core.data.source.remote.models.City
import academy.bangkit.digitalpolice.databinding.ItemHistoryBinding
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ExampleCityAdapter() : RecyclerView.Adapter<ExampleCityAdapter.CityViewHolder>() {
    private val listCity = ArrayList<City>()

    inner class CityViewHolder(private val binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(city: City){
            with(binding){
                cctvName.text = city.cityName

            }
        }
    }

    fun setData(city: List<City>?) {
        if (city == null) return
        listCity.clear()
        listCity.addAll(city)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val itemsHistoryBinding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return CityViewHolder(itemsHistoryBinding)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city = listCity[position]
        holder.bind(city)
    }

    override fun getItemCount(): Int = listCity.size
}