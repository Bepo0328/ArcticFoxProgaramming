package kr.co.bepo.arcticfoxprogaramming

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    var myService: Messenger? = null
    var isBound = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(applicationContext, RemoteService::class.java)
        bindService(intent, myConnection, Context.BIND_AUTO_CREATE)
    }

    fun sendMessage(view: View) {
        if (!isBound) return

        val msg = Message.obtain()
        val bundle = Bundle()

        bundle.putString("MyString", "Message Received")

        msg.data = bundle

        try {
            myService?.send(msg)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private val myConnection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName?, service: IBinder?) {
            myService = Messenger(service)
            isBound = true
        }

        override fun onServiceDisconnected(className: ComponentName?) {
            myService = null
            isBound = false
        }
    }
}