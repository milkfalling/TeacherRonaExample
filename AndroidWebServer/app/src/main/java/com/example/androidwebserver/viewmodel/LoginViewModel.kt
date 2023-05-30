package com.example.androidwebserver.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.androidwebserver.controller.LoginFragment
import com.example.androidwebserver.model.Member
import tw.idv.william.androidwebserver.core.service.requestTask

class LoginViewModel : ViewModel() {
    val member: MutableLiveData<Member> by lazy { MutableLiveData<Member>(Member()) }
    val text: MutableLiveData<String> by lazy { MutableLiveData<String>() }

//    fun login(){
//        member.value?.run {
//            if (username.isEmpty() || password.isEmpty()){
//                text.value = "不可為空"
//                return
//            }
//
//            val url = "http://10.0.2.2:8080/javaweb-exercise-09/member"
//            val respBody = requestTask<Member>("$url/$username/$password")
//            text.value = respBody?.nickname ?: "使用者名稱或密碼錯誤"
//        }
//    }
}