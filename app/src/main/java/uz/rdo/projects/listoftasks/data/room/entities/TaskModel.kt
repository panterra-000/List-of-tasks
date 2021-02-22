package uz.rdo.projects.listoftasks.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

/**
 *  Created by Davronbek Raximjanov 17-Feb-2021
 **/

@Entity
data class TaskModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String,
    var desc: String,
    var completedPercent: Float,
    var status: String,
    var date: Long,
    var deadline: Long

) : Serializable