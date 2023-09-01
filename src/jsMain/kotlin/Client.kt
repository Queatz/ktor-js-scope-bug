import androidx.compose.runtime.*
import kotlinx.coroutines.delay
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.renderComposableInBody

@Composable
fun SomeComponent(value: Int, onValue: (Int) -> Unit) {

    // Changing `Unit` to `value` causes `value` to be correct when accessed inside the LaunchedEffect,
    // however cancelling the coroutine is not the desired effect.
    LaunchedEffect(Unit) {
        while (true) {
            onValue(value + 1)
            delay(1000)
        }
    }

    Div {
        Text("$value")
    }

}

fun main() {
    renderComposableInBody {
        var value by remember {
            mutableStateOf(0)
        }

        SomeComponent(value) { value = it }
    }
}
