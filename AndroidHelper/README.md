## Extensions

### [Most usefull extensions for android development](https://github.com/goodluck3301/android-roadmap/blob/main/AndroidHelper/Extensions.kt)

Ex.
```kt

/*** Hide Keyboard Extensions
 * usage:
 *      hideKeyboard()
 * */
fun Activity.hideKeyboard() {
    val imm: InputMethodManager =
        getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    val view = currentFocus ?: View(this)
    imm.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
}

fun Fragment.hideKeyboard() {
    activity?.apply {
        val imm: InputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        val view = currentFocus ?: View(this)
        imm.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }
}
```
