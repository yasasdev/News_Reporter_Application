package com.example.newsreporterapplication

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.newsreporterapplication.database.NewsDatabase
import com.example.newsreporterapplication.repository.NewsRepository
import com.example.newsreporterapplication.viewmodel.NewsViewModel
import com.example.newsreporterapplication.viewmodel.NewsViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var newsViewModel: NewsViewModel

    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var loginButton: Button
    private lateinit var loginLayout: RelativeLayout

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
        loginLayout = findViewById(R.id.login_layout)

        loginButton.setOnClickListener {
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            if (validateCredentials(email, password)) {
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()

                // Hide login UI
                loginLayout.visibility = View.GONE

                // Navigate to HomeFragment
                findNavController(R.id.fragmentContainerView).navigate(R.id.homeFragment2)
            } else {
                Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show()
            }
        }

        setupViewModel()
    }

    private fun validateCredentials(email: String, password: String): Boolean {
        val validEmail = "yasaslekamge@gmail.com"
        val validPassword = "yasas123"

        return email == validEmail && password == validPassword
    }

    private fun setupViewModel() {
        val newsRepository = NewsRepository(NewsDatabase(this))
        val viewModelProviderFactory = NewsViewModelFactory(application, newsRepository)
        newsViewModel = ViewModelProvider(this, viewModelProviderFactory)[NewsViewModel::class.java]
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu) // Inflates the menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.saveMenu -> {
                // Navigate to HomeFragment when Save button is clicked
                findNavController(R.id.fragmentContainerView).navigate(R.id.homeFragment2)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
