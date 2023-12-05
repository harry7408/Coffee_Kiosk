package com.choi.coffee_kiosks.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.choi.coffee_kiosks.databinding.ItemMissionBinding
import com.choi.coffee_kiosks.data.Document
import com.choi.coffee_kiosks.util.common.setOnAvoidDuplicateClickWithFlow

class MissionAdapter(
    private val missions: List<Document>,
    private val onClick: (Document) -> Unit,
    private val missionStart : (Document)->Unit
) : RecyclerView.Adapter<MissionAdapter.MissionViewHolder>() {

    inner class MissionViewHolder(private val binding: ItemMissionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(mission: Document) {
            with(binding) {
                missionNameTextView.text = mission.fields.name.stringValue.toString()

                checkMissionButton.setOnAvoidDuplicateClickWithFlow {
                    onClick(mission)
                }

                startMissionButton.setOnAvoidDuplicateClickWithFlow {
                    missionStart(mission)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MissionAdapter.MissionViewHolder {
        return MissionViewHolder(
            ItemMissionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MissionAdapter.MissionViewHolder, position: Int) {
        holder.bind(missions[position])
    }

    override fun getItemCount() = missions.size
}