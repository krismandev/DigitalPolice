package academy.bangkit.digitalpolice

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import academy.bangkit.digitalpolice.databinding.ActivityMainBinding
import academy.bangkit.digitalpolice.ui.notifications.ActionBottom
import academy.bangkit.digitalpolice.ui.notifications.FcmServices
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        supportActionBar!!.setBackgroundDrawable(applicationContext.getDrawable(R.drawable.bg_main))
        supportActionBar!!.hide()
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        val btnNotif = findViewById<View>(R.id.navigation_notifications)
        btnNotif.setOnClickListener {
            val addBottomeSheetDialog = ActionBottom.newInstance()
            addBottomeSheetDialog.show(
                supportFragmentManager,ActionBottom.TAG
            )
        }

//        FirebaseMessaging.getInstance().subscribeToTopic("anomaly")
//        val msgs = getString(R.string.msg_subscribed)
//        Toast.makeText(this, msgs, Toast.LENGTH_SHORT).show()
//
//        val deviceToken = FcmServices
//        val msg = getString(R.string.msg_token_fmt, deviceToken)
//        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
//        FirebaseMessaging.getInstance().token.addOnSuccessListener { deviceToken ->
//            val msg = getString(R.string.msg_token_fmt, deviceToken)
//            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
//            Log.d("OKE",msg)
//        }

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }
}