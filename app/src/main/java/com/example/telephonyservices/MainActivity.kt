package com.example.telephonyservices

import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.TelephonyManager
import android.widget.TextView
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {
        private val REQUEST_CODE_PHONE_STATE = 101
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            val tvText : TextView = findViewById(R.id.tvText)
            val telephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as
                    TelephonyManager
            val networkOperatorName = telephonyManager.networkOperatorName
            val phoneType: Int = telephonyManager.getPhoneType()
            var strphoneType : String = ""
            val networkCountryISO: String = telephonyManager.getNetworkCountryIso()
            val SIMCountryISO: String = telephonyManager.getSimCountryIso()
            val softwareVersion: String? = telephonyManager.getDeviceSoftwareVersion()

            if (ActivityCompat.checkSelfPermission(this,
                    android.Manifest.permission.READ_PHONE_STATE) !=
                PackageManager.PERMISSION_GRANTED ) {
                ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.READ_PHONE_STATE), REQUEST_CODE_PHONE_STATE)
            }

            when (phoneType) {
                TelephonyManager.PHONE_TYPE_CDMA -> strphoneType = "CDMA"
                TelephonyManager.PHONE_TYPE_GSM -> strphoneType = "GSM"
                TelephonyManager.PHONE_TYPE_NONE -> strphoneType = "NONE"
            }
            tvText.setText("Network Operator: $networkOperatorName" +
                    "\nPhone Type: $strphoneType" +
                    "\nNetwork Country ISO: $networkCountryISO" +
                    "\nSIM Country ISO: $SIMCountryISO" +
                    "\nSoftware Version: $softwareVersion")
        }
}
