package com.examples.myfoodapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.examples.foodapp.MainActivity
import com.examples.foodapp.R
import com.examples.foodapp.databinding.ActivityLoginBinding
import com.examples.foodapp.datamodel.usermodel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database

class loginActivity : AppCompatActivity() {

    private lateinit var emaail : String
    private lateinit var password : String
    private lateinit var auth : FirebaseAuth
    private lateinit var database : DatabaseReference
    private lateinit var googleSignInclient : GoogleSignInClient

    private val binding : ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build()

        auth = FirebaseAuth.getInstance()
        database = Firebase.database.reference;
        googleSignInclient = GoogleSignIn.getClient(this,googleSignInOptions)

        binding.loginbtn.setOnClickListener {
            emaail = binding.editTextTextEmailAddress.text.toString().trim()
            password = binding.editTextTextPassword.text.toString().trim()

            if(emaail.isBlank() || password.isBlank()){
                Toast.makeText(this,"Fill All Details",Toast.LENGTH_SHORT).show()
            }else{
                createuser()
            }
        }

        binding.googlebtn.setOnClickListener{
            val intent = googleSignInclient.signInIntent
            launcher.launch(intent)
        }

        binding.donthavebtn.setOnClickListener{
            val intent = Intent(this,signupactivity::class.java)
            startActivity(intent)
        }
    }

    private fun createuser(){
        auth.signInWithEmailAndPassword(emaail,password).addOnCompleteListener { task->
            if(task.isSuccessful){
                val user = auth.currentUser
                updateui(user);
                Toast.makeText(this,"Login Succesfull",Toast.LENGTH_SHORT).show()
            }
            else{
                auth.createUserWithEmailAndPassword(emaail, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        updateui(user)
                        Toast.makeText(this, "Account Created Successful", Toast.LENGTH_SHORT).show()
                        saveuserdata()
                    } else {
                        Toast.makeText(this, "Account Creation Failed", Toast.LENGTH_SHORT).show()
                        Log.d("Account", "createAccount : Failure", task.exception)
                    }
                }
            }
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

    private fun saveuserdata() {
        emaail = binding.editTextTextEmailAddress.text.toString().trim()
        password = binding.editTextTextPassword.text.toString().trim()

        val user = usermodel(null,emaail,password)
        val userId = FirebaseAuth.getInstance().currentUser!!.uid
        database.child("user").child(userId).setValue(user)
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if(currentUser != null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun updateui(user: FirebaseUser?) {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}