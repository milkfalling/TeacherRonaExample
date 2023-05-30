package com.example.androidwebserver.controller

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.example.androidwebserver.viewmodel.EditViewModel
import com.example.androidwebserver.R
import com.example.androidwebserver.databinding.FragmentEditBinding

class EditFragment : Fragment() {

   private  lateinit var binding: FragmentEditBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding = FragmentEditBinding.inflate(inflater,container,false)
        val viewModel: EditViewModel by viewModels()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.viewModel?.init()
        with(binding){
            ivAvatar.setOnClickListener {
                val intent = Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                )
                pickPictureLauncher.launch(intent)
            }
        }
    }

    private var pickPictureLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let { uri -> binding.ivAvatar.setImageURI(uri) }
            }
        }

}