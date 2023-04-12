import android.app.Activity
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*


/*** Log Extensions
 * usage:
 *      user.printToLog(tag = "USER_INFO")
 * */
fun Any?.printToLog(tag: String = "DEBUG_LOG") =
    Log.d(tag, toString())


/*** View Extensions
 * usage:
 *      view.gone() / view.visible() / view.invisible()
 * */
fun View.gone() = run { visibility = View.GONE }

fun View.visible() = run { visibility = View.VISIBLE }

fun View.invisible() = run { visibility = View.INVISIBLE }

infix fun View.visibleIf(condition: Boolean) =
    run { visibility = if (condition) View.VISIBLE else View.GONE }

infix fun View.goneIf(condition: Boolean) =
    run { visibility = if (condition) View.GONE else View.VISIBLE }


/*** Toast Message Extensions
 * usage:
 *      toast("This is toast message")
 * */
fun Fragment.toast(message: String) =
    Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()

fun Fragment.toast(@StringRes message: Int) =
    Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()

fun Activity.toast(message: String) =
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()

fun Activity.toast(@StringRes message: Int) =
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()


/*** Snackbar Message Extensions
 * usage:
 *      rootView.snackbar("This is snackbar message"duration = Snackbar.LENGTH_SHORT)
 *      rootView.snackbar("This is snackbar message")
 *      rootView.snackbar(R.string.snackbar_message)
 * */
fun View.snackbar(message: String, duration: Int = Snackbar.LENGTH_LONG) =
    Snackbar.make(this, message, duration).show()

fun View.snackbar(@StringRes message: Int, duration: Int = Snackbar.LENGTH_LONG) =
    Snackbar.make(this, message, duration).show()


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


/** Digit, Alphabetic, and Alphanumeric Check
 * usage:
 *      val isValidNumber = "1234".isDigitOnly  // Return true
 *      val isValid = "1234abc".isDigitOnly        // Return false
 *      val isOnlyAlphabetic = "abcABC".isAlphabeticOnly       // Return true
 *      val isOnlyAlphabetic2 = "abcABC123".isAlphabeticOnly  // Return false
 *      val isOnlyAlphanumeric = "abcABC123".isAlphanumericOnly     // Return true
 *      val isOnlyAlphanumeric2 = "abcABC@123.".isAlphanumericOnly // Return false
 * */
val String.isDigitOnly: Boolean
    get() = matches(Regex("^\\d*\$"))

val String.isAlphabeticOnly: Boolean
    get() = matches(Regex("^[a-zA-Z]*\$"))

val String.isAlphanumericOnly: Boolean
    get() = matches(Regex("^[a-zA-Z\\d]*\$"))


/** isNull
 *
 * usage:
 *      if (obj.isNull) { }
 **/
val Any?.isNull get() = this == null


/** ifNull
 * usage:
 *      obj.ifNull { }
 **/
fun Any?.ifNull(block: () -> Unit) = run {
    if (this == null) {
        block()
    }
}


/** Date Formatter
 * usage:
 *      val currentDate = Date().toStringFormat()
 *      val currentDate2 = Date().toStringFormat(format = "dd-MM-yyyy")
 *      val date = "2023-01-01".toDate(format = "yyyy-MM-dd")
 **/
fun String.toDate(format: String = "yyyy-MM-dd HH:mm:ss"): Date? {
    val dateFormatter = SimpleDateFormat(format, Locale.getDefault())
    return dateFormatter.parse(this)
}

fun Date.toStringFormat(format: String = "yyyy-MM-dd HH:mm:ss"): String {
    val dateFormatter = SimpleDateFormat(format, Locale.getDefault())
    return dateFormatter.format(this)
}
