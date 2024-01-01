package com.example.recyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.data.Food
import com.example.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var seaFood: RecyclerView
    private val list = ArrayList<Food>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Inisialisasi RecyclerView
        recyclerView = findViewById(R.id.seafood)

        // Set animasi pada RecyclerView
        val itemAnimator = DefaultItemAnimator()
        itemAnimator.addDuration = 6000 // Durasi animasi tambahan
        recyclerView.itemAnimator = itemAnimator

        runItemAnimation()

        seaFood = findViewById(R.id.seafood)
        seaFood.setHasFixedSize(true)

        list.addAll(getListHeroes())
        showRecyclerList()

    }
    private fun runItemAnimation() {
        val animation = AnimationUtils.loadAnimation(this, R.anim.item_animation)

        // Memulai animasi pada setiap item di RecyclerView
        for (i in 0 until recyclerView.childCount) {
            val child = recyclerView.getChildAt(i)
            child.startAnimation(animation)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page -> {
                val moveIntent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(moveIntent)
                return true
            }
            R.id.action_list -> {
                seaFood.layoutManager = LinearLayoutManager(this)
            }
            R.id.action_grid -> {
                seaFood.layoutManager = GridLayoutManager(this, 2)
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun getListHeroes(): ArrayList<Food> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val dataGender = resources.getStringArray(R.array.gender)
        val listFood = ArrayList<Food>()

        for (i in dataName.indices) {
            val food = Food(dataName[i], dataDescription[i], dataPhoto[i], dataGender[i])
            listFood.add(food)
        }
        return listFood
    }

    private fun showSelectedFood(hero: Food) {
        Toast.makeText(this, "Kamu memilih " + hero.name, Toast.LENGTH_SHORT).show()
    }

    private fun showRecyclerList() {
        seaFood.layoutManager = LinearLayoutManager(this)
        val listFoodAdapter = ListFoodAdapter(list)
        seaFood.adapter = listFoodAdapter

        listFoodAdapter.setOnItemClickCallback(object : ListFoodAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Food) {

                val intent = Intent(
                    this@MainActivity,
                    Description::class.java
                )
                intent.putExtra(Description.DATA_FOOD, data)
                startActivity(intent)

                showSelectedFood(data)
            }
        })
    }
}