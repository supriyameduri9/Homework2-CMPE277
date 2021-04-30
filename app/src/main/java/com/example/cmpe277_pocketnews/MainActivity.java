package com.example.cmpe277_pocketnews;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.cmpe277_pocketnews.Models.ArticleList;
import com.example.cmpe277_pocketnews.Models.NewsList;
import com.example.cmpe277_pocketnews.api.ApiDetails;
import com.example.cmpe277_pocketnews.api.ApiInterface;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.cmpe277_pocketnews.Constants.getCountry;

public class MainActivity extends AppCompatActivity {
    AlertDialog.Builder reset_alert;
    LayoutInflater inflater;
    FirebaseAuth auth;

    public static final String API_KEY = BuildConfig.ApiKey;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<ArticleList> articles = new ArrayList<>();
    private Adapter adapter;
    private String TAG = MainActivity.class.getSimpleName();
    private SwipeRefreshLayout swipeRefreshLayout;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inflater = this.getLayoutInflater();
        reset_alert = new AlertDialog.Builder(this);
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        fetchNews("");
    }

    public void fetchNews(final String keyword) {

        ApiInterface apiInterface;
        apiInterface = ApiDetails.getApiClient().create(ApiInterface.class);

        String country = getCountry();
      //  swipeRefreshLayout.setRefreshing(true);

        Call<NewsList> call ;

        if(keyword.equals("")) {call = apiInterface.getNews(country, API_KEY);}
        else {call = apiInterface.getNewsSearch(keyword,API_KEY);}

        call.enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(Call<NewsList> call, Response<NewsList> response) {
                if (response.isSuccessful() && response.body().getArticle() != null) {
             //       swipeRefreshLayout.setRefreshing(false);
                    if (!articles.isEmpty()) {
                        articles.clear();
                    }
                    articles = response.body().getArticle();
                    adapter = new Adapter(articles, MainActivity.this);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "No Result", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<NewsList> call, Throwable t) {
              //  swipeRefreshLayout.setRefreshing(false) ;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint("Search");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String keyword) {
                fetchNews(keyword);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                fetchNews(newText);
                return false;
            }
        });
        searchMenuItem.getIcon().setVisible(false,false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.resetUserPassword) {
            startActivity(new Intent(getApplicationContext(), ResetPasswordActivity.class));
        }
        if (item.getItemId() == R.id.logoutBtn) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }




}