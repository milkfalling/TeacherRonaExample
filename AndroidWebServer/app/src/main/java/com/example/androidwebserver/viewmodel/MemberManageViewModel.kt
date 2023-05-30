package com.example.androidwebserver.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidwebserver.model.Member
import com.google.gson.reflect.TypeToken
import tw.idv.william.androidwebserver.core.service.requestTask

class MemberManageViewModel : ViewModel() {
    val member: MutableLiveData<Member> by lazy { MutableLiveData<Member>() }
    val list:  MutableLiveData<List<Member>> by lazy {MutableLiveData<List<Member>>() }
    val url = "http://10.0.2.2:8080/javaweb-exercise-09/member"

    fun init() {
        val type = object : TypeToken<List<Member>>() {}.type
        list.value = requestTask<List<Member>>(url, respBodyType = type)
    }
}