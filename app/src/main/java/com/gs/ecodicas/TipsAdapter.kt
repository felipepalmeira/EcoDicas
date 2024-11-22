package com.gs.ecodicas



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class TipsAdapter(private val tipsList: List<Tip>) : RecyclerView.Adapter<TipsAdapter.TipViewHolder>() {

    inner class TipViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)

        init {
            itemView.setOnClickListener {
                val position = bindingAdapterPosition
                val context = itemView.context
                val tip = tipsList[position]
                Toast.makeText(context, "Dica: ${tip.title}\nDescrição: ${tip.description}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_tip, parent, false)
        return TipViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TipViewHolder, position: Int) {
        val tip = tipsList[position]
        holder.tvTitle.text = tip.title
        holder.tvDescription.text = tip.description
    }

    override fun getItemCount() = tipsList.size
}
