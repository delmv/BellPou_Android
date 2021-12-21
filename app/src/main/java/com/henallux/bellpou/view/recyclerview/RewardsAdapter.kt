package com.henallux.bellpou.view.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.ConfigurationCompat
import androidx.recyclerview.widget.RecyclerView
import com.henallux.bellpou.App
import com.henallux.bellpou.R
import com.henallux.bellpou.model.Reward

class RewardsAdapter(private var rewards: List<Reward>): RecyclerView.Adapter<RewardsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RewardsViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_reward, parent, false)

        return RewardsViewHolder(view)

    }

    override fun onBindViewHolder(holder: RewardsViewHolder, position: Int) {

        val reward = rewards[position]
        val appLanguage = App.applicationContext().resources.configuration.locales[0].language

        if (appLanguage == "fr")
            holder.brandNameView.text = reward.nameFR
        else
            holder.brandNameView.text = reward.nameEN

        holder.costView.text = reward.throinsCost.toString()

        holder.rewardId = reward.id

    }

    override fun getItemCount(): Int = rewards.size

    fun setRewards(rewards: List<Reward>) {
        this.rewards = rewards
    }

}