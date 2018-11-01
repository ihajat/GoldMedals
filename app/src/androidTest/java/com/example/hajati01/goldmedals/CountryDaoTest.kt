package com.example.hajati01.goldmedals

import android.arch.lifecycle.Observer
import android.support.test.runner.AndroidJUnit4
import android.support.test.InstrumentationRegistry
import com.example.hajati01.goldmedals.model.CountryDao
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


import android.arch.lifecycle.LiveData
import com.example.hajati01.goldmedals.model.CountryDb

import org.junit.Assert
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit


@RunWith(AndroidJUnit4::class)
class CountryDaoTest { 

    private var countryDao: CountryDao? = null

    @Before
    fun setup() {
        CountryDb.TEST_MODE = true
        countryDao = CountryDb.getDataBase(InstrumentationRegistry.getTargetContext()).daoCountry()
    }

    @After
    fun tearDown() {

    }

    /*
    Test 1:  Insert functionality
      */
    @Test
    fun should_Insert_Country_Item() {

        // Given that we have a country in the data source
        val country = Country(1,"UK",2)
        countryDao?.let{
            it.insertCountry(country)
        }

        //When we retrieve a country from the data source using the same Id
        val countryTest = countryDao?.getCountryById(country.id)

        //Then they should be the same
        Assert.assertEquals(countryTest?.name, country.name)
    }

    /*
    Test Update functionality
      */
    @Test
    fun should_UpdateCountry_Item() {

        // Given that we have a country in the data source
        val country = Country(2,"France",0)
        countryDao?.let{
            it.insertCountry(country)

        //When we update the country
            country.golds = 2
            it.updateCountry(country)
        }

        //Then the update should contain the updated gold medals value
        val countryTest = countryDao?.getCountryById(country.id)
        Assert.assertEquals(countryTest?.golds, 2)
    }

    /*
    Test Delete functionality
      */
    @Test
    fun should_DeleteCountry_Item() {

        // Given that we have a country in the data source
        val country = Country(2,"France",0)
        countryDao?.let{
            it.insertCountry(country)

            //When we delete the country
            it.deleteCountry(country)
        }

        //Then the data source should not contain the country with the deleted id
        val countryTest = countryDao?.getCountryById(country.id)
        Assert.assertEquals(countryTest, null)
    }

    /*
        Test DeleteAll functionality
     */
    @Test
    fun should_Flush_All_Data(){

        // Given that we delete all cpountries in the data source
        countryDao?.deleteAllCountries()

        //Then the data source should be empty
        val countryTest = getValue(countryDao?.getAllCountries()!!)
        Assert.assertEquals(countryTest.size, 0)
    }



    /*
        LiveData is an asynchronous query, you get the LiveData object but it might contain no data.
        You use an extra method to wait for the data to be filled then extract the data.
     */

    @Throws(InterruptedException::class)
    fun <T> getValue(liveData: LiveData<T>): T {
        val data = arrayOfNulls<Any>(1)
        val latch = CountDownLatch(1)
        val observer = object : Observer<T> {
            override fun onChanged(t: T?) {
                data[0] = t
                latch.countDown()
                liveData.removeObserver(this)
            }

        }
        liveData.observeForever(observer)
        latch.await(2, TimeUnit.SECONDS)

        return data[0] as T
    }
}
