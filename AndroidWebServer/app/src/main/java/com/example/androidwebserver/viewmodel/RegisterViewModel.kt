package com.example.androidwebserver.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidwebserver.model.Member
import com.google.gson.JsonObject
import tw.idv.william.androidwebserver.core.service.requestTask

class RegisterViewModel : ViewModel() {
    val member: MutableLiveData<Member> by lazy { MutableLiveData<Member>(Member()) }
    val cPassword: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val text: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    fun register(){
        member.value?.run {
            if (username.length < 5 || username.length > 50){
                text.value = "帳號長度須為5~50"
                return
            }
            if (password.length < 6 || password.length > 12){
                text.value = "密碼長度須為6~12"
                return
            }
            if (nickname.isEmpty() || nickname.length > 20){
                text.value = "名稱長度須為1~20"
                return
            }

            if (cPassword.value != password){
                text.value = "密碼不一致"
                return
            }

            val url = "http://10.0.2.2:8080/javaweb-exercise-09/member"
            val respBody = requestTask<JsonObject>(url, "POST", member.value)
            text.value = respBody?.get("message")?.asString
            Log.d("test", respBody.toString())
        }

    }

}