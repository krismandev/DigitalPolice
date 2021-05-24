package academy.bangkit.digitalpolice

import android.content.Intent
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.RadialGradient
import android.graphics.Shader
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar!!.hide()
//        val tv = findViewById<TextView>(R.id.tv_splash_screen)
//        val paint = tv.paint
//        var width = paint.measureText("DigitalPolice")
//
//        val textShader: Shader = LinearGradient(0f, 0f, width, tv.textSize, intArrayOf(
//            Color.parseColor("#E18A05"),
//            Color.parseColor("#F3BC2E"),
//            Color.parseColor("#FEDE4B"),
//            Color.parseColor("##F4BF31"),
//            Color.parseColor("#FFD07D")
//        ), null, Shader.TileMode.REPEAT)
//
//        tv.paint.setShader(textShader)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        },3000)

    }
}