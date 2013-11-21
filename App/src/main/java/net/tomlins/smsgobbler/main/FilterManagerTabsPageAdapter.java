package net.tomlins.smsgobbler.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import net.tomlins.smsgobbler.filterFragments.CommingSoonFilterFragment;
import net.tomlins.smsgobbler.filterFragments.NumberFilterFragment;

/**
 * Created by jason on 21/11/13.
 */
public class FilterManagerTabsPageAdapter extends FragmentPagerAdapter {

    public FilterManagerTabsPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                return new NumberFilterFragment();
            case 1:
                return new CommingSoonFilterFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 2;
    }

}