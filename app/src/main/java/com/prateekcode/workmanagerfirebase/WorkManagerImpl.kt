package com.prateekcode.workmanagerfirebase

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

class WorkManagerImpl(private val context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {

    override fun doWork(): Result {
        return try {
            val hashMapData : HashMap<String, String> = HashMap()
            hashMapData["Value"] = "Success"
            Firebase.firestore
                .collection("ParentCollection")
                .document("rahulsheokandg@gmail.com")
                .collection("Child")
                .document("workmanager@poc")
                .collection("Data")
                .document(dateTimeFormatter(System.currentTimeMillis()))
                .set(hashMapData)
                .addOnCompleteListener {
                    Log.d("WorkManagerFirebase", "doWork: Work done success")
                }.addOnFailureListener {
                    Log.d("WorkManagerFirebase", "doWork: Work done failed")
                }
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }
}

fun dateTimeFormatter(timeInMillSecond: Long): String {
    val simpleDateFormatter = SimpleDateFormat("dd MMM yyyy hh:mm:ss")
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = timeInMillSecond
    return simpleDateFormatter.format(calendar.time)
}