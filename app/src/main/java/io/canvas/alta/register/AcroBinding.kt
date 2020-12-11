package io.canvas.alta.register

import android.text.Html
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import io.canvas.alta.R

@BindingAdapter("app:acro_setting_text")
fun acroSettingText(textView: TextView, value: RegisterStep) {
    when (value) {
        RegisterStep.READY_ARCO_1 -> {
            textView.text = Html.fromHtml(textView.resources.getString(R.string.acro_setting_1))
        }
        RegisterStep.READY_ACRO_2 -> {
            textView.text = Html.fromHtml(textView.resources.getString(R.string.acro_setting_2))
        }
        else -> {
            //test
        }
    }
}