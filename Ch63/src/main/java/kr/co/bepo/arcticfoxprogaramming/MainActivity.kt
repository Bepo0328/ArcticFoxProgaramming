package kr.co.bepo.arcticfoxprogaramming

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kr.co.bepo.arcticfoxprogaramming.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    fun buttonClick(view: View) {
        val task = MyTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        val cpu_cores = Runtime.getRuntime().availableProcessors()
        Log.d("MainActivity", "cpu cores: $cpu_cores")
    }

    private inner class MyTask : AsyncTask<String, Int, String>() {
        override fun onPreExecute() {
        }

        override fun doInBackground(vararg p0: String?): String {
            var i = 0
            while (i <= 20) {
                try {
                    Thread.sleep(1000)
                    publishProgress(i)
                    i++
                } catch (e: Exception) {
                    return(e.localizedMessage)
                }
            }
            return "Button Pressed"
        }

        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
            val counter = values[0]
            binding.myTextView.text = "Counter = $counter"
        }

        override fun onPostExecute(result: String?) {
            binding.myTextView.text = result
        }
    }
}