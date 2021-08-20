package kr.co.bepo.arcticfoxprogaramming

import android.app.Service
import android.content.Intent
import android.os.*
import android.widget.Toast

class RemoteService : Service() {

    private val myMessage = Messenger(IncomingHandler())

    inner class IncomingHandler : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            val data = msg.data
            val dataString = data.getString("MyString")
            Toast.makeText(applicationContext, dataString, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBind(intent: Intent): IBinder {
        return myMessage.binder
    }
}