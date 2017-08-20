package com.tel.inoob.montagtel.Controller;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.util.Log;
import com.tel.inoob.montagtel.Model.ConsumableByTask;

import java.util.List;

public class ConsumableDiffCallBack extends DiffUtil.Callback {

    private List<ConsumableByTask> oldListOfConsumableByTask;
    private List<ConsumableByTask> newListOfConsumableByTask;

    /**
     *
     * @param oldList old list.
     * @param newList new list.
     */
    public ConsumableDiffCallBack(List<ConsumableByTask> oldList, List<ConsumableByTask> newList){
        this.oldListOfConsumableByTask = oldList;
        this.newListOfConsumableByTask = newList;
    }

    @Override
    public int getOldListSize() {
        return oldListOfConsumableByTask.size();
    }

    @Override
    public int getNewListSize() {
        return newListOfConsumableByTask.size();
    }


    /**
     * Called by the DiffUtil to decide whether two object represent the same Item.
     * For example, if your items have unique ids, this method should check their id equality.
     *
     * @param oldItemPosition the position of the item in the oldList.
     * @param newItemPosition the position of the item in the newList.
     * @return always true because item will the same in any case.
     */
    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldListOfConsumableByTask.get(oldItemPosition).getQuantity() == newListOfConsumableByTask.get(newItemPosition).getQuantity();
    }

    /**
     * Called by the DiffUtil when it wants to check whether two items have the same data.
     * DiffUtil uses this information to detect if the contents of an item has changed.
     * DiffUtil uses this method to check equality instead of equals(Object) so that you can change its behavior depending on your UI.
     * For example, if you are using DiffUtil with a RecyclerView.Adapter, you should return whether the items' visual representations are the same.
     * This method is called only if areItemsTheSame(int, int) returns true for these items.
     *
     * @param oldItemPosition the position of the item in the oldList.
     * @param newItemPosition the position of the item in the newList.
     * @return true if quantity is same another case false.
     */
    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        ConsumableByTask oldItem = oldListOfConsumableByTask.get(oldItemPosition);
        ConsumableByTask newItem = newListOfConsumableByTask.get(newItemPosition);

        return oldItem.getQuantity() == newItem.getQuantity() && oldItem.getName().equals(newItem.getName());

        //return oldListOfConsumableByTask.get(oldItemPosition).equals(newListOfConsumableByTask.get(newItemPosition));
        //return oldListOfConsumableByTask.get(oldItemPosition).getQuantity() == newListOfConsumableByTask.get(newItemPosition).getQuantity();
    }
}
