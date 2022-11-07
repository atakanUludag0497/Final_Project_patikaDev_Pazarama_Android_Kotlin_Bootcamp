package com.example.shoppingapp_04.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import com.example.shoppingapp_04.R
import com.example.shoppingapp_04.data.model.ShopItem
import com.example.shoppingapp_04.data.util.Resource
import com.example.shoppingapp_04.databinding.FragmentSearchBinding
import com.example.shoppingapp_04.presentation.adapter.SearchAdapter
import com.example.shoppingapp_04.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SearchFragment : Fragment() {

    @Inject
    lateinit var viewModel : HomeViewModel

    @Inject
    lateinit var adapter : SearchAdapter

    private lateinit var binding : FragmentSearchBinding

    private var productsList = listOf<ShopItem>()
    private var productsList2 = listOf<ShopItem>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding = FragmentSearchBinding.bind(view)

        viewModel.getAllProducts()

        viewModel.products.observe(viewLifecycleOwner){result ->
            when(result){
                is Resource.Success -> {
                    Log.i("SearchFragment","the idiot dey successful sha ${result.data}")
                    productsList = result.data!!
                    adapter.differ.submitList(result.data)
                }
                is Resource.Loading -> {
                    Log.i("SearchFragment","the idiot dey load")
                }
                is Resource.Error -> {
                    Log.i("SearchFragment","the idiot fail sha, ${result.message}")
                }
            }
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                productsList2 = productsList.filter { it.title.contains("$query",ignoreCase = true)}
                adapter.differ.submitList(productsList2)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                productsList2 = productsList.filter { it.title.contains("$newText",ignoreCase = true)}
                adapter.differ.submitList(productsList2)
                return true
            }

        })

        binding.searchView.setOnCloseListener {
            adapter.differ.submitList(productsList)
            true
        }

        binding.searchBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.searchCart.setOnClickListener {
            findNavController().navigate(R.id.action_searchFragment_to_cartFragment)
        }

        binding.searchRecyclerView.adapter = adapter

        adapter.setOnItemClickListener {
            val action = SearchFragmentDirections.actionSearchFragmentToProductDetailFragment(it)
            findNavController().navigate(action)
        }


    }

}