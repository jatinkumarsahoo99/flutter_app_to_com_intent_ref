package com.example.testapptoapp

import io.flutter.embedding.android.FlutterActivity

import android.content.Intent
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import android.os.Bundle

class MainActivity: FlutterActivity() {
    /* private val CHANNEL = "com.example.untitled/hello"
     private val METHOD_NAME = "hello";

     private lateinit var channel: MethodChannel

     override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
         super.configureFlutterEngine(flutterEngine)

         channel = MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL)
         channel.invokeMethod(METHOD_NAME, null, object: MethodChannel.Result{
             override fun success(result: Any?) {
                 Log.i("Android", "result: $result")
             }

             override fun error(errorCode: String?, errorMessage: String?, errorDetails: Any?) {
                 Log.i("Android", "error: $errorCode, $errorMessage, $errorDetails")
             }

             override fun notImplemented() {
                 Log.i("Android", "notImplemented")
             }
         })
     }*/

    private var sharedData: String = "kumar"

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)
        handleIntent()
    }

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger,
            "com.example.untitled").setMethodCallHandler { call, result ->
            if (call.method == "getSharedText") {
                handleIntent()
                result.success(sharedData)
                sharedData = ""
            }
        }
    }


    private fun handleIntent() {
        // intent is a property of this activity
        // Only process the intent if it's a send intent and it's of type 'text'
        if (intent?.action == Intent.ACTION_SEND) {
            if (intent.type == "text/plain") {
                intent.getStringExtra("Gender")?.let { intentData ->
                    sharedData = intentData
                    println(">>>>>>>jks"+sharedData)
                }
            }
        }
    }
}
