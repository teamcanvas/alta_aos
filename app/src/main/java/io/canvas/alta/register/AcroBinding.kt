package io.canvas.alta.register

import android.text.Html
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import io.canvas.alta.R

@BindingAdapter("app:acro_setting_text")
fun acroSettingText(textView: TextView, value: RegisterStep) {
    when (value) {
        RegisterStep.READY_ACRO_1 -> {
            textView.text = Html.fromHtml(textView.resources.getString(R.string.acro_setting_1))
        }
        RegisterStep.READY_ACRO_2 -> {
            textView.text = Html.fromHtml(textView.resources.getString(R.string.acro_setting_2))
        }
        RegisterStep.READY_ACRO_3 -> {
            textView.text = Html.fromHtml(textView.resources.getString(R.string.acro_setting_3))
        }
    }
}

@BindingAdapter("app:acro_setting_image")
fun acroSettingImage(imageView: ImageView, value: RegisterStep) {
    when (value) {
        RegisterStep.READY_ACRO_1 -> {
            imageView.setImageDrawable(ContextCompat.getDrawable(imageView.context, R.drawable.acro_setting_1))
        }
        RegisterStep.READY_ACRO_2 -> {
            imageView.setImageDrawable(ContextCompat.getDrawable(imageView.context, R.drawable.acro_setting_2))
        }
        RegisterStep.READY_ACRO_3 -> {
            imageView.setImageDrawable(ContextCompat.getDrawable(imageView.context, R.drawable.acro_setting_3))
        }
    }
}