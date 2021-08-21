package kr.co.bepo.arcticfoxprogaramming.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.co.bepo.arcticfoxprogaramming.Product
import kr.co.bepo.arcticfoxprogaramming.R
import kr.co.bepo.arcticfoxprogaramming.databinding.MainFragmentBinding
import java.util.*

class MainFragment : Fragment() {

    private var adapter: ProductListAdapter? = null

    companion object {
        fun newInstance() = MainFragment()
    }

    val viewModel: MainViewModel by viewModels()
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listenerSetup()
        observerSetup()
        recyclerSetup()
    }

    private fun clearFields() {
        binding.productID.text = ""
        binding.productName.setText("")
        binding.productQuantity.setText("")
    }

    @SuppressLint("SetTextI18n")
    private fun listenerSetup() {
        binding.addButton.setOnClickListener {
            val name = binding.productName.text.toString()
            val quantity = binding.productQuantity.text.toString()

            if (name != "" && quantity != "") {
                val product = Product(productName = name, quantity = Integer.parseInt(quantity))
                viewModel.insertProduct(product)
                clearFields()
            } else {
                binding.productID.text = "Incomplete information"
            }
        }

        binding.findButton.setOnClickListener {
            viewModel.findProduct(binding.productName.text.toString())
        }

        binding.deleteButton.setOnClickListener {
            viewModel.deleteProduct(binding.productName.text.toString())
            clearFields()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun observerSetup() {
        viewModel.getAllProducts()?.observe(viewLifecycleOwner) { products ->
            products?.let {
                adapter?.setProductList(it)
            }
        }

        viewModel.getSearchResults().observe(viewLifecycleOwner) { products ->
            products?.let {
                if (it.isNotEmpty()) {
                    binding.productID.text = String.format(Locale.US, "%d", it[0].id)
                    binding.productName.setText(it[0].productName)
                    binding.productQuantity.setText(String.format(Locale.US, "%d",
                        it[0].quantity))
                } else {
                    binding.productID.text = "No Match"
                }
            }
        }
    }

    private fun recyclerSetup() {
        adapter = ProductListAdapter(R.layout.product_list_item)

        val recyclerView: RecyclerView? = view?.findViewById(R.id.product_recylcer)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}