package com.waracle.androidtest.cakes;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.MenuItem;

import com.waracle.androidtest.R;
import com.waracle.androidtest.data.Cake;
import com.waracle.androidtest.service.CakeService;

import java.util.List;

/**
 * Fragment is responsible for loading in some JSON and
 * then displaying a list of cakes with images.
 * Fix any crashes
 * Improve any performance issues
 * Use good coding practices to make code more secure
 */
public class CakesFragment extends ListFragment implements CakesContract.Observer{
    private static final String TAG = CakesFragment.class.getSimpleName();
    private CakeAdapter mAdapter;
    private CakesContract.Observable cakesViewModel;

    public static CakesFragment newInstance(){
        return new CakesFragment();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setHasOptionsMenu(true);

        mAdapter = new CakeAdapter(getContext());
        setListAdapter(mAdapter);

        cakesViewModel = new CakesViewModel(this);
        if(savedInstanceState == null)
            cakesViewModel.loadCakes(CakeService.FetchStrategy.FRESH_UPDATE);
        else
            cakesViewModel.loadCakes(CakeService.FetchStrategy.USE_CACHE);
    }

    @Override
    public void onStop() {
        super.onStop();
        if(cakesViewModel != null)
            cakesViewModel.unsubscribe();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            cakesViewModel.loadCakes(CakeService.FetchStrategy.USE_CACHE);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCakes(List<Cake> cakes) {
        mAdapter.setItems(cakes);
    }

    @Override
    public void onError(String error) {
        Log.d("CakeFragment", "" + error);
        //        Snackbar.make(getCurrentFocus(), R.string.empty_not_saved, BaseTransientBottomBar.LENGTH_LONG).show();

    }
}