package com.example.newsreporterapplication.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.example.newsreporterapplication.MainActivity
import com.example.newsreporterapplication.R
import com.example.newsreporterapplication.databinding.FragmentAddNoteBinding
import com.example.newsreporterapplication.model.News
import com.example.newsreporterapplication.viewmodel.NewsViewModel

class AddNoteFragment : Fragment(R.layout.fragment_add_note), MenuProvider {

    private var _binding: FragmentAddNoteBinding? = null
    private val binding get() = _binding!!

    private lateinit var newsViewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        newsViewModel = (activity as MainActivity).newsViewModel

        // Set Toolbar as ActionBar
        val toolbar = view.findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
    }

    private fun saveNews() {
        val newsTitle = binding.addNoteTitle.text.toString().trim()
        val newsDescription = binding.addNoteDesc.text.toString().trim()

        if (newsTitle.isNotEmpty()) {
            val news = News(0, newsTitle, newsDescription, "")
            newsViewModel.addNews(news)

            Toast.makeText(requireContext(), "News Saved!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addNoteFragment2_to_homeFragment2)
        } else {
            Toast.makeText(requireContext(), "Please enter a note title!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.menu_add_note, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.saveMenu -> {
                saveNews()
                true
            }
            else -> false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
