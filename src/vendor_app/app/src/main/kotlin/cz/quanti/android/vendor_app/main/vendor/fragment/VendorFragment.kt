package cz.quanti.android.vendor_app.main.vendor.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import cz.quanti.android.vendor_app.MainActivity
import cz.quanti.android.vendor_app.R
import cz.quanti.android.vendor_app.main.vendor.adapter.ShopAdapter
import cz.quanti.android.vendor_app.main.vendor.callback.VendorFragmentCallback
import cz.quanti.android.vendor_app.main.vendor.viewmodel.VendorViewModel
import cz.quanti.android.vendor_app.repository.product.dto.Product
import cz.quanti.android.vendor_app.repository.product.dto.SelectedProduct
import cz.quanti.android.vendor_app.repository.voucher.dto.Voucher
import cz.quanti.android.vendor_app.utils.CurrentVendor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_vendor.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import quanti.com.kotlinlog.Log

class VendorFragment() : Fragment(), VendorFragmentCallback {
    private val vm: VendorViewModel by viewModel()
    private var disposables = CompositeDisposable()
    private val adapter = ShopAdapter(this)

    lateinit var chosenCurrency: String
    lateinit var cart: MutableList<SelectedProduct>
    lateinit var vouchers: MutableList<Voucher>

    val args: VendorFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (activity as MainActivity).supportActionBar?.show()
        return inflater.inflate(R.layout.fragment_vendor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CurrentVendor
        chosenCurrency = args.currency
        cart = (activity as MainActivity).cart
        vouchers = (activity as MainActivity).vouchers

        val shoppingCartFragment = ShoppingCartFragment()

        val transaction = childFragmentManager.beginTransaction()?.apply {
            replace(R.id.fragmentContainer, shoppingCartFragment)
        }
        transaction?.commit()
    }

    override fun onResume() {
        super.onResume()

        val toolbar = (activity as MainActivity).supportActionBar
        toolbar?.title = getString(R.string.vendor_title)
        toolbar?.setDisplayHomeAsUpEnabled(false)
        toolbar?.setDisplayShowTitleEnabled(true)
        toolbar?.setDisplayShowCustomEnabled(true)

        (activity as MainActivity).invalidateOptionsMenu()
    }
    override fun onStart() {
        super.onStart()
        setAdapter()

        disposables.add(vm.getProducts().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                { products ->
                    adapter.setData(products)
                },
                {
                    Log.e(it)
                }
            )
        )
    }

    override fun chooseProduct(product: Product) {
        val productDetailFragment = ProductDetailFragment(product)
        val transaction = childFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainer, productDetailFragment)
        }
        transaction.commit()
    }

    override fun getCurrency(): String {
        return chosenCurrency
    }

    override fun removeFromCart(position: Int) {
        cart.removeAt(position)
    }

    override fun getShoppingCart(): MutableList<SelectedProduct> {
        return cart
    }

    override fun clearCart() {
        cart.clear()
    }

    override fun setCurrency(currency: String) {
        chosenCurrency = currency
    }

    override fun addToShoppingCart(product: SelectedProduct) {
        cart.add(product)
    }

    private fun setAdapter() {
        val viewManager = LinearLayoutManager(activity)

        shopRecyclerView.setHasFixedSize(true)
        shopRecyclerView.layoutManager = viewManager
        shopRecyclerView.adapter = adapter
    }

    override fun onDestroy() {
        disposables.dispose()
        super.onDestroy()
    }
}
