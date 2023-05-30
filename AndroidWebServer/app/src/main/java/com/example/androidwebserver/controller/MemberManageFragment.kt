package com.example.androidwebserver.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidwebserver.databinding.FragmentMemberManageBinding
import com.example.androidwebserver.databinding.ItemviewMemberManagerBinding
import com.example.androidwebserver.model.Member
import com.example.androidwebserver.viewmodel.MemberManageViewModel

class MemberManageFragment : Fragment() {
    private lateinit var binding: FragmentMemberManageBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: MemberManageViewModel by viewModels()
        binding = FragmentMemberManageBinding.inflate(inflater,container,false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.rvMemberList.layoutManager = LinearLayoutManager(requireContext())
        // recycleView有item列的地方要做排版
        binding.viewModel!!.list.observe(viewLifecycleOwner){
            binding.rvMemberList.adapter = MemberAdapter(it)
        }
        binding.viewModel?.init()
    }

}

class MemberAdapter(var list: List<Member>): RecyclerView.Adapter<MemberAdapter.ViewHolder>(){
    class ViewHolder(val binding: ItemviewMemberManagerBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // 要binding layout
        val binding = ItemviewMemberManagerBinding
            .inflate(LayoutInflater.from(parent.context),parent,false)
        // 要binding viewModel
        binding.viewModel = MemberManageViewModel()
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val member = list[position]
        holder.binding.viewModel!!.member.value = member
    }
}