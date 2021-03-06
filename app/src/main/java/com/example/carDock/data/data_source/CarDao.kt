package com.example.carDock.data.data_source

import androidx.room.*
import com.example.carDock.domain.model.Car
import kotlinx.coroutines.flow.Flow


@Dao
interface CarDao {

    @Query(
        "SELECT * FROM car"
    )
    fun getCars(): Flow<List<Car>>

    @Query(
        "SELECT * FROM car WHERE id = :id"
    )
    suspend fun getCarById(id: Long): Car?

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = Car::class)
    suspend fun addCar(car: Car)

    @Delete(entity = Car::class)
    suspend fun delCar(car: Car)

    @Query(
        "SELECT * FROM car WHERE availability = :bool"
    )
    fun getSellingCars(bool: Boolean): Flow<List<Car>>


    @Query(
        "UPDATE car SET availability = 0 WHERE id = :id and availability = 1"
    )
    suspend fun makeCarSold(id : Long)


    @Query(
        "SELECT availability FROM car WHERE id = :id"
    )
    suspend fun isCarAvailable(id : Long) : Boolean

}