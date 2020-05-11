package com.example.musicdao

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.musicdao.net.MusicService

class SubmitReleaseDialog(private val musicService: MusicService) : DialogFragment() {
    private lateinit var title: EditText
    private lateinit var artists: EditText
    private lateinit var releaseDate: EditText
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(activity)
            // Get the layout inflater
            val inflater = requireActivity().layoutInflater;

            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            builder.setView(inflater.inflate(R.layout.dialog_submit_release, null))
                // Add action buttons
                .setPositiveButton("Submit",
                    DialogInterface.OnClickListener { dialog, id ->
                        val titleEditText = getDialog()?.findViewById<EditText>(R.id.title)
                        val artistEditText = getDialog()?.findViewById<EditText>(R.id.artists)
                        val releaseDateEditText =
                            getDialog()?.findViewById<EditText>(R.id.release_date)
                        musicService.finishPublishing(
                            titleEditText?.text,
                            artistEditText?.text,
                            releaseDateEditText?.text
                        )
                    })
                .setNegativeButton("Cancel",
                    DialogInterface.OnClickListener { dialog, id ->
                        getDialog()?.cancel()
                    })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
