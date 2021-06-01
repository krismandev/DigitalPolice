package academy.bangkit.digitalpolice.ui.home

import academy.bangkit.digitalpolice.R
import academy.bangkit.digitalpolice.core.data.source.remote.models.City
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import java.util.*

class SpinnerCityAdapter(
    context: Context,
    resource: Int,
    spinnerText: Int,
    private val listCity: List<City>)
    : ArrayAdapter<City>(context, resource, spinnerText, listCity) {

    override fun getItem(position: Int): City {
        return listCity[position]
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position)
    }

    private fun initView(position: Int): View {
        val city: City = getItem(position)
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v: View = inflater.inflate(R.layout.item_city, null)
        val textView = v.findViewById<TextView>(R.id.spinnerText)
        textView.setText(city.cityName)
        return v
    }
}
