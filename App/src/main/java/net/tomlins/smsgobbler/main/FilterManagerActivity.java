package net.tomlins.smsgobbler.main;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import net.tomlins.smsgobbler.R;

public class FilterManagerActivity extends FragmentActivity implements ActionBar.TabListener {

    private ActionBar filterManagerActionBar;
    private ViewPager filterManagerViewPager;
    private FilterManagerTabsPageAdapter filterManagerTabsPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_manager);

        // Set up the action bar and tab viewer
        //
        filterManagerActionBar = getActionBar();
        filterManagerActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        filterManagerActionBar.setHomeButtonEnabled(false);

        filterManagerTabsPageAdapter = new FilterManagerTabsPageAdapter(getSupportFragmentManager());
        filterManagerViewPager = (ViewPager)findViewById(R.id.filter_manager_view_pager);
        filterManagerViewPager.setAdapter(filterManagerTabsPageAdapter);

        // Add Tabs
        for (String tab_name : getResources().getStringArray(R.array.available_filters)) {
            filterManagerActionBar.addTab(filterManagerActionBar.newTab().setText(tab_name).setTabListener(this));
        }

        /**
         * Change tab on swipe
         * */
        filterManagerViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                filterManagerActionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        filterManagerViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
    }

}