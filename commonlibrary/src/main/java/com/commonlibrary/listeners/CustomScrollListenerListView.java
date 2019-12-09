package com.commonlibrary.listeners;


import android.util.Log;
import android.widget.AbsListView;
import android.widget.ListView;


/**
 * The purpose of this class is to provide notification to UI in case of over scroll.
 */
public abstract class CustomScrollListenerListView implements AbsListView.OnScrollListener {
    public static String TAG = CustomScrollListenerListView.class.getSimpleName();

    private int previousTotal = 0; // The total number of items in the dataset after the last load
    private boolean loading = true; // True if we are still waiting for the last set of com.guilder.guilder.guilder.data to load.
    private int visibleThreshold = 7; // The minimum amount of items to have below your current scroll position before loading more.

    private ListView listView;
    private boolean loadingList;

    public CustomScrollListenerListView(ListView listView) {
        this.listView = listView;
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        visibleItemCount = view.getChildCount();
        totalItemCount = listView.getCount();
        firstVisibleItem = listView.getFirstVisiblePosition();

        Log.d(TAG,"totalItemCount: "+totalItemCount);
        Log.d(TAG,"previousTotal: "+previousTotal);
        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
            // End has been reached

//            onLoadMore(current_page);
            onLoadMore();
            loading = true;
        }
    }

    protected abstract void onLoadMore();

    public void reset(){
        previousTotal = 0;
    }


    public void setLoadingList(boolean loadingList) {
        this.loadingList = loadingList;
    }

    public boolean isLoadingList() {
        return loadingList;
    }
}
