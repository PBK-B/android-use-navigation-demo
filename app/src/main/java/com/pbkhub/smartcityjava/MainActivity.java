package com.pbkhub.smartcityjava;

import static androidx.navigation.Navigation.findNavController;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    FragmentContainerView fragmentContainerView;
    BottomNavigationView bottomNavigationView;
    NavHostFragment navHostFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initNav();
    }

    private void initNav() {
        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);

        // 将底部导航绑定 Navigate
        NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment.getNavController());

        // 添加 Navigate 跳转监听，如果参数不带 ShowAppBar 将不显示底部导航栏
        getNavController().addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                if (bundle != null && bundle.getBoolean("ShowAppBar")) {
                    bottomNavigationView.setVisibility(View.VISIBLE);
                } else {
                    bottomNavigationView.setVisibility(View.GONE);
                }
            }
        });
    }

    private NavController getNavController() {
        return navHostFragment.getNavController();
    }

    private void initView() {
        fragmentContainerView = findViewById(R.id.nav_host_fragment);
        bottomNavigationView = findViewById(R.id.a_main_bottomnavigation);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return findNavController(fragmentContainerView).navigateUp();
    }
}