package chawla.sanil.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class DailyForecastViewHolder(view:View) : RecyclerView.ViewHolder(view){
    private val tempText=view.findViewById<TextView>(R.id.tempText)
    private val desc:TextView=view.findViewById(R.id.desc)

    fun bind(dailyForecast: DailyForecast){
        tempText.text=String.format("%.2f",dailyForecast.temp)
        desc.text=dailyForecast.desc
    }



}
class DailyForecastAdapter(
    private val clickHandler:(DailyForecast)->Unit
): ListAdapter<DailyForecast,DailyForecastViewHolder>(DIFF_CONFIG) {

    companion object {
        val DIFF_CONFIG= object: DiffUtil.ItemCallback<DailyForecast>(){
            override fun areItemsTheSame(oldItem: DailyForecast, newItem: DailyForecast): Boolean {
                return oldItem===newItem
            }

            override fun areContentsTheSame(
                oldItem: DailyForecast,
                newItem: DailyForecast
            ): Boolean {
                return oldItem==newItem
            }

        }
    }
    //create a new view that holds item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyForecastViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.item_daily_forecast,parent,false)
        return DailyForecastViewHolder(itemView)
    }

    //pass data to view holder
    override fun onBindViewHolder(holder: DailyForecastViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener{
            clickHandler(getItem(position))
        }
    }

}