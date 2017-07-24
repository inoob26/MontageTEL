package com.tel.inoob.montagtel.Controller;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import com.tel.inoob.montagtel.Model.ConsumableByTask;
import com.tel.inoob.montagtel.R;
import com.tel.inoob.montagtel.Tools.NewWebClient;

import java.util.List;

/**
 * @author inoob
 * @since 0.1
 */
public class RVConsumeAdapter extends RecyclerView.Adapter<RVConsumeAdapter.ConsumeHolder>{

    private List<ConsumableByTask> list;
    private int taskId;

    public RVConsumeAdapter(List<ConsumableByTask> list){
        this.list = list;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public ConsumeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_consume,parent,false);
        ConsumeHolder holder = new ConsumeHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ConsumeHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        int q = list.get(position).getQuantity();
        String palaceholder = ""+q;
        holder.addQuality.setText(palaceholder);

        holder.id = list.get(position).getID();
        holder.taskId = taskId;
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public class ConsumeHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private EditText addQuality;
        private ImageButton addConsumeBtn;
        private int id;
        private int taskId;

        public ConsumeHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.consume_name);
            addQuality = (EditText) itemView.findViewById(R.id.edit_quality);
            addConsumeBtn = (ImageButton) itemView.findViewById(R.id.add_cons_btn);

            addConsumeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*
                    {"model":{"TaskId":26647,"Consumables":[{"ID":3,"Quantity":10}]}} */
                    StringBuilder builder = new StringBuilder();

                    builder.append("{ \"model\": { \"TaskId\": " + taskId
                            + ", \"Consumables\": [ { \"ID\" : " + id +
                              ", \"Quantity\" : " + addQuality.getText() + " } ] }}");

                    Log.i("ConsumeHolder","json: " + builder.toString());

                    NewWebClient client = new NewWebClient();
                    client.addConsumablesToTask(builder.toString());
                }
            });
        }
    }
}
