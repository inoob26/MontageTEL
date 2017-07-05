package com.tel.inoob.montagtel.Activities

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import com.tel.inoob.montagtel.Controller.RVTaskSeviceListAdapter
import com.tel.inoob.montagtel.Controller.TicketController
import com.tel.inoob.montagtel.R

class DetailTicketActivity : AppCompatActivity() {


    private var controller: TicketController? = null
    private var clientFio: TextView? = null
    private var clientPhone: TextView? = null
    private var taskAddress: TextView? = null
    private var task_id: TextView? = null
    private var client_id: TextView? = null
    private var start_time: TextView? = null
    private var recyclerView_task_service_list: RecyclerView? = null


    private var task_detail_lbl_for_device_sum: TextView? = null
    private var task_detail_lbl_pay_total_sum: TextView? = null

    private fun onLoad() {
        task_id = findViewById(R.id.task_detail_id) as TextView
        client_id = findViewById(R.id.task_detail_client_id) as TextView
        clientFio = findViewById(R.id.ticket_detail_client_fio) as TextView
        clientPhone = findViewById(R.id.ticket_detail_client_phone) as TextView
        taskAddress = findViewById(R.id.ticket_detail_task_address) as TextView
        start_time = findViewById(R.id.task_detail_time) as TextView
        task_detail_lbl_for_device_sum = findViewById(R.id.task_detail_lbl_for_device_sum) as TextView
        task_detail_lbl_pay_total_sum = findViewById(R.id.task_detail_lbl_pay_total_sum) as TextView

        val extras = intent.extras

        clientFio!!.text = extras.get("clientFio") as String
        clientPhone!!.text = extras.get("clientPhone") as String
        taskAddress!!.text = extras.get("taskAddress") as String
        task_id!!.text = "№" + extras.get("task_id").toString()
        client_id!!.text = extras.get("client_id").toString()
        start_time!!.text = extras.get("task_detail_time").toString()

        initializeRecycleView(extras.get("task_id") as Int)
    }

    private fun initializeRecycleView(task_id: Int) {
        //layout
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView_task_service_list = findViewById(R.id.recycle_view_task_service_list) as RecyclerView
        recyclerView_task_service_list!!.layoutManager = linearLayoutManager

        controller = TicketController()
        val list = controller!!.getListOfTaskService(task_id)

        var device_sum = 0.0
        var total_sum = 0.0

        for (task in list) {
            if (0 != task.scladId) {
                device_sum += task.price!!
                total_sum += task.price!!
            } else {
                total_sum += task.price!!
            }
        }

        task_detail_lbl_for_device_sum!!.text = "" + device_sum.toInt() + " руб"
        task_detail_lbl_pay_total_sum!!.text = "" + total_sum.toInt() + " руб"

        var adapter: RVTaskSeviceListAdapter = RVTaskSeviceListAdapter()
        adapter.setTaskServiceList(list)
        recyclerView_task_service_list!!.adapter = adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(LAYOUT)
        onLoad()
        initListeners()
    }


    private fun initListeners() {
        /**
         * dial when click on phone number.
         */
        clientPhone!!.setOnClickListener {
            val dialNumber = Intent("android.intent.action.DIAL", Uri.parse("tel:8" + clientPhone!!.text))
            startActivity(dialNumber)
        }
    }

    companion object {
        /**
         * Layout.
         */
        private val LAYOUT = R.layout.activity_detail_ticket
    }
}
