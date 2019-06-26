package com.thernat.pets.utils.dialogs

import android.app.AlertDialog
import android.content.Context
import com.thernat.pets.R
import javax.inject.Inject

/**
 * Created by m.rafalski on 2019-06-26.
 */
class SimpleDialogCreator @Inject constructor() {


    fun displayAlert(context: Context, message: String, onDismissed: () -> Unit = {}) {
        val alertDialog = AlertDialog.Builder(context).create()
        alertDialog.setMessage(message)
        alertDialog.setButton(
            AlertDialog.BUTTON_NEUTRAL,context.getString(R.string.ok)
        ) { dialog, _ -> dialog.dismiss()
            onDismissed()
        }
        alertDialog.show()
    }

    fun displayConfirmationDialogAlert(context: Context, message: String, onConfirm: () -> Unit, onDismissed: () -> Unit = {}) {
        val alertDialog = AlertDialog.Builder(context).create()
        alertDialog.setMessage(message)
        alertDialog.setOnDismissListener{
            onDismissed()
        }
        alertDialog.setButton(
            AlertDialog.BUTTON_POSITIVE,context.getString(R.string.ok)
        ) { dialog, _ -> dialog.dismiss()
            onConfirm()
        }
        alertDialog.setButton(
            AlertDialog.BUTTON_NEGATIVE,context.getString(R.string.cancel)
        ) { dialog, _ -> dialog.dismiss()
        }
        alertDialog.show()
    }
}