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
//        Toast.makeText(requireContext(), msgs, Toast.LENGTH_SHORT).show()

        val deviceToken = FcmServices
        val msg = getString(R.string.msg_token_fmt, deviceToken)
//        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
        FirebaseMessaging.getInstance().token.addOnSuccessListener { deviceToken ->
            val msg = getString(R.string.msg_token_fmt, deviceToken)
//            Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
            Log.d("OKE",msg)

            val token = DeviceToken(deviceToken)
            viewModel.postToken(token)

        }



    }

}



//class HomeFragment : Fragment() {
//
//    private val cityList = ArrayList<City>()
//    private val cityNames = ArrayList<String>()
//    private lateinit var spinner: Spinner
//
//    //    private lateinit var homeViewModel: HomeViewModel
//    private lateinit var binding: FragmentHomeBinding
////    private lateinit var citySpinner: Spinner
//
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
//        spinner = binding.mySpinner
//        return binding.root
//    }
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        initializeViews()
//
//    }
//    private var adapter: ArrayAdapter<CosmicBody>? = null
//    var categories = arrayOf("All", "Jakarta ", "Bogor", "Tangger")
//
//    /*
//    Initialize ListView and Spinner, set their adapters and listen to spinner itemSelection events
//    */
//    private fun initializeViews() {
//        val factory = ViewModelFactory.getInstance(requireActivity())
//        val viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]
//
//        viewModel.getCities().observe(viewLifecycleOwner,{
//            for (item in it){
//                val city = City(item.id,item.cityName)
//                cityList.add(city)
//            }
//
//            for(item in cityList){
//                cityNames.add(item.cityName)
//            }
//
//
//        })
//
//        binding.mySpinner.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, cityNames)
//        binding.myListView.adapter = ArrayAdapter(
//            requireContext(), android.R.layout.simple_list_item_1,
//            cosmicBodies
//        )
//        //myListView.adapter=adapter
//
//        //spinner selection events
//        binding.mySpinner.onItemSelectedListener = object : OnItemSelectedListener {
//            override fun onItemSelected(
//                adapterView: AdapterView<*>?,
//                view: View,
//                position: Int,
//                itemID: Long
//            ) {
//                Toast.makeText(requireContext(), "SIP WOOOOOOOOI", Toast.LENGTH_SHORT).show()
//                Log.d("SIAP","BISA NIH DI LOG")
//                if (position >= 0 && position < categories.size) {
//                    getSelectedCategoryData(position)
//                } else {
//                    Toast.makeText(
//                        requireContext(),
//                        "Selected Category Does not Exist!",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            }
//
//            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
//        }
//    }
//
//    /*
//   Populate an arraylist that will act as our data source.
//    */
//    private val cosmicBodies: ArrayList<CosmicBody>
//        get() {
//            val data = ArrayList<CosmicBody>()
//            data.add(CosmicBody("Mercury", 1))
//            data.add(CosmicBody("UY Scuti", 1))
//            data.add(CosmicBody("Andromeda", 3))
//            data.add(CosmicBody("VV Cephei A", 2))
//            data.add(CosmicBody("IC 1011", 3))
//            data.add(CosmicBody("Sun", 2))
//            data.add(CosmicBody("Aldebaran", 2))
//            data.add(CosmicBody("Venus", 1))
//            data.add(CosmicBody("Malin 1", 3))
//            data.add(CosmicBody("Rigel", 2))
//            data.add(CosmicBody("Earth", 1))
//            data.add(CosmicBody("Whirlpool", 3))
//            data.add(CosmicBody("VY Canis Majoris", 2))
//            data.add(CosmicBody("Saturn", 1))
//            data.add(CosmicBody("Sombrero", 3))
//            data.add(CosmicBody("Betelgeuse", 2))
//            data.add(CosmicBody("Uranus", 1))
//            data.add(CosmicBody("Virgo Stellar Stream", 3))
//            data.add(CosmicBody("Epsillon Canis Majoris", 2))
//            data.add(CosmicBody("Jupiter", 1))
//            data.add(CosmicBody("VY Canis Majos", 2))
//            data.add(CosmicBody("Triangulum", 3))
//            data.add(CosmicBody("Cartwheel", 3))
//            data.add(CosmicBody("Antares", 2))
//            data.add(CosmicBody("Mayall's Object", 3))
//            data.add(CosmicBody("Proxima Centauri", 2))
//            data.add(CosmicBody("Black Eye", 3))
//            data.add(CosmicBody("Mars", 1))
//            data.add(CosmicBody("Sirius", 2))
//            data.add(CosmicBody("Centaurus A", 3))
//            data.add(CosmicBody("Pinwheel", 3))
//            data.add(CosmicBody("Small Magellonic Cloud", 3))
//            data.add(CosmicBody("Uranus", 1))
//            data.add(CosmicBody("Alpha Centauri", 2))
//            data.add(CosmicBody("Large Magellonic Cloud", 3))
//            return data
//        }
//
//    /*
//   Get the selected category's cosmic bodies and bind to ListView
//    */
//    private fun getSelectedCategoryData(categoryID: Int) {
//        //arraylist to hold selected cosmic bodies
//        val data = ArrayList<CosmicBody>()
//        adapter = if (categoryID == 0) {
//            ArrayAdapter(
//                requireContext(), android.R.layout.simple_list_item_1,
//                cosmicBodies
//            )
//
//        } else {
//            //filter by id
//            for (cosmicBody in cosmicBodies) {
//                if (cosmicBody.categoryId == categoryID) {
//                    data.add(cosmicBody)
//                }
//            }
//            //instatiate adapter a
//            ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, data)
//        }
//        //set the adapter to GridView
//        binding.myListView!!.adapter = adapter
//    }
//
//    /*
//    when activity is created, setContentView then initializeViews.
//     */
//
//}
//
//class CosmicBody(private val name: String, val categoryId: Int) {
//
//    override fun toString(): String {
//        return name
//    }
//}


