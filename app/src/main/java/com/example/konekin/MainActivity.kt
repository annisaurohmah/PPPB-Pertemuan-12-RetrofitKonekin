package com.example.konekin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import retrofit2.Call
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.konekin.databinding.ActivityMainBinding
import com.example.konekin.model.RickModel
import com.example.konekin.network.ApiClient
import retrofit2.Response
import java.lang.reflect.Array
import retrofit2.Callback

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val client = ApiClient.getInstance()
        val response = client.getRickCharacter()
        val rickList = ArrayList<String>()


        response.enqueue(object : Callback<RickModel> {
            override fun onResponse(call: Call<RickModel>, response: Response<RickModel>) {
                val thisResult = response.body()
                val datas = thisResult?.results ?: emptyList()
                println("this result ${thisResult?.results?.size}")
                if (datas.isNotEmpty()) {
                    for (i in datas) {
                        rickList.add(i.name)
                    }
                }
                println("this datas ${datas.size}")

                val listAdapter = ArrayAdapter(
                    this@MainActivity,
                    android.R.layout.simple_list_item_1,
                    rickList
                )

                binding.lvName.adapter = listAdapter
            }

            override fun onFailure(call: Call<RickModel>, t: Throwable) {
                Toast.makeText(
                    this@MainActivity, "Error Connection",
                    Toast.LENGTH_LONG
                ).show()
            }
        })

    }
}