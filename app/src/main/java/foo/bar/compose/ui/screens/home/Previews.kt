package foo.bar.compose.ui.screens.home

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import foo.bar.compose.feature.counter.CounterState
import foo.bar.compose.ui.size.*
import foo.bar.compose.ui.theme.ComposeTheme

@Composable
fun PreviewWithWindowSize(content: @Composable (size: WindowSize) -> Unit) {
    ComposeTheme {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            content(DpSize(maxWidth, maxHeight).toWindowSize())
        }
    }
}

@Preview(showBackground = true, widthDp = wXS_low, heightDp = hXS_low)
@Preview(showBackground = true, widthDp = wXS_high, heightDp = hXS_high)
@Preview(showBackground = true, widthDp = wS_low, heightDp = hS_low)
@Preview(showBackground = true, widthDp = wS_high, heightDp = hS_high)
@Preview(showBackground = true, widthDp = wM_low, heightDp = hM_low)
@Preview(showBackground = true, widthDp = wM_high, heightDp = hM_high)
@Preview(showBackground = true, widthDp = wL_low, heightDp = hL_low)
@Preview(showBackground = true, widthDp = wL_high, heightDp = hL_high)
@Preview(showBackground = true, widthDp = wXL_low, heightDp = hXL_low)
@Preview(showBackground = true, widthDp = wXL_high, heightDp = hXL_high)
@Preview(showBackground = true, widthDp = wL_high, heightDp = hXS_low)
@Preview(showBackground = true, widthDp = wXS_low, heightDp = hM_high)
@Composable
fun MyPreviews() {
    PreviewWithWindowSize {
        CounterView(size = it, counterState = CounterState(3))
    }
}
