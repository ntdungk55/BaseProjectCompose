package com.example.basecomposeproject.extension

import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

fun View.hide(){
    visibility = View.INVISIBLE
}

fun View.show(){
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun <T> RecyclerView.Adapter<*>.autoNotify(oldList: List<T>, newList: List<T>, compare: (T, T) -> Boolean) {

    val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return compare(oldList[oldItemPosition], newList[newItemPosition])
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

        override fun getOldListSize() = oldList.size
        override fun getNewListSize() = newList.size
    })

    diff.dispatchUpdatesTo(this)
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            afterTextChanged.invoke(s.toString())
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { // na
        }
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { // na
        }
    })
}

fun EditText.numberFilter(min: Int, max: Int, onCheckDone: (String) -> Unit) {
    if(inputType != InputType.TYPE_CLASS_NUMBER) {
        return
    }

    var currentS = ""
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            s?.run {
                toString().toIntOrNull()?.let {
                    if(it < min || it > max) {
                        this@numberFilter.setText(currentS)
                        this@numberFilter.setSelection(currentS.length - 1)
                    }
                }
            }
            onCheckDone(currentS)
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { // na
            currentS = s?.toString() ?: ""
        }
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { // na
        }
    })
}

fun EditText.setActive(isActive: Boolean) {
    if(isActive) {
        isEnabled = true
        alpha = 1f
    } else {
        isEnabled = false
        alpha = 0.5f
        clearFocus()
    }
}

fun setOnClick(t: View.OnClickListener,vararg v: View?){
    v.forEach {
        it?.setOnClickListener(t)
    }
}

fun View.setMargin(left:Int, top:Int, right:Int, bottom:Int){
    if (this.layoutParams is ViewGroup.MarginLayoutParams){
        val p = this.layoutParams as ViewGroup.MarginLayoutParams
        p.setMargins(left, top, right, bottom)
        this.requestLayout()
    }
}