package cz.quanti.android.vendor_app.main.checkout.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cz.quanti.android.vendor_app.R
import cz.quanti.android.vendor_app.main.checkout.fragment.CheckoutFragment
import cz.quanti.android.vendor_app.main.checkout.viewholder.SelectedProductsViewHolder
import cz.quanti.android.vendor_app.repository.product.dto.SelectedProduct
import cz.quanti.android.vendor_app.utils.misc.getStringFromDouble

class SelectedProductsAdapter(private val checkoutFragment: CheckoutFragment) :
    RecyclerView.Adapter<SelectedProductsViewHolder>() {

    private val products: MutableList<SelectedProduct> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedProductsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_checkout_selected_product, parent, false)
        return SelectedProductsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: SelectedProductsViewHolder, position: Int) {
        val item = products[position]

        // TODO handle images
        holder.productDetail.text = item.product.name
        holder.amount.text = getStringFromDouble(item.quantity) + " " + item.product.unit
        holder.price.text =
            getStringFromDouble(item.subTotal) + " " + checkoutFragment.chosenCurrency
    }

    fun setData(data: List<SelectedProduct>) {
        products.clear()
        products.addAll(data)
        notifyDataSetChanged()
    }
}
