package chawla.sanil.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private  val forecastRepository=ForecastRepository()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val zipcodeEditText:EditText=findViewById(R.id.zipCodeText)
        val enterButton:Button=findViewById(R.id.enterButton)
        enterButton.setOnClickListener {
            val zipcode:String=zipcodeEditText.text.toString();

            if(zipcode.length!=5){
                Toast.makeText(this,R.string.error_text, Toast.LENGTH_SHORT).show()
            }else{
                forecastRepository.loadForecast(zipcode)
            }
        }

        val forecastList: RecyclerView=findViewById(R.id.forecastList)
        forecastList.layoutManager=LinearLayoutManager(this)
        val dailyForecastAdapter=DailyForecastAdapter(){forecastItem->
            val message=getString(R.string.forecast_clicked_format,forecastItem.temp,forecastItem.desc)
            Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
        }

        forecastList.adapter=dailyForecastAdapter

        val weeklyForecastObserver=Observer<List<DailyForecast>>{forecastItems->
            // update our listadapter
            dailyForecastAdapter.submitList(forecastItems)
        }

        forecastRepository.weeklyForecast.observe(this,weeklyForecastObserver)


    }
}