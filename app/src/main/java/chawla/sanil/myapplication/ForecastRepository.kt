package chawla.sanil.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlin.random.Random

class ForecastRepository  {

    //give me a weeks forecast - read only that will be give me list of daily items
    private val _weeklyForecast= MutableLiveData<List<DailyForecast>>()
    val weeklyForecast: LiveData<List<DailyForecast>> = _weeklyForecast

    fun loadForecast(zipcode:String){
        val randomValues = List(10,{
          Random.nextFloat().rem(100) *100
        })
        val forecastItems=randomValues.map {temp->
            DailyForecast(
                temp, getTempDescription(temp)
            )
        }
        _weeklyForecast.value=forecastItems
    }

    private fun getTempDescription(temp:Float):String{
        return when(temp){
            in Float.MIN_VALUE.rangeTo(0f) -> " Anything below doesn't make sense"
            in 0f.rangeTo(32f)-> "Way too cold"
            in 32f.rangeTo(55f) -> "Okayish"
            in 55f.rangeTo(100f) -> "Eghhh"
            else -> "Well well"
        }
    }


}