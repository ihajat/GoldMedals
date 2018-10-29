package com.example.hajati01.goldmedals

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import android.arch.persistence.room.Room
import android.support.test.runner.AndroidJUnit4
import android.support.test.InstrumentationRegistry
import com.example.hajati01.goldmedals.model.CountryDao
import com.example.hajati01.goldmedals.model.CountryDb
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import java.util.*

@RunWith(AndroidJUnit4::class)
class CountryDaoTest {

    @Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private var database: CountryDb? = null
    private var dao: CountryDao? = null

    @Mock
    private val observer: Observer<List<Country>>? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        val context = InstrumentationRegistry.getTargetContext()
        database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), CountryDb::class.java!!)
                .allowMainThreadQueries().build()
        dao = database!!.daoCountry()
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        database!!.close()
    }

    /*

    Test to check that the insert country functionality works

    */
    @Test
    @Throws(Exception::class)
    fun insert() {

        // given
        val country = Country()
        country.name = "UK"
        country.golds = 3

        observer?.let { dao!!.getAllCountries().observeForever(it) }

        // when
        dao!!.insertCountry(country)

        // then
        verify(observer)!!.onChanged(Collections.singletonList(country))
    }

    /*

    TODO - Test to check that the update country functionality works

    */
    @Test
    @Throws(Exception::class)
    fun update() {

        // given
        val country = Country()
        country.name = "UK"
        country.golds = 3

        observer?.let { dao!!.getAllCountries().observeForever(it) }

        // when
        dao!!.updateCountry(country)

        // then
        verify(observer)!!.onChanged(Collections.singletonList(country))
    }

    /*

       TODO -  Test to check that the delete country functionality works

     */
    @Test
    @Throws(Exception::class)
    fun delete() {

        // given
        val country = Country()
        country.name = "UK"
        country.golds = 3

        observer?.let { dao!!.getAllCountries().observeForever(it) }

        // when
        dao!!.deleteCountry(country)

        // then
        verify(observer)!!.onChanged(Collections.singletonList(country))
    }
}
