package com.henallux.bellpou.view.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.henallux.bellpou.R
import com.henallux.bellpou.model.Reward

class RewardsAdapter(private var rewards: List<Reward>): RecyclerView.Adapter<RewardsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RewardsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_reward, parent, false)

        return RewardsViewHolder(view)
    }

    override fun onBindViewHolder(holder: RewardsViewHolder, position: Int) {
        val reward = rewards[position]

        holder.brandNameView.text = reward.nameEN
        holder.rewardDescriptionView.text = reward.descriptionEN
        holder.costView.text = reward.throinsCost.toString()
    }

    override fun getItemCount(): Int {
        return rewards.size
    }

    fun setRewards(rewards: List<Reward>) {
        this.rewards = rewards
    }
}