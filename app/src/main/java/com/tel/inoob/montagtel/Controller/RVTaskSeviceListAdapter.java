package com.tel.inoob.montagtel.Controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import com.tel.inoob.montagtel.Model.TaskService;
import com.tel.inoob.montagtel.R;

import java.util.List;

/**
 * @author inoob
 * @since 0.1
 */
public class RVTaskSeviceListAdapter extends RecyclerView.Adapter<RVTaskSeviceListAdapter.TaskServiceHolder> {

    private List<TaskService> taskServiceList;

    public RVTaskSeviceListAdapter(List<TaskService> taskServiceList){
        this.taskServiceList = taskServiceList;
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
    }

    @Override
    public int getItemCount() {
        return taskServiceList.size();
    }

    public class TaskServiceHolder extends RecyclerView.ViewHolder {
        private TextView task_service_name;
        private Switch task_service_flag;

        public TaskServiceHolder(View itemView) {
            super(itemView);
            task_service_name = (TextView) itemView.findViewById(R.id.card_view_task_service_name);
            task_service_flag = (Switch) itemView.findViewById(R.id.card_view_task_service_switch);

        }
    }
}
