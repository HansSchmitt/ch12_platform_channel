package com.zafnib.ch12_platform_channel

import android.os.Bundle

import io.flutter.app.FlutterActivity
import io.flutter.plugins.GeneratedPluginRegistrant

//Custom imports
import io.flutter.plugin.common.MethodChannel
import android.os.Build

class MainActivity: FlutterActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    GeneratedPluginRegistrant.registerWith(this)

    val deviceInfoChannel = MethodChannel(flutterView, "platformchannel.zafnib.com/deviceinfo")
    deviceInfoChannel.setMethodCallHandler { methodCall, result ->
      if (methodCall.method == "getDeviceInfo") {
        val deviceInfo = getDeviceInfo()
        result.success(deviceInfo)
      } else {
        result.notImplemented()
      }
    }
  }

  private fun getDeviceInfo(): String {
    return ("\nDevice: " + Build.DEVICE
            + "\nManufacturer: " + Build.MANUFACTURER
            + "\nModel: " + Build.MODEL
            + "\nProduct: " + Build.PRODUCT
            + "\nVersion Release: " + Build.VERSION.RELEASE
            + "\nVersion SDK: " + Build.VERSION.SDK_INT
            + "\nFingerprint: " + Build.FINGERPRINT
            + "\nTime: " + Build.TIME
            + "\nUser: " + Build.USER)
  }

}
