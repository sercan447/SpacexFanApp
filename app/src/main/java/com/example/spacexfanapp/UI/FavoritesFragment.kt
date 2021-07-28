package com.example.spacexfanapp.UI

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.spacexfanapp.R
import com.example.spacexfanapp.adapter.FavoriteAdapter
import com.example.spacexfanapp.database.AppDatabase
import com.example.spacexfanapp.databinding.FragmentFavoritesBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class FavoritesFragment : Fragment()  , FavoriteAdapter.IFavoriteItemListener{

    private lateinit var favoriteAdapter: FavoriteAdapter
    private lateinit var auth: FirebaseAuth

    private var _binding : FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    lateinit var user : FirebaseUser;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        favoriteAdapter = FavoriteAdapter(this)

        favoriteAdapter.favoriteShows = AppDatabase.getDatabase(context).favoriteDao().getAll()

        _binding = FragmentFavoritesBinding.inflate(inflater,container,false)

        binding.recyclerviewFavorites.apply {
            adapter = favoriteAdapter
            //layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
            layoutManager =  GridLayoutManager(getActivity(), 2, GridLayoutManager.HORIZONTAL, false);

            //setHasFixedSize(true)
        }
        // Initialize Firebase Auth
         auth = FirebaseAuth.getInstance()

        binding.btnLogin.setOnClickListener {
            try {

                auth.signInWithEmailAndPassword(binding.edtUseremail.text.toString(), binding.edtPass.text.toString())
                    .addOnCompleteListener(requireActivity()) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("SRC_Fav", "signInWithEmail:success")

                            binding.txtHeader.visibility = View.GONE
                            binding.lrLoginScreen.visibility = View.GONE
                            binding.recyclerviewFavorites.visibility = View.VISIBLE

                            Toast.makeText(requireContext(), "Authentication success.", Toast.LENGTH_SHORT).show()
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("SRC_Fav", "signInWithEmail:failure", task.exception)
                            Toast.makeText(requireContext(), "Authentication failed.", Toast.LENGTH_SHORT).show()

                            binding.lrLoginScreen.visibility = View.VISIBLE
                            binding.recyclerviewFavorites.visibility = View.GONE
                        }
                    }

            }catch (e:Exception) {
                Log.w("SRC_Fav", "Error"+ e.toString())
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

    override fun onResume() {
        super.onResume()
    }

    override fun onStart() {
        super.onStart()

        if(auth.currentUser != null){
            Log.e("SRC","bilgi : "+ auth.uid)
            binding.txtHeader.visibility = View.GONE
            binding.lrLoginScreen.visibility = View.GONE
            binding.recyclerviewFavorites.visibility = View.VISIBLE
        }else{
            binding.lrLoginScreen.visibility = View.VISIBLE
            binding.recyclerviewFavorites.visibility = View.GONE
        }

    }
    override fun onClickedFavorite(Id: String) {

        val bundle = Bundle()
        bundle.putString("rocketId",Id)
        findNavController().navigate(R.id.action_rocketDetailFragment,bundle)
    }
}