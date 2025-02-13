package com.example.newsreporterapplication

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.newsreporterapplication.database.NewsDatabase
import com.example.newsreporterapplication.repository.NewsRepository
import com.example.newsreporterapplication.viewmodel.NewsViewModel
import com.example.newsreporterapplication.viewmodel.NewsViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var newsViewModel: NewsViewModel

    lateinit var emailInput : EditText
    lateinit var passwordInput : EditText
    lateinit var loginButton : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login_layout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        emailInput = findViewById(R.id.email_input)
        passwordInput = findViewById(R.id.password_input)
        loginButton = findViewById(R.id.login_btn)

        loginButton.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()
            //Log.i("Test Credentials", "Email : $email and Password : $password")
        }

        setupViewModel()

    }

    private fun setupViewModel() {
        val newsRepository = NewsRepository(NewsDatabase(this))
        val viewModelProviderFactory = NewsViewModelFactory(application, newsRepository)
        newsViewModel = ViewModelProvider(this, viewModelProviderFactory)[NewsViewModel::class.java]
    }

}