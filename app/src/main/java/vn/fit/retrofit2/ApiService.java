package vn.fit.retrofit2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("appfoods/categories.php")
    Call<List<Category>> getCategories();
}
