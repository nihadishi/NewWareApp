package com.example.storeapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.storeapp.api.LoginApiService
import com.example.storeapp.models.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.storeapp.models.LoginRequest

class FragmentLogin : Fragment() {
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        val button = view.findViewById<Button>(R.id.loginLoginButton)

        val usernameEditText = view.findViewById<EditText>(R.id.loginTextUsername)
        val passwordEditText = view.findViewById<EditText>(R.id.loginTextPassword)
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.137.54:8080/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(LoginApiService::class.java)
        sharedPreferences = requireActivity().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        button.setOnClickListener {
            val email = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            val loginRequest =LoginRequest(email, password)
            val call = apiService.login(loginRequest)
            call.enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    try {
                        if (response.isSuccessful) {
                            val token = response.body()?.token

                            if (token != null) {
                                Log.d("Token", "Alınan token: $token")
                                saveTokenToSharedPreferences(token)


                            } else {
                                Toast.makeText(activity, "Token local 10wF17HmcWgDcL", Toast.LENGTH_SHORT).show()
                            }
                            val intent = Intent(activity, HomeActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(
                                activity,
                                "Parol ya da username sehvdir",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } catch (e: Exception) {
                        Log.e("ApiCall", "Exception: ${e.message}")
                            showError("XETA OLDU.")
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.e("ApiCall", "Retrofit onFailure: ${t.message}")
                    Toast.makeText(activity, "Ağ hatası", Toast.LENGTH_SHORT).show()
                }
            })
        }
        return view
    }
    private fun saveTokenToSharedPreferences(token: String) {
        val editor = sharedPreferences.edit()
        editor.putString("token", token)
        editor.apply()
    }
    private fun showError(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()

    }
}
