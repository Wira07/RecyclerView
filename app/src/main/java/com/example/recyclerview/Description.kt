package com.example.recyclerview

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Contacts.Photo
import com.example.recyclerview.data.Food
import com.example.recyclerview.databinding.ActivityDescriptionBinding

class Description : AppCompatActivity() {


    companion object {
        const val DATA_FOOD = "extra_data"
    }
    private lateinit var binding: ActivityDescriptionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val food: Food? = intent.getParcelableExtra(DATA_FOOD)
        if (food != null) {
            val resourceId = resources.getIdentifier(food.photo, "drawable", packageName)

            if (resourceId != 0) {
                binding.profileImage.setImageResource(resourceId)
            } else {
                // Handle the case where the resource is not found
                // You might want to set a default image or show an error message
            }

            binding.description.text = food.description
            binding.fullName.text = food.name
            binding.gender.text = food.gender
        }

//        val Food = if (Build.VERSION.SDK_INT >= 33) {
//            intent.getParcelableExtra<Food>(Key_Food, Food::class.java)
//        } else {
//            @Suppress("DEPRECATION")
//            intent.getParcelableExtra<Food>(key_food)
//        }

        binding.shareButton.setOnClickListener { shareButtonClick() }
    }
    private fun shareButtonClick() {
        val sharingIntent = Intent(Intent.ACTION_SEND)
        sharingIntent.type = "text/plain"
        sharingIntent.putExtra(Intent.EXTRA_TEXT, "Check out this description: ${getDescriptionText()}")
        startActivity(Intent.createChooser(sharingIntent, "Share using"))
    }

    private fun getDescriptionText(): String {
        return binding.description.text.toString()
    }
}