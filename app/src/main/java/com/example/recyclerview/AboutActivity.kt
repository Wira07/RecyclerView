package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import com.example.recyclerview.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutBinding
    private lateinit var topAnim: Animation
    private lateinit var bottomAnim: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        topAnim = android.view.animation.AnimationUtils.loadAnimation(this, R.anim.item_animation)
        bottomAnim = android.view.animation.AnimationUtils.loadAnimation(this, R.anim.item_animation)

        binding.profileImage.startAnimation(topAnim)
        binding.wira.startAnimation(bottomAnim)
        binding.email.startAnimation(bottomAnim)
        binding.kelengkapan.startAnimation(bottomAnim)

    }
}