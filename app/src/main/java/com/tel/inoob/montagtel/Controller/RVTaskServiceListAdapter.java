package com.tel.inoob.montagtel.Controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import com.tel.inoob.montagtel.Model.TaskService;
import com.tel.inoob.montagtel.R;
import com.tel.inoob.montagtel.View.RecyclerOnItemClickListener;

import java.util.List;

/**
 * @author inoob
 * @since 0.1
 */
public class RVTaskServiceListAdapter extends RecyclerView.Adapter<RVTaskServiceListAdapter.TaskServiceHolder> {

    private List<TaskService> taskServiceList;
    private RecyclerOnItemClickListener itemClickListener;

    public RVTaskServiceListAdapter(){

    }

    /**
     *
     * @param taskServiceList
     */
    public void setTaskServiceList(List<TaskService> taskServiceList) {
        this.taskServiceList = taskServiceList;
    }

    /**
     *
     * @param itemClickListener
     */
    public void setItemClickListener(RecyclerOnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public TaskServiceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_task_service_list,parent,false);
        TaskServiceHolder taskServiceHolder = new TaskServiceHolder(view);

        return taskServiceHolder;
    }

    @Override
    public void onBindViewHolder(TaskServiceHolder holder, int position) {
        holder.task_service_name.setText(taskServiceList.get(position).getServiceName());
        holder.task_service_flag.setChecked(taskServiceList.get(position).isCompleted());
        holder.position = position;
    }

    @Override
    public int getItemCount() {
        return taskServiceList.size();
    }

    /**
     * Describe 1 object from list.
     */
    public class TaskServiceHolder extends RecyclerView.ViewHolder {
        private TextView task_service_name;
        private Switch task_service_flag;
        /**
         * keep elemnet position of list.
         */
        private int position;

        public TaskServiceHolder(final View itemView) {
            super(itemView);
            task_service_name = (TextView) itemView.findViewById(R.id.card_view_task_service_name);
            task_service_flag = (Switch) itemView.findViewById(R.id.card_view_task_service_switch);
            task_service_flag.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked){
                        itemClickListener.onItemClickRVTaskServiceListAdapter(position,isChecked);
                    } else {
                        itemClickListener.onItemClickRVTaskServiceListAdapter(position,isChecked);
                    }
                }
            });
        }
    }
}
