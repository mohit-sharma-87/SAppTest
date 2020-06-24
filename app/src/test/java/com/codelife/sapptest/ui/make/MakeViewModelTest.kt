package com.codelife.sapptest.ui.make

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.codelife.sapptest.R
import com.codelife.sapptest.models.MakeInfo
import com.codelife.sapptest.repo.ICarRepo
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.*

class MakeViewModelTest {

    @Mock
    lateinit var carRepo: ICarRepo

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)


        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }

        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setMainThreadSchedulerHandler { Schedulers.trampoline() }
    }


    @Test
    fun getMakeInfoOnSuccess() {

        // Given
        val mockResponse = listOf(
            MakeInfo(true, "1", "1", "", Date())
        )
        Mockito.`when`(carRepo.getMakes()).thenReturn(Single.just(mockResponse))
        val viewModel = MakeViewModel(carRepo)

        // assert
        Assert.assertEquals(viewModel.makes.value, mockResponse)
    }

    @Test
    fun getMakeInfoOnFailure() {

        // Given
        val mockResponse = listOf(
            MakeInfo(true, "1", "1", "", Date())
        )

        Mockito.`when`(carRepo.getMakes()).thenReturn(Single.error(Exception()))
        val viewModel = MakeViewModel(carRepo)

        // assert
        Assert.assertEquals(viewModel.errorMgs.value, R.string.error_mgs)
    }
}