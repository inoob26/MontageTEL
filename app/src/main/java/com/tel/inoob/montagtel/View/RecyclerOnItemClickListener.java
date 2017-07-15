package com.tel.inoob.montagtel.View;

import android.view.View;

/**
 * Interface binds RecycleView and RVAdapterObject.
 * It means RecycleView listen adapterObjects.
 *
 * @author inoob
 * @since 0.1
 */
public interface RecyclerOnItemClickListener {
    /**
     * Called when an item is clicked.
     *
     * @param position  Position of the item that was clicked.
     * @param isCompleted complete flag done or not.
     */
    void onItemClickRVTaskServiceListAdapter( int position, boolean isCompleted);
}
