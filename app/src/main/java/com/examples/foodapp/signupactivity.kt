package com.examples.myfoodapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.examples.foodapp.MainActivity
import com.examples.foodapp.R
import com.examples.foodapp.databinding.ActivitySignupactivityBinding
import com.examples.foodapp.datamodel.usermodel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

class signupactivity : AppCompatActivity() {

    private lateinit var email: String
    private lateinit var password: String
    private lateinit var name: String
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var googleSignInClient: GoogleSignInClient

    private val binding: ActivitySignupactivityBinding by lazy {
        ActivitySignupactivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build()
        // Initialize Firebase Auth
        auth = Firebase.auth
        // initialize firebase database
        database = Firebase.database.reference
        // Initialize Google Sign In Client
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)

        binding.createaccountbtn.setOnClickListener {
            email = binding.edittextemail.text.toString().trim()
            password = binding.editTextTextPassword.text.toString().trim()
            name = binding.edittextName.text.toString()

            if (email.isBlank() || password.isBlank() || name.isBlank()) {
                Toast.makeText(this, "Fill All Details", Toast.LENGTH_SHORT).show()
            } else {
                createAccount(email, password)
            }
        }


        binding.googlebtn.setOnClickListener {
            val intent = googleSignInClient.signInIntent
            launcher.launch(intent)
        }



        binding.alreadyhavebtn.setOnClickListener {
            val intent = Intent(this, loginActivity::class.java)
            startActivity(intent)
        }
    }

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                if (task.isSuccessful) {
                    val account: GoogleSignInAccount? = task.result
                    val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
                    auth.signInWithCredential(credential).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
//                        if(task.result?.additionalUserInfo?.isNewUser == true){
//                            saveuserdata()
//                        }
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this, "Authentication Failed", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this, "Authentication Failed", Toast.LENGTH_SHORT).show()
                }
            }
        }

    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(this, "Account Created Successful", Toast.LENGTH_SHORT).show()
                saveuserdata()
                val intent = Intent(this, loginActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Account Creation Failed", Toast.LENGTH_SHORT).show()
                Log.d("Account", "createAccount : Failure", task.exception)
            }
        }
    }

    private fun saveuserdata() {
        name = binding.edittextName.text.toString()
        email = binding.edittextemail.text.toString().trim()
        password = binding.editTextTextPassword.text.toString().trim()

        val user = usermodel(name, email, password)
        val userId = FirebaseAuth.getInstance().currentUser!!.uid
        database.child("user").child(userId).setValue(user)
    }
}