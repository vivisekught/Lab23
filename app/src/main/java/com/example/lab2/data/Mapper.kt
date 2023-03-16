package com.example.lab2.data

import android.util.Log
import com.example.lab2.domain.UserChoice

class Mapper {

    fun mapEntityToDbModel(userChoice: UserChoice) = UserChoiceDbModel(
        id = userChoice.id,
        flags = userChoice.flags,
        figure = userChoice.figures
    )

    fun mapDbModelToEntity(userChoiceDbModel: UserChoiceDbModel) = UserChoice(
        id = userChoiceDbModel.id,
        flags = userChoiceDbModel.flags,
        figures = userChoiceDbModel.figure
    )

    fun mapListDbModelToListEntity(list: List<UserChoiceDbModel>) = list.map {
        mapDbModelToEntity(it)
    }
}