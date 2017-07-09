package com.tel.inoob.montagtel.Activities

import android.app.Dialog
import android.support.v4.app.FragmentManager
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.tel.inoob.montagtel.Controller.RVTaskSeviceListAdapter
import com.tel.inoob.montagtel.Controller.TicketController
import com.tel.inoob.montagtel.R
import com.tel.inoob.montagtel.Tools.Deserialize

class DetailTicketActivity : AppCompatActivity() {

    private var controller: TicketController? = null
    private var clientFio: TextView? = null
    private var clientPhone: TextView? = null
    private var taskAddress: TextView? = null
    private var task_id: TextView? = null
    private var client_id: TextView? = null
    private var start_time: TextView? = null
    private var recyclerView_task_service_list: RecyclerView? = null
    private var user_id: Int = 0
    private var task_detail_btn_add_task_service: Button? = null
    private var task_detail_lbl_for_device_sum: TextView? = null
    private var task_detail_lbl_pay_total_sum: TextView? = null

    private fun onLoad() {
        task_id = findViewById(R.id.task_detail_id) as TextView
        client_id = findViewById(R.id.task_detail_client_id) as TextView
        clientFio = findViewById(R.id.ticket_detail_client_fio) as TextView
        clientPhone = findViewById(R.id.ticket_detail_client_phone) as TextView
        taskAddress = findViewById(R.id.ticket_detail_task_address) as TextView
        start_time = findViewById(R.id.task_detail_time) as TextView
        task_detail_btn_add_task_service = findViewById(R.id.task_detail_btn_add_task_service) as Button
        task_detail_lbl_for_device_sum = findViewById(R.id.task_detail_lbl_for_device_sum) as TextView
        task_detail_lbl_pay_total_sum = findViewById(R.id.task_detail_lbl_pay_total_sum) as TextView

        val extras = intent.extras

        clientFio!!.text = extras.get("clientFio") as String
        clientPhone!!.text = extras.get("clientPhone") as String
        taskAddress!!.text = extras.get("taskAddress") as String
        task_id!!.text = "№" + extras.get("task_id").toString()
        client_id!!.text = extras.get("client_id").toString()
        start_time!!.text = extras.get("task_detail_time").toString()
        user_id = extras.get("user_id") as Int

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

        task_detail_btn_add_task_service!!.setOnClickListener {

            val serviceAdvansDialog = ServiceAdvansDialog()
            val args  = Bundle()
            args.putInt("user_id",user_id)
            serviceAdvansDialog.isCancelable = true
            serviceAdvansDialog.arguments = args
            serviceAdvansDialog.show(supportFragmentManager,"tag")
        }
    }

    companion object {
        /**
         * Layout.
         */
        private val LAYOUT = R.layout.activity_detail_ticket
    }

    /**
     * {@code ServiceAdvansDialog} describe additional service object.
     * it adding dynamically into DetailTicketActivity.
     * it is service that can't be schedule before hand.
     *
     */
    class ServiceAdvansDialog : DialogFragment() {
        init {

        }

        override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            val view: View = inflater!!.inflate(R.layout.fragment_service_adavans,container,false)

            var recycle_view_service_advans = view!!.findViewById(R.id.recycle_view_service_advans) as RecyclerView
            val linearLayoutManager = LinearLayoutManager(view.getContext())
            recycle_view_service_advans!!.setLayoutManager(linearLayoutManager)

            val args: Bundle = arguments

            val deserialize = Deserialize()

            val advansAdapter = RVServiceAdvansAdapter(deserialize.deserializeServiceAdvans(args.getInt("user_id")))
            recycle_view_service_advans!!.setAdapter(advansAdapter)

            return view
        }
    }
}
