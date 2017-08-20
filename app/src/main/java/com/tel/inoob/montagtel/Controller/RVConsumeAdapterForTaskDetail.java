package com.tel.inoob.montagtel.Controller;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.tel.inoob.montagtel.Model.ConsumableByTask;
import com.tel.inoob.montagtel.R;
import com.tel.inoob.montagtel.View.ConsumableOnClickUpdateListener;

import java.util.List;

/**
 * @author inoob
 * @since 0.1
 */
public class RVConsumeAdapterForTaskDetail extends RecyclerView.Adapter<RVConsumeAdapterForTaskDetail.ConsumeHolderForTaskDetail>{
    private List<ConsumableByTask> list;
    private ConsumableOnClickUpdateListener listener;


    public RVConsumeAdapterForTaskDetail(List<ConsumableByTask> list){
        this.list = list;
    }

    @Override
    public ConsumeHolderForTaskDetail onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_cnsume_ticket_detail,parent,false);
        return new ConsumeHolderForTaskDetail(view);
    }

    @Override
    public void onBindViewHolder(ConsumeHolderForTaskDetail holder, int position) {
        holder.consume_name.setText(list.get(position).getName());
        String quantity = ""+list.get(position).getQuantity();

        holder.consume_count.setText(quantity);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setListener(ConsumableOnClickUpdateListener listener) {
        this.listener = listener;
    }

    public class ConsumeHolderForTaskDetail extends RecyclerView.ViewHolder{

        private TextView consume_name;
        private TextView consume_count;

        public ConsumeHolderForTaskDetail(View itemView) {
            super(itemView);
            consume_name = (TextView) itemView.findViewById(R.id.task_detail_consume_name_lbl);
            consume_count = (TextView) itemView.findViewById(R.id.task_detail_consume_count);
        }
    }

    public void updateList(List<ConsumableByTask> newList){
        final ConsumableDiffCallBack consumableDiffCallBack = new ConsumableDiffCallBack(this.list,newList);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(consumableDiffCallBack);

        this.list.clear();
        this.list.addAll(newList);

        diffResult.dispatchUpdatesTo(this);
    }
}
