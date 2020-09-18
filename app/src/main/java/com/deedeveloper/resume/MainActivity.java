package com.deedeveloper.resume;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.deedeveloper.resume.cv.CVFragment;
import com.deedeveloper.resume.home.HomeFragment;
import com.deedeveloper.resume.portfolio.PortfolioFragment;
import com.deedeveloper.resume.sidemenu.Callback;
import com.deedeveloper.resume.sidemenu.MenuAdapter;
import com.deedeveloper.resume.sidemenu.MenuItem;
import com.deedeveloper.resume.sidemenu.MenuUtil;
import com.deedeveloper.resume.team.TeamFragment;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Callback {

    RecyclerView menuRv;
    List<MenuItem> menuItems;
    MenuAdapter adapter;
    int selectedMenuPos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Transparent Status bar
//        getWindow().setFlags(
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
//        );

        // setup side menu
        setupSideMenu();

        // set the default fragment when activity launch
        setHomeFragment();

    }

    private void setupSideMenu() {

        menuRv = findViewById(R.id.rv_side_menu);

        // get menu list item  will get data from a static data class

        menuItems = MenuUtil.getMenuList();
        adapter = new MenuAdapter(menuItems, this);
        menuRv.setLayoutManager(new LinearLayoutManager(this));
        menuRv.setAdapter(adapter);

    }

    void setPortfolioFragment() {

        getSupportFragmentManager().beginTransaction().replace(R.id.container, new PortfolioFragment()).commit();

    }

    void setTeamFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new TeamFragment()).commit();
    }

    void setCVFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new CVFragment()).commit();
    }


    void setHomeFragment() {

        getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();

    }


    @Override
    public void onSideMenuItemClick(int i) {

        switch (menuItems.get(i).getCode()) {

            case MenuUtil.CV_FRAGMENT_CODE:
                setCVFragment();
                break;
            case MenuUtil.TEAM_FRAGMENT_CODE:
                setTeamFragment();
                break;
            case MenuUtil.PORTFOLIO_FRAGMENT_CODE:
                setPortfolioFragment();
                break;
            default:
                setHomeFragment();
        }

        // highlight the selected menu item

        menuItems.get(selectedMenuPos).setSelected(false);
        menuItems.get(i).setSelected(true);
        selectedMenuPos = i;
        adapter.notifyDataSetChanged();
    }
}
