package com.henallux.bellpou.view.fragments

import android.os.Bundle
import android.provider.Contacts
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.henallux.bellpou.R
import com.henallux.bellpou.repository.RewardsRepository
import com.henallux.bellpou.view.recyclerview.RewardsAdapter
import com.henallux.bellpou.viewmodel.LoginViewModel
import com.henallux.bellpou.viewmodel.RewardViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class RewardsFragment : Fragment() {

    private val rewardsVM by activityViewModels<RewardViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_rewards, container, false)

        CoroutineScope(Dispatchers.IO).launch() {
            val rewardsRecycleView = view.findViewById<RecyclerView>(R.id.rewards_recycler_view)
            val rewards = rewardsVM.getRewards()
            val rewardsAdapter = RewardsAdapter(rewards)

            if (rewardsRecycleView != null) {
                withContext(Dispatchers.Main) {
                    rewardsRecycleView.layoutManager = LinearLayoutManager(activity)
                    rewardsRecycleView.adapter = rewardsAdapter
                }
            }
        }


        return view
    }

}