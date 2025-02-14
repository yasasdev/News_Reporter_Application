package com.example.newsreporterapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import com.example.newsreporterapplication.MainActivity
import com.example.newsreporterapplication.R
import com.example.newsreporterapplication.databinding.FragmentAddNoteBinding
import com.example.newsreporterapplication.model.News
import com.example.newsreporterapplication.viewmodel.NewsViewModel


class AddNoteFragment : Fragment(R.layout.fragment_add_note), MenuProvider {

    private var addNoteBinding: FragmentAddNoteBinding? = null
    private val binding get() = addNoteBinding!!

    private lateinit var newsViewModel: NewsViewModel
    private lateinit var addNewsView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        addNoteBinding = FragmentAddNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        newsViewModel = (activity as MainActivity).newsViewModel
        addNewsView = view
    }

    private fun saveNews(view: View){
        val newsTitle = binding.addNoteTitle.text.toString().trim()
        val newsDescription = binding.addNoteDesc.text.toString().trim()
//        val newsImage = binding.add

        if (newsTitle.isNotEmpty()){
            val news = News(0, newsTitle, newsDescription, "")
            newsViewModel.addNews(news)

            Toast.makeText(addNewsView.context, "News Saved!", Toast.LENGTH_SHORT).show()
            view.findNavController().popBackStack(R.id.homeFragment2, false)
        } else {
            Toast.makeText(addNewsView.context, "Please enter note title!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.menu_add_note, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when(menuItem.itemId){
            R.id.saveMenu -> {
                saveNews(addNewsView)
                true
            } else -> false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        addNoteBinding = null
    }
}