//class HomeFragment : Fragment() {
//
//    private val cityList = ArrayList<City>()
//    private val cityNames = ArrayList<String>()
//    private lateinit var spinner: Spinner
//
////    private lateinit var homeViewModel: HomeViewModel
//    private lateinit var binding: FragmentHomeBinding
////    private lateinit var citySpinner: Spinner
//
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
//        spinner = binding.mySpinner
//        return binding.root
//    }
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        if (activity != null){
//            val factory = ViewModelFactory.getInstance(requireActivity())
//            val viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]
//            val adapter = HistoryAdapter()
//            val cities = ArrayList<City>()
//            val textCities = ArrayList<String>()
//
//            viewModel.getCities().observe(viewLifecycleOwner,{
//
//                for (item in it){
//                    val city = City(item.id, item.cityName)
//                    cityList?.add(city)
//                }
//
//                Log.d("CityList",cityList.toString())
//
//                for (item in cityList){
//                    cityNames.add(item.cityName)
//                }
//                Log.d("OK",cityNames.toString())
//            })
//            val spinnerArrayAdapter: ArrayAdapter<String> =
//                ArrayAdapter<String>(requireContext(), simple_spinner_item, cityNames)
//            spinnerArrayAdapter.setDropDownViewResource(layout.simple_spinner_dropdown_item) // The drop down view
//            spinner!!.adapter = spinnerArrayAdapter
//
//            spinner.onItemSelectedListener = object : OnItemSelectedListener {
//                override fun onItemSelected(
//                    parent: AdapterView<*>?,
//                    view: View,
//                    position: Int,
//                    id: Long
//                ) {
//                    Toast.makeText(requireContext(), "SIP WOOOOOOOOI", Toast.LENGTH_SHORT).show()
//                    showLoading(true)
//                    viewModel.getHistoryByCity(position).observe(viewLifecycleOwner,{
//                        showLoading(false)
//                        adapter.setData(it)
//                        Log.d("History",it.toString())
//                    })
//
//                }
//
//                override fun onNothingSelected(parent: AdapterView<*>?) {}
//            }
//        }
//    }
//
//
//
//    fun showLoading(state: Boolean){
//        if(state){
//            binding.progressBar.visibility = View.VISIBLE
//        }else{
//            binding.progressBar.visibility = View.GONE
//        }
//    }
//
//}




//class HomeFragment : Fragment() {
//
//    private lateinit var homeViewModel: HomeViewModel
//    private lateinit var binding: FragmentHomeBinding
//    private lateinit var citySpinner: Spinner
//
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        binding = FragmentHomeBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        if (activity != null){
//            val factory = ViewModelFactory.getInstance(requireActivity())
//            val viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]
//            val adapter = HistoryAdapter()
//            val cities = ArrayList<City>()
//            val textCities = ArrayList<String>()
//
//            showLoading(true)
//            viewModel.getHistories().observe(viewLifecycleOwner,{
//                showLoading(false)
//                adapter.setData(it)
//            })
//
//            viewModel.getCities().observe(viewLifecycleOwner,{
//                cities.addAll(it)
//                for (item in it){
//                    textCities.add(item.cityName)
//                }
//            })
//            val cityAdapter = SpinnerCityAdapter(requireActivity(), R.layout.item_city, R.id.spinnerText, cities)
//            binding.mySpinner.adapter = cityAdapter
//
//            binding.mySpinner.setOnItemSelectedListener(object : OnItemSelectedListener {
//                override fun onItemSelected(
//                    parent: AdapterView<*>?,
//                    view: View,
//                    position: Int,
//                    id: Long
//                ) {
//                    Log.d("TEST","OKEE")
//                    val city = cityAdapter.getItem(position)
//                    val cityId = city.id
//                    showLoading(true)
//                    viewModel.getHistoryByCity(cityId).observe(viewLifecycleOwner,{
//                        showLoading(false)
//                        adapter.setData(it)
//                    })
//
//                }
//
//                override fun onNothingSelected(parent: AdapterView<*>?) {}
//            })
//
//            with(binding.rvHistory) {
//                layoutManager = LinearLayoutManager(context)
//                setHasFixedSize(true)
//                this.adapter = adapter
//            }
//        }
//    }
//
//    fun showLoading(state: Boolean){
//        if(state){
//            binding.progressBar.visibility = View.VISIBLE
//        }else{
//            binding.progressBar.visibility = View.GONE
//        }
//    }
//
//}

//class HomeFragment : Fragment() {
//
//    private lateinit var homeViewModel: HomeViewModel
//    private lateinit var binding: FragmentHomeBinding
//    private lateinit var citySpinner: Spinner
//    val historyAdapter = HistoryAdapter()
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        binding = FragmentHomeBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        if (activity != null){
//            val factory = ViewModelFactory.getInstance(requireActivity())
//            val viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]
//            val cities = ArrayList<City>()
//            val textCities = ArrayList<String>()
//
//            showLoading(true)
//            viewModel.getHistories().observe(viewLifecycleOwner,{
//                showLoading(false)
//                historyAdapter.setData(it)
//            })
//
//            viewModel.getCities().observe(viewLifecycleOwner,{
//                cities.addAll(it)
//                for (item in it){
//                    textCities.add(item.cityName)
//                }
//            })
//            binding.mySpinner.adapter = ArrayAdapter(requireActivity(),android.R.layout.simple_list_item_1,textCities)
//
//            binding.mySpinner.onItemSelectedListener = object : OnItemSelectedListener{
//                override fun onItemSelected(
//                    parent: AdapterView<*>?,
//                    view: View?,
//                    position: Int,
//                    id: Long
//                ) {
//                    Log.d("SIAP","OKEE SIAP")
//                    showLoading(true)
//                    viewModel.getHistoryByCity(position).observe(viewLifecycleOwner,{
//                        showLoading(false)
//                        historyAdapter.setData(it)
//                    })
//                }
//
//                override fun onNothingSelected(parent: AdapterView<*>?) {}
//
//            }
//
//            with(binding.rvCity) {
//                layoutManager = LinearLayoutManager(context)
//                this.adapter = historyAdapter
//            }
//        }
//    }
//
//    fun showLoading(state: Boolean){
//        if(state){
//            binding.progressBar.visibility = View.VISIBLE
//        }else{
//            binding.progressBar.visibility = View.GONE
//        }
//    }
//
//}
//
