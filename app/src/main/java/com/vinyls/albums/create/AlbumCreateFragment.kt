package com.vinyls.albums.create

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.vinyls.R
import com.vinyls.albums.list.AlbumsStatus
import kotlinx.coroutines.launch
import java.lang.Exception

class AlbumCreateFragment : Fragment() {

    //private lateinit var albumName: EditText
    private lateinit var albumUrl: EditText
    private lateinit var albumDate: EditText
    private lateinit var albumDescription: EditText
    private lateinit var albumGender: EditText
    private lateinit var albumSeal: EditText
    private lateinit var albumSaveBtn: Button

    companion object {
        fun newInstance() = AlbumCreateFragment()
    }

    private lateinit var viewModel: AlbumCreateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_album_create, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AlbumCreateViewModel::class.java)
        // TODO: Use the ViewModel

        val albumName = view?.findViewById<EditText>(R.id.albumName);
        val albumUrl = view?.findViewById<EditText>(R.id.albumUrl);
        val albumDate = view?.findViewById<EditText>(R.id.albumDate);
        val albumDescription = view?.findViewById<EditText>(R.id.albumDescription);
        val albumGender = view?.findViewById<EditText>(R.id.albumGender);
        val albumSeal = view?.findViewById<EditText>(R.id.albumSeal);
        val albumSaveBtn = view?.findViewById<Button>(R.id.albumSaveBtn);

        albumSaveBtn?.setOnClickListener {
            if(
                albumName?.text.toString().length > 0 &&
                albumUrl?.text.toString().length > 0 &&
                albumDate?.text.toString().length > 0 &&
                albumDescription?.text.toString().length > 0 &&
                albumGender?.text.toString().length > 0 &&
                albumSeal?.text.toString().length > 0
            ){

                findNavController().navigate(R.id.navigation_albums_list);
                Toast.makeText(context, "Album guardado", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context, "Faltan campos requeridos", Toast.LENGTH_SHORT).show()
            }
        }

    }

}