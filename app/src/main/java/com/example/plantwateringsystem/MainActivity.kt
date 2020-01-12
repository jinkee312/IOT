package com.example.plantwateringsystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var database:DatabaseReference = FirebaseDatabase.getInstance().getReference("testIOT")
        var data:DatabaseReference = FirebaseDatabase.getInstance().getReference("Moisture level")
        var base:DatabaseReference = FirebaseDatabase.getInstance().getReference("Water level")
//
        btn_On.setOnClickListener {
            database.child("Switch").setValue(1)
        }
        btn_Off.setOnClickListener {
            database.child("Switch").setValue(0)
        }
        database.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(
                    this@MainActivity,
                    "Error Encounter Due to " + databaseError.message,
                    Toast.LENGTH_LONG
                ).show()/**/

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    moist.setText(dataSnapshot.child("Moisture level").getValue().toString())
                    water.setText(dataSnapshot.child("Water level").getValue().toString())
                }
                else {
                    Toast.makeText(this@MainActivity, "No data Found", Toast.LENGTH_LONG).show()
                }

            }

        })

    }
}

class DataModel(var moist: String, var water: String) {

    constructor():this("","")
}