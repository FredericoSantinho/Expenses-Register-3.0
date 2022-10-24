package neuro.expenses.register.ui.home

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.SupportMapFragment
import neuro.expenses.register.R
import neuro.expenses.register.common.android.BaseFragment
import neuro.expenses.register.common.viewBinding
import neuro.expenses.register.databinding.FragmentHomeBinding
import neuro.expenses.register.ui.home.sugested.ProductsAdapter
import neuro.expenses.register.ui.map.OnMapReady
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {

  val binding by viewBinding(FragmentHomeBinding::bind)
  val homeViewModel: HomeViewModel by viewModel()

  override fun getLayout(): Int = R.layout.fragment_home

  override fun setupViewModel() {
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    setupMap()
    setupRecyclerView(view)
    setupLocationSpinner(view)
    setupDateTimePicker(view)
  }

  private fun setupMap() {
    val mapFragment = childFragmentManager
      .findFragmentById(R.id.map) as SupportMapFragment
    mapFragment.getMapAsync(OnMapReady())
  }

  private fun setupRecyclerView(root: View) {
    val recyclerView = root.findViewById<RecyclerView>(R.id.place_suggested_recycler_view)

    recyclerView.setHasFixedSize(true)
    val layoutManager =
      GridLayoutManager(recyclerView.context, 2, LinearLayoutManager.VERTICAL, false)
    recyclerView.layoutManager = layoutManager

    val adapter = ProductsAdapter(
      homeViewModel.getPlaceProducts()
    )
    recyclerView.adapter = adapter
  }

  private fun setupLocationSpinner(root: View) {
    ArrayAdapter(requireContext(), R.layout.simple_spinner_item, homeViewModel.getPlacesNames())
      .also { adapter ->
        val spinner: Spinner = root.findViewById(R.id.places_spinner)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
      }
  }

  private fun setupDateTimePicker(root: View) {
    val dateTextView = root.findViewById<TextView>(R.id.date_text_view)

    dateTextView.setOnClickListener {
      showDateTimePicker(
        (requireActivity() as AppCompatActivity).supportFragmentManager
      )
    }
  }

  private fun showDateTimePicker(supportFragmentManager: FragmentManager) {
  }
}