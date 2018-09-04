package com.example.android.sportsnews.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.android.sportsnews.R;
import com.example.android.sportsnews.api.RequestController;
import com.example.android.sportsnews.data.EventsList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventsActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {

    final private Context context = this;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private ProgressBar progressBar;
    private ListView listView;
    private EventsAdapter adapter;
    private EventsList events = new EventsList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        initViews();

        downloadEvents("football");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, InfoActivity.class);
                intent.putExtra("article", events.getEvents().get(position).getArticle());
                startActivity(intent);
            }
        });
    }

    public void downloadEvents(String category) {
        listView.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        Call<EventsList> call = new RequestController().createRequest().getEvents(category);
        call.enqueue(new Callback<EventsList>() {
            @Override
            public void onResponse(@NonNull Call<EventsList> call, @NonNull Response<EventsList> response) {
                if (response.isSuccessful()) {
                    listView.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);

                    events = response.body();
                    adapter = new EventsAdapter(events, context);
                    listView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                } else {
                    listView.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(EventsActivity.this, "Ошибка запроса", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<EventsList> call, @NonNull Throwable t) {
                listView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(EventsActivity.this, "Ошибка подключения", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void initViews() {
        toolbar =  findViewById(R.id.toolbar);
        drawer =  findViewById(R.id.drawer_layout);
        progressBar = findViewById(R.id.progress_bar);
        navigationView =  findViewById(R.id.nav_view);
        listView = findViewById(R.id.list_view_events);
        adapter = new EventsAdapter(events, this);
        listView.setAdapter(adapter);
        setSupportActionBar(toolbar);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_football) {
            downloadEvents("football");
        } else if (id == R.id.nav_hockey) {
            downloadEvents("hockey");
        } else if (id == R.id.nav_tennis) {
            downloadEvents("tennis");
        } else if (id == R.id.nav_basketball) {
            downloadEvents("basketball");
        } else if (id == R.id.nav_volleyball) {
            downloadEvents("volleyball");
        } else if (id == R.id.nav_cybersport) {
            downloadEvents("cybersport");
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    private static final long TIME_INTERVAL_TO_EXIT = 2000;
    private long mLastTimeBackPressed;

    @Override
    public boolean onKeyDown(final int pKeyCode, final KeyEvent pEvent) {
        if(pKeyCode == KeyEvent.KEYCODE_BACK
                && pEvent.getAction() == KeyEvent.ACTION_DOWN) {
            if(System.currentTimeMillis() - mLastTimeBackPressed < TIME_INTERVAL_TO_EXIT) {

                moveTaskToBack(true);
                finish();
                System.runFinalization();
                System.exit(0);
            }
            else {
                mLastTimeBackPressed = System.currentTimeMillis();

                Toast.makeText(this,"Нажмите ещё раз для выхода" ,Toast.LENGTH_LONG).show();

            }
            return true;
        } else {
            return super.onKeyDown(pKeyCode, pEvent);
        }

    }

}
