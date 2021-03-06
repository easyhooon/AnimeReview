package com.kenshi.animereview.common

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.CheckResult
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

//ViewExt
fun View.showKeyboard(requestFocus: Boolean = false) {
    if (requestFocus) this.requestFocus()
    (this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).showSoftInput(
        this,
        InputMethodManager.SHOW_IMPLICIT)
}

fun View.hideKeyboard(clearFocus: Boolean = false) {
    if (clearFocus) this.clearFocus()
    (this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
        this.windowToken,
        InputMethodManager.HIDE_NOT_ALWAYS)
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

//Float type 의 DP -> Px
fun Float.fromDpToPx(): Int {
    return (this * Resources.getSystem().displayMetrics.density).toInt()
}

fun Context.dpToPx(dp: Float): Int =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics).toInt()

inline fun Fragment.launchAndRepeatWithViewLifecycle(
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    crossinline block: suspend CoroutineScope.() -> Unit,
) {
    viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.lifecycle.repeatOnLifecycle(minActiveState) {
            block()
        }
    }
}

// 계속 반복되는 함수이므로 재사용하기 위한 모듈화
fun <T> Fragment.collectLatestLifecycleFlow(flow: Flow<T>, collect: suspend (T) -> Unit) {
    viewLifecycleOwner.lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collectLatest(collect)
        }
    }
}

fun LifecycleOwner.repeatOnStarted(block: suspend CoroutineScope.() -> Unit) {
    lifecycleScope.launch {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED, block)
    }
}

//// 계속 반복되는 함수이므로 재사용하기 위한 모듈화
//fun <T> Fragment.collectLifecycleFlow(flow: Flow<T>, collect: suspend (T) -> Unit) {
//    viewLifecycleOwner.lifecycleScope.launch {
//        repeatOnLifecycle(Lifecycle.State.STARTED) {
//            flow.collect(collect)
//        }
//    }
//}

//fun <T> Activity.collectLatestLifecycleFlow(flow: Flow<T>, collect: suspend (T) -> Unit) {
//    this.lifecycleScope.launch {
//        repeatOnLifecycle(Lifecycle.State.STARTED) {
//            flow.collectLatest(collect)
//        }
//    }
//}

fun NavController.safeNavigate(direction: NavDirections) {
    currentDestination?.getAction(direction.actionId)?.run { navigate(direction) }
}

//fun NavController.safeNavigate(
//    @IdRes currentDestinationId: Int,
//    @IdRes id: Int,
//    args: Bundle? = null
//) {
//    if (currentDestinationId == currentDestination?.id) {
//        navigate(id, args)
//    }
//}


fun EditText.textChangesToFlow(): Flow<CharSequence?> {
    return callbackFlow {
        val listener = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) = Unit
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) =
                Unit

            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
                //offer(text)
                // 값 내보내기
                trySend(text)
            }
        }
        addTextChangedListener(listener)
        // 콜백이 사라질때 실행, 리스너 제거
        awaitClose { removeTextChangedListener(listener) }
    }.onStart {
        // event 방출
        emit(text)
    }
}

