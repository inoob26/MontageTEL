package com.tel.inoob.montagtel.Controller;

import android.support.v7.util.DiffUtil;
import com.tel.inoob.montagtel.Model.TaskService;

import java.util.List;

/**
 * @author inoob
 * @since 0.1
 */
public class TaskServiceDiffCallback extends DiffUtil.Callback {

    private List<TaskService> oldTaskServiceList;
    private List<TaskService> newTaskServiceList;

    public TaskServiceDiffCallback(List<TaskService> newTaskServiceList, List<TaskService> oldTaskServiceList){
        this.newTaskServiceList = newTaskServiceList;
        this.oldTaskServiceList = oldTaskServiceList;
    }

    @Override
    public int getOldListSize() {
        return oldTaskServiceList.size();
    }

    @Override
    public int getNewListSize() {
        return newTaskServiceList.size();
    }

    /**
     * check list size.
     * @param oldItemPosition the position of the item in the oldList.
     * @param newItemPosition the position of the item in the newList.
     * @return true if size is not same and false if size is same.
     */
    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        //return oldTaskServiceList.get(oldItemPosition).isCompleted() == newTaskServiceList.get(newItemPosition).isCompleted();
        return getOldListSize() != getNewListSize();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        //return oldTaskServiceList.get(oldItemPosition).equals(newTaskServiceList.get(newItemPosition));
        return false;
    }
}
