package bangkit.kiki.androidbeginnerfinalproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bangkit.kiki.androidbeginnerfinalproject.databinding.FruitCardBinding
import com.bumptech.glide.Glide

class ListFruitAdapter(private val listFruit: ArrayList<Fruit>): RecyclerView.Adapter<ListFruitAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    class ListViewHolder(var binding: FruitCardBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = FruitCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, scientificName, _, nutrition, _, photo) = listFruit[position]
        Glide.with(holder.itemView.context).load(photo).into(holder.binding.imgItemPhoto)
        holder.binding.tvName.text = name
        holder.binding.tvScientificName.text = scientificName
        holder.binding.tvNutrition.text = nutrition

        holder.itemView.setOnClickListener{ onItemClickCallback.onItemClicked(listFruit[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int = listFruit.size

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Fruit)
    }
}