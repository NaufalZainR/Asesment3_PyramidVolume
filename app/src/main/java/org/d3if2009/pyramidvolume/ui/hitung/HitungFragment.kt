package org.d3if2009.pyramidvolume.ui.hitung

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.d3if2009.pyramidvolume.R
import org.d3if2009.pyramidvolume.databinding.FragmentHitungBinding
import org.d3if2009.pyramidvolume.db.DataDb
import org.d3if2009.pyramidvolume.model.HasilHitung

class HitungFragment: Fragment() {

    private lateinit var binding: FragmentHitungBinding

    private val viewModel: HitungViewModel by lazy {
        val db = DataDb.getInstance(requireContext())
        val factory = HitungViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[HitungViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentHitungBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.buttonCalculate.setOnClickListener { calculate() }
        binding.shareButton.setOnClickListener { shareData() }
        viewModel.getHasil().observe(requireActivity(), { showResult(it) })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_histori -> {
                findNavController().navigate(
                    R.id.action_hitungFragment_to_historiFragment)
                return true
            }
            R.id.menu_about -> {
                findNavController().navigate(
                    R.id.action_hitungFragment_to_aboutFragment
                )
                return true
            }
            R.id.menu_pyramid -> {
                findNavController().navigate(
                    R.id.action_hitungFragment_to_pyramidFragment
                )
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun calculate() {
        val inputPanjang = binding.editPanjang.text.toString()
        val inputLebar = binding.editLebar.text.toString()
        val inputTinggi = binding.editTinggi.text.toString()

        if (TextUtils.isEmpty(inputPanjang) || TextUtils.isEmpty(inputLebar) || TextUtils.isEmpty(inputTinggi)) {
            Toast.makeText(context, R.string.input_invalid, Toast.LENGTH_LONG).show()
            return
        }

        viewModel.calculate(
            inputPanjang.toFloat(),
            inputLebar.toFloat(),
            inputTinggi.toFloat(),
        )
    }

    private fun showResult(result: HasilHitung?) {
        if (result == null) return
        binding.textViewResult.text = result.hasil.toString()
    }

    private fun shareData() {
        val message = "Jika ingin mengukur luas piramida di mesir, pakailah aplikasi ini!"
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
        if (shareIntent.resolveActivity(
                requireActivity().packageManager) != null) {
            startActivity(shareIntent)
        }
    }

}