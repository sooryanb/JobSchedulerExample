package com.example.jobschedulerexample

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {

    val TAG = "ExampleJobService"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun scheduleJob(v: View){
        val componentName = ComponentName(this, ExampleJobService::class.java)
        val info = JobInfo.Builder(123, componentName)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setPersisted(true)
                .setPeriodic(15 * 60 * 1000)
                .build()

        val scheduler: JobScheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
        val resultCode = scheduler.schedule(info)
        if(resultCode == JobScheduler.RESULT_SUCCESS){
            Log.d(TAG, "Job Scheduled")
        }else{
            Log.d(TAG, "Job scheduling failed")
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun cancelJob(v: View){
        val scheduler: JobScheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
        scheduler.cancel(123)
        Log.d(TAG, "Canclled")
    }

}