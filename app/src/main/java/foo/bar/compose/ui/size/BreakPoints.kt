package foo.bar.compose.ui.size

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import co.early.fore.core.WorkMode
import co.early.fore.kt.core.delegate.Delegate
import co.early.fore.kt.core.delegate.Fore
import co.early.fore.kt.core.delegate.ReleaseDelegateDefault

/**
 *
 * Not recommended, but if you wanted to match the break points used in
 * https://developer.android.com/guide/topics/large-screens/support-different-screen-sizes
 * you can call:
 *
 * BreakPoints.overrideBreakPoints(
 *   ViewPortBreakPoints(
 *     widthXSDpBelow = 600.dp,
 *     widthSDpBelow = 600.dp,
 *     widthMDpBelow = 840.dp,
 *     widthLDpBelow = Float.MAX_VALUE.dp,
 *     heightXSDpBelow = 480.dp,
 *     heightSDpBelow = 480.dp,
 *     heightMDpBelow = 900.dp,
 *     heightLDpBelow = Float.MAX_VALUE.dp,
 *   )
 * )
 *
 * where
 * XS = Compact
 * M = Medium
 * L = Expanded
 *
 * Then create your values with the 3 parameter constructor e.g.:
 * WidthBasedValue(xs = 2.dp, m = 5.dp, l = 10.dp)
 *
 * It's not recommended, because it will mean you won't be able to distinguish
 * between any screens less than 600dp or more than 840dp wide. If you leave
 * the break points at their default values, it doesn't mean that you _have_ to
 * distinguish more screen sizes in your design (you can just use the two
 * value constructors, for instance: WidthBasedValue(s = 5.dp, l = 10.dp), just
 * that you _can_ if you want to in the future for any specific layout.
 *
 */
class BreakPoints {
    companion object {

        var breakPoints: ViewPortBreakPoints = ViewPortBreakPoints()
            private set

        fun overrideBreakPoints(breakPoints: ViewPortBreakPoints){
            this.breakPoints = breakPoints
        }
    }
}

/**
 * These constants let us directly reference the midway breakpoint
 * values in Preview annotations. Example:
 *
 * @Composable
 * fun PreviewWithWindowSize(content: @Composable (size: WindowSize) -> Unit) {
 *   ComposeTheme {
 *     BoxWithConstraints {
 *       content(DpSize(maxWidth, maxHeight).toWindowSize())
 *     }
 *   }
 * }
 *
 * @Preview(showBackground = true, widthDp = wXSDp, heightDp = hXSDp)
 * @Preview(showBackground = true, widthDp = wSDp, heightDp = hSDp)
 * @Preview(showBackground = true, widthDp = wMDp, heightDp = hMDp)
 * @Preview(showBackground = true, widthDp = wLDp, heightDp = hLDp)
 * @Preview(showBackground = true, widthDp = wXLDp, heightDp = hXLDp)
 * @Preview(showBackground = true, widthDp = wLDp, heightDp = hXSDp)
 * @Preview(showBackground = true, widthDp = wXSDp, heightDp = hMDp)
 * @Composable
 * fun ViewsTestSmall() {
 *   PreviewWithWindowSize {
 *     MyView(size = it)
 *   }
 * }
 *
 * Obviously if you override the break points, you will need to add your
 * own constants to use in your Preview annotations.
 */
const val wXS_low = 150
const val wXS_high = 249
const val wS_low = 250
const val wS_high = 399
const val wM_low = 400
const val wM_high = 499
const val wL_low = 500
const val wL_high = 899
const val wXL_low = 900
const val wXL_high = 1900

const val hXS_low = 150
const val hXS_high = 249
const val hS_low = 250
const val hS_high = 699
const val hM_low = 700
const val hM_high = 899
const val hL_low = 900
const val hL_high = 1279
const val hXL_low = 1280
const val hXL_high = 2000

class ViewPortBreakPoints(
    val widthXSDpBelow: Dp = wS_low.dp,
    val widthSDpBelow: Dp = wM_low.dp,
    val widthMDpBelow: Dp = wL_low.dp,
    val widthLDpBelow: Dp = wXL_low.dp,
    val widthXLDpBelow: Dp = Int.MAX_VALUE.dp,

    val heightXSDpBelow: Dp = hS_low.dp,
    val heightSDpBelow: Dp = hM_low.dp,
    val heightMDpBelow: Dp = hL_low.dp,
    val heightLDpBelow: Dp = hXL_low.dp,
    val heightXLDpBelow: Dp = Int.MAX_VALUE.dp,

    val minDimXSDpBelow: Dp = widthXSDpBelow,
    val minDimSDpBelow: Dp = widthSDpBelow,
    val minDimMDpBelow: Dp = widthMDpBelow,
    val minDimLDpBelow: Dp = widthLDpBelow,
    val minDimXLDpBelow: Dp = widthXLDpBelow,

    val consideredSquarishBelowRatio: Float = 1.10f,
)
