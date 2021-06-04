package academy.bangkit.digitalpolice.ui.detail

import academy.bangkit.digitalpolice.R
import academy.bangkit.digitalpolice.core.data.source.remote.models.History
import academy.bangkit.digitalpolice.core.ui.ViewModelFactory
import academy.bangkit.digitalpolice.databinding.ActivityDetailBinding
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_detail.*
import java.text.ParseException
import java.text.SimpleDateFormat

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    companion object{
        const val EXTRA_HISTORY_ID = "id"
        const val REQUEST_NOTIFICATION = 1
        const val REQUEST_ADAPTER = 2
        const val REQUEST_TYPE = "request_type"
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar!!.setBackgroundDrawable(resources.getDrawable(R.drawable.bg_main))

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        val extras = intent.extras
//        var historyId = 0
//
//        when(getRequestType()){
//            REQUEST_ADAPTER -> {
//                    historyId = intent.getIntExtra(EXTRA_HISTORY_ID,0)
//            }
//            REQUEST_NOTIFICATION -> {
//                    historyId = intent.getStringExtra(EXTRA_HISTORY_ID)!!.toInt()
//            }
//        }

//        val historyId = extras?.getInt(EXTRA_HISTORY_ID)

        if (extras != null){
            val historyId = extras.getInt(EXTRA_HISTORY_ID)
            if (historyId != null) {
                viewModel.selectedHistory(historyId)
            }
            viewModel.getHistory().observe(this,{history->
                showDetail(history)
                binding.btnPlayVideo.setOnClickListener {
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(history.videoLink))
                    startActivity(browserIntent)
                }
            })

        }


    }

    private fun showDetail(history: History){
        val list = history.createdAt.split("\\.")
        var strDateTime = list[0]
        strDateTime = strDateTime.replace("T"," ")

        binding.apply {
            cctvReceived.text = history.cctv.name
            locationReceived.text = history.cctv.city.cityName
            timeReceived.text = modifyDateLayout(strDateTime)
        }
    }

    @Throws(ParseException::class)
    private fun modifyDateLayout(inputDate: String): String? {
        val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(inputDate)
        return SimpleDateFormat("dd MMMM yyyy, HH:mm:ss").format(date)
    }

    fun getRequestType(): Int{
        return intent.getIntExtra(REQUEST_TYPE,0)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

}