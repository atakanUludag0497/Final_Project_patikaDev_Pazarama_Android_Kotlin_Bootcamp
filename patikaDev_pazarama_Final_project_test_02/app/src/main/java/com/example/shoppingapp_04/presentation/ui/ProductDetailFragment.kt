package com.example.shoppingapp_04.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.shoppingapp_04.R
import com.example.shoppingapp_04.data.model.CartItem2
import com.example.shoppingapp_04.data.model.ShopItem
import com.example.shoppingapp_04.data.util.Utils
import com.example.shoppingapp_04.databinding.FragmentProductDetailBinding
import com.example.shoppingapp_04.presentation.viewmodel.ProductDetailViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class ProductDetailFragment : Fragment() {

    private lateinit var binding : FragmentProductDetailBinding

    @Inject
    lateinit var viewModel : ProductDetailViewModel

    private lateinit var shopItem: ShopItem

    private var like = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        shopItem = ProductDetailFragmentArgs.fromBundle(requireArguments()).shopItem

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product_detail,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentProductDetailBinding.bind(view)

        updateViews()

        binding.productDetailBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.productDetailAddToCart.setOnClickListener {
            val cartItem = CartItem2(shopItem.id,shopItem.image,
                Utils.formatPrice(shopItem.price.toString()),shopItem.title,1,shopItem.price)
            viewModel.saveToCart(cartItem)
            Snackbar.make(binding.productDetailAddToCart,"Added to Cart Successfully", Snackbar.LENGTH_SHORT).show()
        }

        binding.productDetailCart.setOnClickListener {
            findNavController().navigate(R.id.action_productDetailFragment_to_cartFragment)
        }

        binding.productDetailLike.setOnClickListener {
            if (like){
                like = false
                viewModel.removeFromWishlist(shopItem)
                binding.productDetailLike.setImageResource(R.drawable.ic_not_favorite)
                Snackbar.make(binding.productDetailAddToCart,"Removed from wishlist", Snackbar.LENGTH_SHORT).show()
            }else{
                like = true
                viewModel.addToWishlist(shopItem)
                binding.productDetailLike.setImageResource(R.drawable.ic_favorite)
                Snackbar.make(binding.productDetailAddToCart,"Added to wishlist", Snackbar.LENGTH_SHORT).show()
            }
        }

    }

    private fun updateViews() {

        binding.productDetailPrice.text = "USD ${shopItem.price}"
        binding.productDetailTitle.text = shopItem.title
        binding.productDetailDescription.text = shopItem.description

        Glide.with(binding.productDetailImage)
            .load(shopItem.image)
            .into(binding.productDetailImage)

        binding.productDetailRating.text = "${shopItem.rating.rate}"
        binding.productDetailReviews.text = "${shopItem.rating.count} Reviews"

    }

}