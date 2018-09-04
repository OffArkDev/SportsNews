package com.example.android.sportsnews.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.sportsnews.R;
import com.example.android.sportsnews.api.RequestController;
import com.example.android.sportsnews.data.Article;
import com.example.android.sportsnews.data.DetailedInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoActivity extends AppCompatActivity {

    private final Context context = this;
    private TextView tvTeam1;
    private TextView tvTeam2;
    private TextView tvTime;
    private TextView tvTournament;
    private TextView tvPlace;
    private ListView lvArticle;
    private TextView tvPrediction;
    private ArticleAdapter adapter;
    private List<Article> articles = new ArrayList<>();

    private ProgressBar progressBar;
    private View viewInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        initViews();
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String article = intent.getStringExtra("article");

        downloadData(article);


    }

    //http://mikonatoruri.win/post.php?article=/2018/02/15/lester-sheffild-junajted-prognoz-na-kubok-anglii-16-02-2018

    public void downloadData(String article) {

        Call<DetailedInfo> call = new RequestController().createRequest().getDetailedInfo(article);
        viewInfo.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<DetailedInfo>() {
            @Override
            public void onResponse(@NonNull Call<DetailedInfo> call, @NonNull Response<DetailedInfo> response) {
                viewInfo.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.INVISIBLE);

                if (response.isSuccessful()) {

                    DetailedInfo detailedInfo = response.body();
                    if (detailedInfo != null) {
                        tvTeam1.setText(detailedInfo.getTeam1());
                        tvTeam2.setText(detailedInfo.getTeam2());
                        tvTime.setText(detailedInfo.getTime());
                        if (detailedInfo.getPlace() == null ||detailedInfo.getPlace().equals(""))
                            tvPlace.setVisibility(View.GONE);
                        else {
                            tvPlace.setText(detailedInfo.getPlace());
                        }
                        tvPrediction.setText(detailedInfo.getPrediction());
                        tvTournament.setText(detailedInfo.getTournament());
                        articles = detailedInfo.getArticles();
                        adapter = new ArticleAdapter(articles, context);
                        lvArticle.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    Toast.makeText(InfoActivity.this, "Ошибка запроса", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<DetailedInfo> call, @NonNull Throwable t) {
                viewInfo.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                Toast.makeText(InfoActivity.this, "Ошибка подключения", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void initViews(){
        tvTeam1 = findViewById(R.id.team1);
        tvTeam2 = findViewById(R.id.team2);
        tvTime = findViewById(R.id.time);
        tvTournament = findViewById(R.id.tournament);
        tvPlace = findViewById(R.id.place);
        lvArticle = findViewById(R.id.list_view_article);
        tvPrediction = findViewById(R.id.prediction);
        adapter = new ArticleAdapter(articles, context);
        lvArticle.setAdapter(adapter);
        progressBar = findViewById(R.id.progress_bar);
        viewInfo = findViewById(R.id.view_info);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();  return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
