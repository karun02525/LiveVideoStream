package com.exa.livestream
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.UUID


class MainActivity : AppCompatActivity() {
    lateinit var nameInput:EditText
    lateinit var liveInput:EditText
    lateinit var goLiveBtn:Button
    var liveID=""
    var name=""
    var userId=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        liveInput=findViewById(R.id.id)
        nameInput=findViewById(R.id.name)
        goLiveBtn=findViewById(R.id.btnSend)

        liveInput.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
               liveID =  liveInput.text.toString()
                if(liveID.length==0){
                    goLiveBtn.setText("Star New Live")
                }else{
                    goLiveBtn.setText("Join a Live")
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })


        goLiveBtn.setOnClickListener {

            name = nameInput.text.toString()
            liveID = liveInput.text.toString()

            if(name.isEmpty()){
                Toast.makeText(this, "Name is required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            startMeeting()
        }

    }

    private fun startMeeting() {

        var isHost= true
        if(liveID.length==5){
            isHost=false
        }else {
            liveID = (20343..34334).random().toString()
        }

        userId = UUID.randomUUID().toString()


        startActivity(Intent(this@MainActivity,LiveActivity::class.java)
            .putExtra("user_id",userId)
            .putExtra("name",name)
            .putExtra("live_id",liveID)
            .putExtra("host",isHost)

        )
    }
}