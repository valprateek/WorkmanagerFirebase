package com.prateekcode.workmanagerfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        periodicAppWorkManager()
    }

    fun periodicAppWorkManager() {
        val appWorkRequest =
            PeriodicWorkRequest.Builder(
                WorkManagerImpl::class.java,
                15,
                TimeUnit.MINUTES
            )
                .addTag("WorkManagerFirebase")
                .build()

        val workManager = WorkManager.getInstance(applicationContext)
        workManager.enqueue(appWorkRequest)
    }
}