import com.example.storeapp.models.Product
import com.example.storeapp.models.ProductResponse
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApiService {

    @GET("v1/product")
    fun getProducts(): Call<ProductResponse>

    @DELETE("v1/product/{id}")
    fun deleteProduct(@Path("id") id: Int): Call<Void>
}
