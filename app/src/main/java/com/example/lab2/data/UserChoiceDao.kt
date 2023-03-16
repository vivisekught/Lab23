package com.example.lab2.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lab2.domain.UserChoice

@Dao
interface UserChoiceDao {

    @Query("SELECT * FROM user_choices")
    fun getUserChoicesList(): LiveData<List<UserChoiceDbModel>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addUserChoice(userChoiceDbModel: UserChoiceDbModel)

}