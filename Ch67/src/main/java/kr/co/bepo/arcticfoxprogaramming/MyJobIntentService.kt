package kr.co.bepo.arcticfoxprogaramming

import android.content.Intent
import android.util.Log
import androidx.core.app.JobIntentService

class MyJobIntentService: JobIntentService() {

    private val TAG = "ServiceExample"

    override fun onHandleWork(intent: Intent) {
        Log.i(TAG, "Job Service started")

        var i: Int = 0

        while (i <= 3) {
            try {
                Thread.sleep(10_000)
                i++
            } catch (e: Exception) {
                e.printStackTrace()
            }

            Log.i(TAG, "Service running")
        }
    }
}