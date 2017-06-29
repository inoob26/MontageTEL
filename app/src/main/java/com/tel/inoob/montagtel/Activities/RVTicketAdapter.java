package com.tel.inoob.montagtel.Activities;

import android.content.Intent;
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
        holder.idTask.setText("№" + listOfTask.get(position).getId());

        holder.clientId.setText("ID " + listOfTask.get(position).getClientId());
        holder.taskStart.setText(listOfTask.get(position).getStartDateTime());

        holder.taskAddress.setText(listOfTask.get(position).getObjectName()
                + " кв." + listOfTask.get(position).getFlatNumber());
        holder.clientPhone.setText(listOfTask.get(position).getClientPhone());
        holder.clientFio.setText(listOfTask.get(position).getFirstName()
                + " " + listOfTask.get(position).getSecondName()
                + " " + listOfTask.get(position).getMiddleName());
        holder.serviceInfo.setText(listOfTask.get(position).getServiceInfo());

        holder.task_id = listOfTask.get(position).getId();
        holder.client_id = listOfTask.get(position).getClientId();

        //set color for task
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
        TextView idTask;
        TextView clientId;
        TextView clientFio;
        TextView clientPhone;
        TextView taskAddress;
        TextView serviceInfo;
        TextView taskStart;
        int task_id;
        int client_id;

        public TicketViewHolder(View itemView) {
            super(itemView);
            cardView =  (CardView) itemView.findViewById(R.id.cardViewTicket);
            idTask = (TextView) itemView.findViewById(R.id.task_id);
            clientId = (TextView) itemView.findViewById(R.id.client_id);
            clientFio = (TextView) itemView.findViewById(R.id.clientFIO);
            clientPhone = (TextView) itemView.findViewById(R.id.clientPhone);
            taskAddress = (TextView) itemView.findViewById(R.id.taskAddress);
            serviceInfo = (TextView) itemView.findViewById(R.id.serviceInfo);
            taskStart = (TextView) itemView.findViewById(R.id.task_start);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent detail_ticket = new Intent(v.getContext(),DetailTicketActivity.class);

                    detail_ticket.putExtra("clientFio",clientFio.getText());
                    detail_ticket.putExtra("clientPhone",clientPhone.getText());
                    detail_ticket.putExtra("taskAddress",taskAddress.getText());
                    detail_ticket.putExtra("serviceInfo",serviceInfo.getText());
                    detail_ticket.putExtra("task_id",task_id);
                    detail_ticket.putExtra("client_id",client_id);
                    detail_ticket.putExtra("task_detail_time",taskStart.getText());

                    v.getContext().startActivity(detail_ticket);
                }
            });
        }
    }
}
