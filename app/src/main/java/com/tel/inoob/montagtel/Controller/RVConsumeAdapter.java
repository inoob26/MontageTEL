package com.tel.inoob.montagtel.Controller;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import com.tel.inoob.montagtel.Model.ConsumableByTask;
import com.tel.inoob.montagtel.R;
import com.tel.inoob.montagtel.Tools.NewWebClient;
import com.tel.inoob.montagtel.View.ConsumableOnClickUpdateListener;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author inoob
 * @since 0.1
 */
public class RVConsumeAdapter extends RecyclerView.Adapter<RVConsumeAdapter.ConsumeHolder>{

    private List<ConsumableByTask> consumableByTaskList;
    private int taskId;
    private ConsumableOnClickUpdateListener closeListener;

    public RVConsumeAdapter(){

    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public void setConsumableByTaskList(final List<ConsumableByTask> consumableTaskList) {
        this.consumableByTaskList = consumableTaskList;

        /*
        Log.i("setConsumableByTaskList", "quantity: " + consumableTaskList.get(0).getQuantity());
        if (this.consumableByTaskList == null){
            Log.i("setConsumableByTaskList","if block");
            this.consumableByTaskList = consumableTaskList;
            notifyItemRangeChanged(0,consumableByTaskList.size());
        } else {
            Log.i("setConsumableByTaskList","else block");
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return consumableByTaskList.size();
                }

                @Override
                public int getNewListSize() {
                    return consumableTaskList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return consumableByTaskList.get(oldItemPosition).getQuantity()
                            == consumableTaskList.get(newItemPosition).getQuantity();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    return false;
                }
            });
            consumableByTaskList = consumableTaskList;
            Log.i("setConsumableByTaskList","else block consumableByTaskList quantity: "
                    + consumableByTaskList.get(0).getQuantity());
            diffResult.dispatchUpdatesTo(this);
        }*/

    }

    /**
     * setter for ConsumableOnClickListener.
     *
     * @param listener ConsumableOnClickListener.
     */
    public void setCloseListener(ConsumableOnClickUpdateListener listener) {
        this.closeListener = listener;
    }

    @Override
    public ConsumeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_consume,parent,false);
        ConsumeHolder holder = new ConsumeHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ConsumeHolder holder, int position) {
        holder.name.setText(consumableByTaskList.get(position).getName());
        int q = consumableByTaskList.get(position).getQuantity();
        String palaceholder = ""+q;
        holder.addQuality.setText(palaceholder);

        holder.id = consumableByTaskList.get(position).getID();
        holder.taskId = taskId;
    }

    @Override
    public int getItemCount() {
        return this.consumableByTaskList.size();
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

            String regex = "^[0-9]+$";
            final Pattern pattern = Pattern.compile(regex);


            /**
             * add/change data and send it to server.
             * after close ConsumablesByTaskActivity.
             */
            addConsumeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /**
                     * validate data.
                     * pattern ^[0-9]+$.
                     */
                    Matcher matcher = pattern.matcher(addQuality.getText());
                    if (!matcher.find()){
                        addQuality.setError("неверный формат ввода. Необходимо вводить только цифры от 1 и больше");
                    } else {
                        /*
                        {"model":{"TaskId":26647,"Consumables":[{"ID":3,"Quantity":10}]}} */

                        StringBuilder builder = new StringBuilder();

                        builder.append("{ \"model\": { \"TaskId\": " + taskId
                                + ", \"Consumables\": [ { \"ID\" : " + id +
                                  ", \"Quantity\" : " + addQuality.getText() + " } ] }}");

                        //Log.i("ConsumeHolder", "json: " + builder.toString());

                        NewWebClient client = new NewWebClient();
                        client.addConsumablesToTask(builder.toString());

                        closeListener.sendDataUpdateAndCloseFrame();
                        ConsumableByTaskController consumableByTaskController = ConsumableByTaskController.getINSTANCE();
                        ConsumableOnClickUpdateListener update = consumableByTaskController.getUpdateListener();
                        update.sendDataUpdateAndCloseFrame();
                    }
                }
            });
        }
    }


    /**
     * Rerender RecycleView.
     * @param list list
     */
    public void updateList(List<ConsumableByTask> list){
        final ConsumableDiffCallBack consumableDiffCallBack = new ConsumableDiffCallBack(this.consumableByTaskList,list);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(consumableDiffCallBack);

        this.consumableByTaskList.clear();
        this.consumableByTaskList.addAll(list);

        diffResult.dispatchUpdatesTo(this);
    }
}
