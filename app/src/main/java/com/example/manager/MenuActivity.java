package com.example.manager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import PagesAdapter.PagesAdapter;

public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;
    ViewPager2 viewPager2;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    Intent intent;
    Bundle bundle;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        viewPager2 = findViewById(R.id.viewPager);
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.nav_view);
        setSupportActionBar(toolbar);

        intent = getIntent();
        bundle = intent.getExtras();
        if (bundle != null) {
            String username = bundle.getString("USERNAME_ACCOUNT", "");
        }

        // xu ly hien thi Drawer menu
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.Open_drawer, R.string.Close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        setupViewPager(); // goi ham
        Menu menu = navigationView.getMenu();
        MenuItem itemLogout = menu.findItem(R.id.menu_logout);
        // xu ly bam logout
        itemLogout.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem item) {
                // remove data ma Login ban sang
                if (bundle != null) {
                    intent.removeExtra("USERNAME_ACCOUNT");
                    intent.removeExtra("ID_ACCOUNT");
                    intent.removeExtra("EMAIL_ACCOUNT");
                    intent.removeExtra("ROLE_ACCOUNT");
                }
                // quay lai trang dang nhap
                Intent login = new Intent(MenuActivity.this, LoginActivity.class);
                startActivity(login);
                finish(); // khong cho back tro lai
                return false;
            }
        });

        // xu ly click vao cac tab bottom navigation
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.menu_home) {
                viewPager2.setCurrentItem(0);
            } else if (item.getItemId() == R.id.menu_Expense) {
                viewPager2.setCurrentItem(1);
            } else if (item.getItemId() == R.id.menu_Budgets) {
                viewPager2.setCurrentItem(2);
            } else if (item.getItemId() == R.id.menu_Setting) {
                viewPager2.setCurrentItem(3);
            } else {
                viewPager2.setCurrentItem(0);
            }
            return true;
        });
    }

    private void setupViewPager() {
        PagesAdapter adapter = new PagesAdapter(getSupportFragmentManager(), getLifecycle());
        viewPager2.setAdapter(adapter);

        // DÒNG CODE DUY NHẤT ĐƯỢC THÊM VÀO ĐỂ SỬA LỖI
        // Yêu cầu ViewPager2 giữ lại tất cả các Fragment, không hủy chúng khi không hiển thị
        viewPager2.setOffscreenPageLimit(adapter.getItemCount());

        // xu ly khi vuot man hinh chuyen tab
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == 0) {
                    bottomNavigationView.getMenu().findItem(R.id.menu_home).setChecked(true);
                } else if (position == 1) {
                    bottomNavigationView.getMenu().findItem(R.id.menu_Expense).setChecked(true);
                } else if (position == 2) {
                    bottomNavigationView.getMenu().findItem(R.id.menu_Budgets).setChecked(true);
                } else if (position == 3) {
                    bottomNavigationView.getMenu().findItem(R.id.menu_Setting).setChecked(true);
                } else {
                    bottomNavigationView.getMenu().findItem(R.id.menu_home).setChecked(true);
                }
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_home) {
            viewPager2.setCurrentItem(0);
        } else if (item.getItemId() == R.id.menu_Expense) {
            viewPager2.setCurrentItem(1);
        } else if (item.getItemId() == R.id.menu_Budgets) {
            viewPager2.setCurrentItem(2);
        } else if (item.getItemId() == R.id.menu_Setting) {
            viewPager2.setCurrentItem(3);
        }
        drawerLayout.closeDrawer(GravityCompat.START); // dong menu lai
        return true;
    }
}