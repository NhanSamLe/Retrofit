package vn.fit.retrofit2;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    RecyclerView rcCate;
    CategoryAdapter categoryAdapter;
    ApiService apiService;
    List<Category> categoryList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        AnhXa();
        GetCategory();
    }
    private void AnhXa() {
        rcCate = findViewById(R.id.rc_category);
    }
    private void GetCategory() {

        // Goi Interface trong APIService

        apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        apiService.getCategories().enqueue(new Callback<List<Category>>() {

            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful()) {

                    categoryList = response.body(); // nhận mảng

                    // khởi tạo Adapter

                    categoryAdapter = new CategoryAdapter( MainActivity.this, categoryList);
                    rcCate.setHasFixedSize(true);

                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),
                            LinearLayoutManager.HORIZONTAL, false);

                    rcCate.setLayoutManager(layoutManager);
                    rcCate.setAdapter(categoryAdapter);

                    categoryAdapter.notifyDataSetChanged();

                } else {

                    int statusCode = response.code();

                    // handle request errors depending on status code

                }

            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

                Log.d("logg", t.getMessage());

            }

        });

    }
}