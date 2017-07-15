package com.tel.inoob.montagtel.View;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.tel.inoob.montagtel.Model.ServiceAdvans;
import com.tel.inoob.montagtel.R;

import java.util.List;

/**
 * Describe ServiceAdvansAdapter for RecycleView.
 *
 * @author inoob
 * @since 0.1
 */
public class RVServiceAdvansAdapter extends RecyclerView.Adapter<RVServiceAdvansAdapter.ServiceAdvansViewHolder> {

    private List<ServiceAdvans> list;

    public RVServiceAdvansAdapter(List<ServiceAdvans> list){
        this.list = list;
    }

    @Override
    public RVServiceAdvansAdapter.ServiceAdvansViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_service_advans,parent,false);
        ServiceAdvansViewHolder serviceAdvansViewHolder = new ServiceAdvansViewHolder(view);

        return serviceAdvansViewHolder;
    }

    @Override
    public void onBindViewHolder(RVServiceAdvansAdapter.ServiceAdvansViewHolder holder, int position) {
        holder.recycle_view_service_advans_name.setText(list.get(position).getServiceName());

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

    public static class ServiceAdvansViewHolder extends RecyclerView.ViewHolder{
        private TextView recycle_view_service_advans_name;

        public ServiceAdvansViewHolder(View itemView){
            super(itemView);
            recycle_view_service_advans_name = (TextView) itemView.findViewById(R.id.card_view_service_advans_name);
        }
    }
}
