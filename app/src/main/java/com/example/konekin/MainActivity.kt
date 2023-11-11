package com.example.konekin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import retrofit2.Call
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.konekin.databinding.ActivityMainBinding
import com.example.konekin.model.ResultItem
import com.example.konekin.model.ResponseModel
import com.example.konekin.network.ApiClient
import retrofit2.Response
import java.lang.reflect.Array
import retrofit2.Callback

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var binding: ActivityMainBinding
    private lateinit var itemAdapter: ItemAdapter
    private val rickList = ArrayList<ResultItem>() // Declare the list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        manager = LinearLayoutManager(this)

        // Set up RecyclerView with ItemAdapter
        itemAdapter = ItemAdapter(this@MainActivity, rickList) { clickedData ->
            // Handle item click event if needed
            Toast.makeText(this, "Clicked: ${clickedData.title}", Toast.LENGTH_SHORT).show()
        }

        binding.recyclerView1.layoutManager = manager
        binding.recyclerView1.adapter = itemAdapter

        // Fetch data from the API
        fetchData()
    }

    private fun fetchData() {
        val client = ApiClient.getInstance()
        val response = client.getRickCharacter()

        response.enqueue(object : Callback<ResponseModel> {
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                if (response.isSuccessful) {
                    val thisResult = response.body()
                    val datas = thisResult?.result ?: emptyList()

                    // Update the list directly
                    rickList.clear()
                    rickList.addAll(datas.filterNotNull())

                    // Notify the adapter that the data has changed
                    itemAdapter.notifyDataSetChanged()
                } else {
                    // Handle unsuccessful response
                    Toast.makeText(
                        this@MainActivity, "Error Response",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                // Handle failure
                Toast.makeText(
                    this@MainActivity, "Error Connection",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }
}
