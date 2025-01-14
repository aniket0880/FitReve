package com.uilover.project1932.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.uilover.project1932.R
import com.uilover.project1932.databinding.ActivitySignUp2Binding

class SignUp_Activity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var email:String
    private lateinit var userName:  String
    private lateinit var password:String
    private lateinit var database: DatabaseReference
    private val binding:ActivitySignUp2Binding by lazy {
        ActivitySignUp2Binding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        //inisialise firebase suth
        auth= Firebase.auth
        //inisialise firebase database
        database=Firebase.database.reference

        // on clicking the create account button
        binding.createbtn.setOnClickListener{
            //gets text from the edit text
            userName=binding.name.text.toString().trim()
            email=binding.email.text.toString().trim()
            password=binding.password.text.toString().trim()

            //if someone keeps any feild empty
            if (userName.isBlank()||email.isBlank()||password.isBlank()){
                Toast.makeText(this,"Please fill all details",Toast.LENGTH_SHORT).show()
            }
            else{
                CreateAccount(email,password)
            }


        }
        binding.alreadyhavebutton.setOnClickListener{
            val intent= Intent(this,Login_Activity::class.java)
            startActivity(intent)
        }

    }

    private fun CreateAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{ task->
            if(task.isSuccessful){
                Toast.makeText(this,"Account Created Successfully",Toast.LENGTH_SHORT).show()
                saveUserData()
                val intent= Intent(this,Login_Activity::class.java)
                startActivity(intent)
                finish()
            }
            else{
                Toast.makeText(this,"Account Creation Failed",Toast.LENGTH_SHORT).show()
                Log.d("Account","createAccount:Failure",task.exception)
            }
        }

    }
    // function to save data in database
    private fun saveUserData() {
        //gets text from the edit text
        userName=binding.name.text.toString().trim()
        email=binding.email.text.toString().trim()
        password=binding.password.text.toString().trim()
        val user=UserModal(userName,email,password)
        val userId=FirebaseAuth.getInstance().currentUser!!.uid
        //save user data firebase database
        database.child("user").child(userId).setValue(user)

    }
}