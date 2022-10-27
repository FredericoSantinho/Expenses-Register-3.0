package neuro.expenses.register.ui.home.sugested

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import neuro.expenses.register.R
import neuro.expenses.register.domain.entity.Product

class ProductsAdapter(val myDataset: List<Product>) :
    RecyclerView.Adapter<ProductsAdapter.SuggestedExpenseViewHolder>() {

    class SuggestedExpenseViewHolder(button: ConstraintLayout) : RecyclerView.ViewHolder(button)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SuggestedExpenseViewHolder {
        val constraintLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.suggested_product_view, parent, false) as ConstraintLayout

        return SuggestedExpenseViewHolder(
            constraintLayout
        )
    }

    fun showDetailed(fragmentManager: FragmentManager, position: Int) {

    }


    override fun onBindViewHolder(holder: SuggestedExpenseViewHolder, position: Int) {
        val itemView = holder.itemView

        val descriptionTV = itemView.findViewById<TextView>(R.id.description_text)
        val categoryTV = itemView.findViewById<TextView>(R.id.category_text)
        val priceTV = itemView.findViewById<TextView>(R.id.price_text)
        val productIconImageView = itemView.findViewById<ImageView>(R.id.product_icon)

        descriptionTV.text = myDataset[position].description
        categoryTV.text = myDataset[position].category
        priceTV.text = myDataset[position].price.toString()
        Glide.with(itemView).load(myDataset[position].iconUrl).into(productIconImageView)

        itemView.findViewById<CardView>(R.id.card_view).setOnLongClickListener({
            showDetailed(
                (itemView.context as AppCompatActivity).supportFragmentManager,
                position
            )

            return@setOnLongClickListener true
        })

    }

    private fun subCategorySeparator() = " | "

    override fun getItemCount() = myDataset.size
}
