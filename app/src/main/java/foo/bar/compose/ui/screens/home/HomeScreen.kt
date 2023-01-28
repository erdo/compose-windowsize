package foo.bar.compose.ui.screens.home

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.*
import co.early.fore.kt.core.delegate.Fore
import co.early.fore.kt.core.ui.observeAsState
import foo.bar.compose.OG
import foo.bar.compose.R
import foo.bar.compose.feature.counter.CounterModel
import foo.bar.compose.feature.counter.CounterState
import foo.bar.compose.ui.screens.common.toLabel
import foo.bar.compose.ui.size.*

@Composable
fun HomeScreen(
    size: WindowSize = WindowSize(),
    counterModel: CounterModel = OG[CounterModel::class.java],
) {

    Fore.getLogger().i("HomeScreen")

    val counterState by counterModel.observeAsState { counterModel.state }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CounterView(
            size = size,
            counterState = counterState,
            increaseCallback = { counterModel.increase() },
            decreaseCallback = { counterModel.decrease() },
        )
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        DiagnosticInfo(size)
    }
}

@Composable
fun CounterView(
    size: WindowSize,
    counterState: CounterState,
    increaseCallback: () -> Unit = {},
    decreaseCallback: () -> Unit = {},
) {

    Fore.getLogger().i("CounterView")

    val minimumDimension = size.dpSize.minimumDimension()
    val borderThickness = minimumDimension * 0.10f
    val boxHeight = minimumDimension * 0.50f
    val numberFontSize = (minimumDimension / 5f).value.sp
    val buttonFontSize = (minimumDimension / 8f).value.sp
    val buttonSize = max(borderThickness * 3, 50.dp)
    val color = WidthBasedValue(
        xs = Color.Red,
        s = Color.Green,
        m = Color.Blue,
        l = Color.Magenta,
        xl = Color.Gray
    )
    val shape = AspectBasedValue(
        port = CircleShape,
        land = CircleShape,
        squarish = RectangleShape
    )

    Box(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(boxHeight)
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = borderThickness, end = borderThickness)
                .border(width = borderThickness, color = color(size), shape = shape(size)),
        )

        Box(modifier = Modifier.fillMaxSize()) {

            CustomButton(
                Modifier.align(Alignment.CenterStart),
                R.string.decrease,
                counterState.canDecrease(),
                buttonSize,
                buttonFontSize,
                decreaseCallback,
            )

            if (counterState.loading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center).size(borderThickness),
                )
            } else {
                Text(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(borderThickness / 2),
                    text = counterState.amount.toString(),
                    style = TextStyle(fontSize = numberFontSize)
                )
            }

            CustomButton(
                Modifier.align(Alignment.CenterEnd),
                R.string.increase,
                counterState.canIncrease(),
                buttonSize,
                buttonFontSize,
                increaseCallback,
            )
        }
    }
}

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    @StringRes labelResId: Int,
    enabled: Boolean,
    buttonSize: Dp,
    buttonFontSize: TextUnit,
    callback: () -> Unit = {}
) {

    Fore.getLogger().i("CustomButton")

    Button(
        modifier = modifier.size(buttonSize),
        onClick = { callback() },
        enabled = enabled
    ) {
        Text(
            text = stringResource(id = labelResId),
            style = TextStyle(fontSize = buttonFontSize)
        )
    }
}

@Composable
fun BoxScope.DiagnosticInfo(size: WindowSize) {
    WidthBasedComposable(
        xs = { sz -> MiniDiagnostics(sz) },
        m = { sz -> MiniDiagnostics(sz) },
        l = { sz -> RegularDiagnostics(sz) },
    )(size)
}

@Composable
fun BoxScope.RegularDiagnostics(size: WindowSize) {

    Fore.getLogger().i("RegularDiagnostics ${size.toLabel()}")

    val diagnosticsFontSize = (size.dpSize.width / 60f).value.sp

    Text(
        modifier = Modifier
            .align(Alignment.BottomStart)
            .background(color = Color.Yellow),
        text = size.toLabel(true),
        style = TextStyle(color = Color.Red, fontSize = diagnosticsFontSize)
    )
}

@Composable
fun BoxScope.MiniDiagnostics(size: WindowSize) {

    Fore.getLogger().i("MiniDiagnostics ${size.toLabel()}")

    val diagnosticsFontSize = (size.dpSize.width / 30f).value.sp

    Text(
        modifier = Modifier
            .align(Alignment.BottomStart)
            .background(color = Color.Yellow),
        text = size.toLabel(),
        style = TextStyle(color = Color.Red, fontSize = diagnosticsFontSize)
    )
}
