package com.exa.livestream

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.exa.livestream.Const.APP_ID
import com.exa.livestream.Const.APP_SIGN
import com.zegocloud.uikit.ZegoUIKit
import com.zegocloud.uikit.prebuilt.livestreaming.ZegoUIKitPrebuiltLiveStreamingConfig
import com.zegocloud.uikit.prebuilt.livestreaming.ZegoUIKitPrebuiltLiveStreamingFragment


class LiveActivity : AppCompatActivity() {

    var userId=""
    var name=""
    var liveID=""
    var isHost=false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live)

        userId = intent.getStringExtra("user_id").toString()
        name = intent.getStringExtra("name").toString()
        liveID  = intent.getStringExtra("live_id").toString()
        isHost  = intent.getBooleanExtra("host",false)

        val txtId=findViewById<TextView>(R.id.liveId)
        txtId.text = liveID


        addFragment()
    }

    private fun addFragment() {



          val config: ZegoUIKitPrebuiltLiveStreamingConfig = if (isHost) {
              ZegoUIKitPrebuiltLiveStreamingConfig.host()
          } else {
              ZegoUIKitPrebuiltLiveStreamingConfig.audience()
          }

          val fragment = ZegoUIKitPrebuiltLiveStreamingFragment.newInstance(
              APP_ID, APP_SIGN, userId, name, liveID, config
              )
          supportFragmentManager.beginTransaction()
              .replace(R.id.fragment_container, fragment)
              .commitNow()
    }
}