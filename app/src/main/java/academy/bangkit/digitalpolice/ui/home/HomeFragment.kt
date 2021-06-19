package academy.bangkit.digitalpolice.ui.home

import academy.bangkit.digitalpolice.R
import academy.bangkit.digitalpolice.core.data.source.remote.models.City
import academy.bangkit.digitalpolice.core.data.source.remote.models.DeviceToken
import academy.bangkit.digitalpolice.core.data.source.remote.models.History
import academy.bangkit.digitalpolice.core.ui.ViewModelFactory
import academy.bangkit.digitalpolice.databinding.FragmentHomeBinding
import academy.bangkit.digitalpolice.ui.notifications.FcmServices
import android.R.layout
import android.R.layout.simple_spinner_item
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.messaging.FirebaseMessaging
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Retrofit

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var factory: ViewModelFactory
    private lateinit var viewModel: HomeViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]
        fcmService()
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null){


            val cityNames = arrayOf("All","Tangerang","Jakarta Pusat","Bogor")
            val historyAdapter = HistoryAdapter()

            showLoading(true)
            viewModel.getHistories().observe(viewLifecycleOwner,{
                showLoading(false)
                historyAdapter.setData(it)
            })


            binding.mySpinner.adapter = ArrayAdapter(requireActivity(),R.layout.item_spinner,cityNames)

            binding.mySpinner.onItemSelectedListener = object : OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    Log.d("SIAP","OKEE SIAP")
                    showLoading(true)
                    val histories = ArrayList<History>()
                    viewModel.getHistories().observe(viewLifecycleOwner,{

                        when(position){
                            0 -> {
                                historyAdapter.setData(it)
                            }
                            1 -> {
                                histories.clear()
                                for (item in it){
                                    if (item.cctv.city.cityName == "Tangerang"){
                                        histories.add(item)
                                    }
                                }
                                historyAdapter.setData(histories)
                            }
                            2 -> {
                                histories.clear()
                                for (item in it){
                                    if (item.cctv.city.cityName == "Jakarta Pusat"){
                                        histories.add(item)
                                    }
                                }
                                historyAdapter.setData(histories)
                            }
                            3 -> {
                                histories.clear()
                                for (item in it){
                                    if (item.cctv.city.cityName == "Bogor"){
                                        histories.add(item)
                                    }
                                }
                                historyAdapter.setData(histories)
                            }
                        }

                        showLoading(false)
                    })
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}

            }

            with(binding.rvHistory) {
                layoutManager = LinearLayoutManager(context)
                this.adapter = historyAdapter
            }
        }
    }

    fun showLoading(state: Boolean){
        if(state){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
        }
    }

    fun fcmService(){
        FirebaseMessaging.getInstance().subscribeToTopic("news")
        val msgs = getString(R.string.msg_subscribed)
        val deviceToken = FcmServices
        val msg = getString(R.string.msg_token_fmt, deviceToken)
        FirebaseMessaging.getInstance().token.addOnSuccessListener { deviceToken ->
            val msg = getString(R.string.msg_token_fmt, deviceToken)
            Log.d("OKE",msg)

            val token = DeviceToken(deviceToken)
            viewModel.postToken(token)

        }



    }

}
