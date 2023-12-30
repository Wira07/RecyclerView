package com.example.recyclerview.splashscreen
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import androidx.appcompat.app.AppCompatActivity
import com.example.recyclerview.MainActivity
import com.example.recyclerview.R
import com.example.recyclerview.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding


    private val SPLASH_TIME_OUT: Long = 3000 // in milliseconds (3 seconds)
    private lateinit var topAnim: Animation
    private lateinit var bottomAnim: Animation


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Animations
        topAnim = android.view.animation.AnimationUtils.loadAnimation(this, R.anim.item_animation)
        bottomAnim = android.view.animation.AnimationUtils.loadAnimation(this, R.anim.item_animation)

        binding.imageViewLogo.startAnimation(topAnim)
        // Handler untuk menunda perpindahan ke activity berikutnya
        Handler().postDelayed({
            val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_TIME_OUT)
    }
}
