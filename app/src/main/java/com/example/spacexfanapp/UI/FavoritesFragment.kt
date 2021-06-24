package com.example.spacexfanapp.UI

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.transition.Visibility
import com.example.spacexfanapp.R
import com.example.spacexfanapp.databinding.FragmentFavoritesBinding
import com.example.spacexfanapp.databinding.FragmentLauncherBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize

class FavoritesFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    private var _binding : FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    lateinit var user : FirebaseUser;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentFavoritesBinding.inflate(inflater,container,false)

        // Initialize Firebase Auth
         auth = FirebaseAuth.getInstance()

        binding.btnLogin.setOnClickListener {
            try {

                auth.signInWithEmailAndPassword(binding.edtUseremail.text.toString(), binding.edtPass.text.toString())
                    .addOnCompleteListener(requireActivity()) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("SERCAN", "signInWithEmail:success")

                            binding.txtHeader.visibility = View.GONE
                            binding.lrLoginScreen.visibility = View.GONE
                            binding.recyclerviewFavorites.visibility = View.VISIBLE

                            Toast.makeText(requireContext(), "Authentication success.", Toast.LENGTH_SHORT).show()
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("SERCAN", "signInWithEmail:failure", task.exception)
                            Toast.makeText(requireContext(), "Authentication failed.", Toast.LENGTH_SHORT).show()

                            binding.lrLoginScreen.visibility = View.VISIBLE
                            binding.recyclerviewFavorites.visibility = View.GONE
                        }
                    }

            }catch (e:Exception) {
                Log.w("SERCAN", "Error"+ e.toString())
            }
        }

        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FavoritesFragment().apply {

            }
    }
}