package com.example.androidwebserver.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.androidwebserver.viewmodel.LoginViewModel
import com.example.androidwebserver.R
import com.example.androidwebserver.databinding.FragmentLoginBinding
import com.example.androidwebserver.model.Member
import tw.idv.william.androidwebserver.core.service.requestTask

class LoginFragment : Fragment() {
    private lateinit var binding:FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        val viewModel: LoginViewModel by viewModels()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            btnRegister.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_loginFragment_to_registerFragment)
            }

            btnLogin.setOnClickListener {
                viewModel?.member?.value?.run {
                    if (username.isEmpty() || password.isEmpty()){
                        viewModel?.text?.value = "不可為空"
                        return@run
                    }

                    val url = "http://10.0.2.2:8080/javaweb-exercise-09/member"
                    val respBody = requestTask<Member>("$url/$username/$password")
                    viewModel?.text?.value = respBody?.nickname ?: "使用者名稱或密碼錯誤"
                    if (respBody?.nickname != null){
                        val roleId = respBody.roleId
                        val target =
                            if (roleId == 1)
                                R.id.memberManageFragment
                            else
                                R.id.editFragment
                        Navigation.findNavController(it)
                            .navigate(target)
                    }

                }
            }
        }
    }



}