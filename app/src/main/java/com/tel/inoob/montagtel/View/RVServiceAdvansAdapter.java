package com.tel.inoob.montagtel.View;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.tel.inoob.montagtel.Model.ServiceAdvans;
import com.tel.inoob.montagtel.R;
import com.tel.inoob.montagtel.Tools.NewWebClient;

import java.util.List;

/**
 * Describe ServiceAdvansAdapter for RecycleView.
 *
 * @author inoob
 * @since 0.1
 */
public class RVServiceAdvansAdapter extends RecyclerView.Adapter<RVServiceAdvansAdapter.ServiceAdvansViewHolder> {

    private List<ServiceAdvans> list;
    private int taskId;
    //dialog need for close ServiceAdvansDialog
    private Dialog dialog;
    private ServiceAdvanceClickForUpdateRecycleView serviceAdvanceClickForUpdateRecycleView;

    public RVServiceAdvansAdapter(List<ServiceAdvans> list, Dialog dialog, ServiceAdvanceClickForUpdateRecycleView serviceAdvanceClickForUpdateRecycleView){
        this.list = list;
        this.dialog = dialog;
        this.serviceAdvanceClickForUpdateRecycleView = serviceAdvanceClickForUpdateRecycleView;
    }

    @Override
    public RVServiceAdvansAdapter.ServiceAdvansViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_service_advans,parent,false);
        //ServiceAdvansViewHolder serviceAdvansViewHolder = new ServiceAdvansViewHolder(view);

        return new ServiceAdvansViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RVServiceAdvansAdapter.ServiceAdvansViewHolder holder, int position) {
        holder.recycle_view_service_advans_name.setText(list.get(position).getServiceName());
        final String placeHolderPrice = Double.toString(list.get(position).getPrice()) + " руб";
        holder.recycle_view_service_advans_price.setText(placeHolderPrice);

        holder.quantity = list.get(position).getQuantity();
        holder.tarifId = list.get(position).getTarifId();

        holder.taskId = taskId;
        holder.serviceTemplateId = list.get(position).getId();

        holder.dialog = dialog;
        holder.serviceAdvanceClickForUpdateRecycleView = this.serviceAdvanceClickForUpdateRecycleView;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * В случае, если LayoutManager добавляет элемент.
     * LayoutManager сообщает RecyclerView о том, что создает элемент: addView.
     * RecyclerView посылает запрос Adapter’у: onViewAttachedToWindow.
     * @param holder holder.
     */
    @Override
    public void onViewAttachedToWindow(ServiceAdvansViewHolder holder) {
        super.onViewAttachedToWindow(holder);

    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Holder class sends information about only one additional service
     */
    public static class ServiceAdvansViewHolder extends RecyclerView.ViewHolder{
        private TextView recycle_view_service_advans_name;
        private TextView recycle_view_service_advans_price;
        private int taskId;
        /**
         * field serviceTemplateId it is taskId.
         * serviceTemplateId uses for correct add into task.
         */
        private int serviceTemplateId;
        private int quantity;
        private int tarifId;
        private Dialog dialog;
        private ServiceAdvanceClickForUpdateRecycleView serviceAdvanceClickForUpdateRecycleView;

        public ServiceAdvansViewHolder(final View itemView){
            super(itemView);
            recycle_view_service_advans_name = (TextView) itemView.findViewById(R.id.card_view_service_advans_name);
            recycle_view_service_advans_price = (TextView) itemView.findViewById(R.id.card_view_service_advans_price);

            recycle_view_service_advans_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //serialize data for add service to task
                    StringBuilder  stringBuilder = new StringBuilder();
                    stringBuilder.append("{ \"model\":" +
                            "{\"TaskId\": " + taskId + "," +
                            "\"Services\": [ " +
                                                "{\"ServiceTemplateId\":" + serviceTemplateId + "," +
                                                "\"TarifId\":" + tarifId +"," +
                                                "\"Quantity\": " + quantity+ "} ] }}");
                    //Log.i("RVServiceAdvansAdapter","json: " + stringBuilder.toString());

                    //send json to server
                    NewWebClient client = new NewWebClient();
                    client.addServiceToTask(stringBuilder.toString());


                    //close ServiceAdvansDialog
                    dialog.dismiss();

                    //rerender recycle view for detailTicketActivity
                    serviceAdvanceClickForUpdateRecycleView.reRenderRecycleView();
                }
            });
        }
    }
}
