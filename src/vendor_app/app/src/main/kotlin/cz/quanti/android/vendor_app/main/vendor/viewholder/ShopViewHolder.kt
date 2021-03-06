package cz.quanti.android.vendor_app.main.vendor.viewholder

import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_shop.view.*

class ShopViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var firstProductName: TextView? = itemView.firstProductTitle
    var firstProductImage: LinearLayout? = itemView.firstProductImage
    var firstProductLayout: RelativeLayout? = itemView.firstProductLayout
    var secondProductName: TextView? = itemView.secondProductTitle
    var secondProductImage: LinearLayout? = itemView.secondProductImage
    var secondProductLayout: RelativeLayout? = itemView.secondProductLayout
    var thirdProductName: TextView? = itemView.thirdProductTitle
    var thirdProductImage: LinearLayout? = itemView.thirdProductImage
    var thirdProductLayout: RelativeLayout? = itemView.thirdProductLayout
}
