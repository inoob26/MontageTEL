package com.tel.inoob.montagtel.Activities;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.tel.inoob.montagtel.Model.Task;
import com.tel.inoob.montagtel.R;

import java.util.List;
import java.util.Objects;

/**
 * @author inoob
 * @since 0.1
 */
public class RVTicketAdapter extends RecyclerView.Adapter<RVTicketAdapter.TicketViewHolder>{
    private List<Task> listOfTask;
    public RVTicketAdapter(List<Task> listOfTask) {
        this.listOfTask = listOfTask;
    }

    @Override
    public TicketViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ticket,viewGroup,false);
        TicketViewHolder ticketViewHolder = new TicketViewHolder(view);

        return ticketViewHolder;
    }

    //@RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onBindViewHolder(TicketViewHolder holder, int position) {
        holder.idTask_clientId.setText(listOfTask.get(position).getId()
                + " "+ listOfTask.get(position).getClientId());
        holder.taskAddress.setText(listOfTask.get(position).getObjectName()
                + " кв." + listOfTask.get(position).getFlatNumber());
        holder.clientPhone.setText(listOfTask.get(position).getClientPhone());
        holder.clientFio.setText(listOfTask.get(position).getFirstName()
                + " " + listOfTask.get(position).getSecondName()
                + " " + listOfTask.get(position).getMiddleName());
        holder.serviceInfo.setText(
                Objects.requireNonNull(listOfTask.get(position).getServiceInfo()
                        ,"service info need not null"));

        if(listOfTask.get(position).getStatus() == 1){
            holder.cardView.setCardBackgroundColor(ContextCompat.getColor(holder.cardView.getContext(),R.color.status_color_red));
        } else if(listOfTask.get(position).getStatus() == 2){
            holder.cardView.setCardBackgroundColor(ContextCompat.getColor(holder.cardView.getContext(),R.color.status_color_green));
        } else if(listOfTask.get(position).getStatus() == 11){
            holder.cardView.setCardBackgroundColor(ContextCompat.getColor(holder.cardView.getContext(),R.color.status_color_orange));
        } else if(listOfTask.get(position).getStatus() == 101){
            holder.cardView.setCardBackgroundColor(ContextCompat.getColor(holder.cardView.getContext(),R.color.status_color_red));
        } else {
            holder.cardView.setCardBackgroundColor(ContextCompat.getColor(holder.cardView.getContext(),R.color.white));
        }

    }

    @Override
    public int getItemCount() {
        return listOfTask.size();
    }

    public static class TicketViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView idTask_clientId;
        TextView clientFio;
        TextView clientPhone;
        TextView taskAddress;
        TextView serviceInfo;

        public TicketViewHolder(View itemView) {
            super(itemView);
            cardView =  (CardView) itemView.findViewById(R.id.cardViewTicket);
            idTask_clientId = (TextView) itemView.findViewById(R.id.task_id);
            clientFio = (TextView) itemView.findViewById(R.id.clientFIO);
            clientPhone = (TextView) itemView.findViewById(R.id.clientPhone);
            taskAddress = (TextView) itemView.findViewById(R.id.taskAddress);
            serviceInfo = (TextView) itemView.findViewById(R.id.serviceInfo);
        }
    }
}
