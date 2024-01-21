package com.example.fotofix.activity

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import com.dsphotoeditor.sdk.activity.DsPhotoEditorActivity
import com.dsphotoeditor.sdk.utils.DsPhotoEditorConstants
import com.example.fotofix.R
import com.example.fotofix.databinding.ActivityEditingactivityBinding


class Editing : AppCompatActivity() {
    lateinit var binding: ActivityEditingactivityBinding
private val  EDIT_PHOTO_REQUEST_CODE=100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@Editing, R.layout.activity_editingactivity)
        val image = intent.getStringExtra("image").toString()
        val editorintent=Intent(this,DsPhotoEditorActivity::class.java)
        editorintent.data=image.toUri()
        editorintent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_OUTPUT_DIRECTORY,"Edited Image")
        startActivityForResult(editorintent,EDIT_PHOTO_REQUEST_CODE)


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            if (requestCode==EDIT_PHOTO_REQUEST_CODE){
                val outputuri:Uri?=data?.data
                if (outputuri!=null){
                    binding.editedimage.setImageURI(outputuri)
                }else {
                    Toast.makeText(this, "Error loading edited image", Toast.LENGTH_SHORT).show()
                }
            }else {
                Toast.makeText(this, "Unexpected request code", Toast.LENGTH_SHORT).show()
            }
        }else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "Editing canceled", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Error during editing", Toast.LENGTH_SHORT).show()
        }
    }

}