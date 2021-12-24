package com.henallux.bellpou.view.recyclerview

import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.henallux.bellpou.App
import com.henallux.bellpou.R
import com.henallux.bellpou.model.BoughtReward
import com.henallux.bellpou.repository.RewardsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RewardsViewHolder(
    containerView: View,
): RecyclerView.ViewHolder(containerView) {

    val brandNameView: TextView by lazy { containerView.findViewById(R.id.box_brand) }
    val costView: TextView by lazy { containerView.findViewById(R.id.box_cost) }
    lateinit var rewardId: Integer


    init {

        containerView.setOnClickListener {

            CoroutineScope(Dispatchers.IO).launch {

                try {

                    val reward = RewardsRepository.buyReward(BoughtReward(rewardId))

                    val message = App.applicationContext().getString(R.string.received_reward) + reward.discountCode

                    withContext(Dispatchers.Main) {

                        val toast = Toast.makeText(App.applicationContext(), message, Toast.LENGTH_SHORT)
                        toast.show()

                    }

                } catch (e: Exception) {

                    withContext(Dispatchers.Main) {

                        val toast = Toast.makeText(App.applicationContext(), e.message, Toast.LENGTH_SHORT)
                        toast.show()

                    }

                }

            }

        }

    }

}