package com.example.saurabhpracticle.base

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

abstract class BaseActivity: AppCompatActivity() {

    open fun toastMsg(context: Context,msg:String,length:Int){
        if(!msg.isEmpty()){
            Toast.makeText(context,msg,length).show()
        }
    }
    open fun snackbarMsg(view: View,msg: String,length: Int){
        if(!msg.isEmpty()){
            Snackbar.make(view,msg,length).show()
        }
    }
}