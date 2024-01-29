package com.example.storeapp

import ProductAdapter
import ProductApiService
import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.storeapp.databinding.FragmentHomeBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.storeapp.models.Product
import com.example.storeapp.models.ProductResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class FragmentHome : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    // Product listesini tanımlayın
    private var products: Array<Product>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
//        val btnHome: Button = binding.btnDelete
        if (isNetworkAvailable(requireContext())) {
//            loadDataFromApi()
        } else {
            showNoInternetAlert()
        }
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.137.54:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val productApiService = retrofit.create(ProductApiService::class.java)
        val call: Call<ProductResponse> = productApiService.getProducts()

        call.enqueue(object : Callback<ProductResponse> {
            override fun onResponse(call: Call<ProductResponse>, response: Response<ProductResponse>) {
                val productResponse = response.body()

                if (response.isSuccessful && productResponse != null) {
                    val products = productResponse.data

                    val adapter = ProductAdapter(products!!)
                    recyclerView.adapter = adapter
                } else {
                    Log.e("ProductApiService", "Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                Log.e("ProductApiService", "Error: ${t.message}")
            }
        })




    }
    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    private fun showNoInternetAlert() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("No Internet Connection")
        builder.setMessage("Please check your internet connection and try again.")
        builder.setPositiveButton("OK") { _, _ ->
            // Uygulamayı kapatma veya başka bir işlem yapma kodunu ekleyin.
        }
        builder.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}




