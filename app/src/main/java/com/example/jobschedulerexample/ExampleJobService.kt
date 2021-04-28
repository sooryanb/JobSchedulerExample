package com.example.jobschedulerexample

import android.app.job.JobParameters
import android.app.job.JobService
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class ExampleJobService: JobService() {

    val TAG = "ExampleJobService"
    private var jobCanelled = false

    override fun onStartJob(params: JobParameters?): Boolean {
        Log.d(TAG, "Job Started")
        doBackgroundWork(params)
        return true
    }

    private fun doBackgroundWork(params: JobParameters?){
        Thread{
           for (i in 1..10){
               Log.d(TAG, "run $i")
               if (jobCanelled) return@Thread
               Thread.sleep(1000)
           }
            Log.d(TAG, "Job finished")
            jobFinished(params, false)
        }.start()
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        Log.d(TAG, "Job cancelled before completeion")
        jobCanelled = true
        return true
    }
}