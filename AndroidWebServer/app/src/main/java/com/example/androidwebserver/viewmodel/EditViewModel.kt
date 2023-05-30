package com.example.androidwebserver.viewmodel

import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.androidwebserver.R
import com.example.androidwebserver.model.Member
import com.google.gson.JsonObject
import tw.idv.william.androidwebserver.core.service.requestTask

class EditViewModel : ViewModel() {
    val url = "http://10.0.2.2:8080/javaweb-exercise-09/member"
    val member: MutableLiveData<Member> by lazy { MutableLiveData() } //不需要有預設值
    val cPassword: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val text: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    //先把登入後的資料載入
    fun init(){
        member.value = requestTask(url,"OPTIONS")
    }

    fun save(){
        member.value?.run {
            if (password.isNotEmpty() && password.length < 6 || password.length > 12){
                text.value = "密碼長度須為6~12"
                return
            }
            if (nickname.isEmpty() || password.length > 20){
                text.value = "名稱長度須為1~20"
                return
            }
            if (password.isNotEmpty() && cPassword.value != password){
                text.value = "密碼不一致"
                return
            }
        }

        val respBody = requestTask<JsonObject>(url, "PUT", member.value)
        respBody?.run {
            text.value = get("successful").asString
        }
    }

    fun logout(view: View) {
        AlertDialog.Builder(view.context)
            .setMessage("確定登出?")
            .setPositiveButton("是") { _,_->
                requestTask<JsonObject>(url,"DELETE")
                Navigation.findNavController(view).navigate(R.id.loginFragment)
            }
            .setNegativeButton("否", null)
            .setCancelable(false)
            .show()
    }
}

