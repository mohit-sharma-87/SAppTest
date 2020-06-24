package com.codelife.sapptest.repo

import com.codelife.sapptest.models.MakeInfo
import com.codelife.sapptest.repo.database.CarInfoDao
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.*

class CarRepoTest {

    @Mock
    private lateinit var networkDao: EndPoints

    @Mock
    private lateinit var dbDao: CarInfoDao

    private val carRepo by lazy {
        CarRepo(networkDao, dbDao)
    }

    private val mockNetworkResponse = listOf(
        MakeInfo(true, "1", "1", "", Date())
    )

    private val mockDBkResponse = listOf(MakeInfo(true, "2", "2", "", Date()))

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getMakes_whenNoDB_getDataFromNetwork() {

        //Given
        Mockito.`when`(networkDao.getMakes()).thenReturn(Single.just(mockNetworkResponse))
        Mockito.`when`(dbDao.getMakes()).thenReturn(Single.just(emptyList()))

        //When
        val actual = carRepo.getMakes().test()
        actual.awaitTerminalEvent()

        // assert

        actual
            .assertNoErrors()
            .assertValue(mockNetworkResponse)
    }

    @Test
    fun getMakes_whenDBAndNetwork_getDataFromDB() {

        //Given
        Mockito.`when`(networkDao.getMakes()).thenReturn(Single.just(mockNetworkResponse))
        Mockito.`when`(dbDao.getMakes()).thenReturn(Single.just(mockDBkResponse))

        //When
        val actual = carRepo.getMakes().test()
        actual.awaitTerminalEvent()

        // assert
        actual
            .assertNoErrors()
            .assertValue(mockDBkResponse)
    }

    @Test
    fun getMake_whenNetworkErrorAndDBisReady_getDataFromDB() {
        //Given
        Mockito.`when`(networkDao.getMakes()).thenReturn(Single.error(Exception()))
        Mockito.`when`(dbDao.getMakes()).thenReturn(Single.just(mockDBkResponse))

        //When
        val actual = carRepo.getMakes().test()
        actual.awaitTerminalEvent()

        // assert
        actual
            .assertNoErrors()
            .assertValue(mockDBkResponse)
    }

    @Test
    fun getMake_whenDBError_getDataFromNetwork() {
        //Given
        Mockito.`when`(networkDao.getMakes()).thenReturn(Single.just(mockNetworkResponse))
        Mockito.`when`(dbDao.getMakes()).thenReturn(Single.error(Exception()))

        //When
        val actual = carRepo.getMakes().test()
        actual.awaitTerminalEvent()

        // assert
        actual.assertValue(mockNetworkResponse)
    }
}
