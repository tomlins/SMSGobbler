package net.tomlins.smsgobbler.filterFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.tomlins.smsgobbler.R;

/**
 * Created by jason on 21/11/13.
 */
public class CommingSoonFilterFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_comming_soon_filter, container, false);

        return rootView;
    }
}