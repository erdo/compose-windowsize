package foo.bar.compose.ui.size

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.*

data class WindowSize(
    val width: Width = Width.Medium,
    val height: Height = Height.Medium,
    val minDim: MinDim = MinDim.Medium,
    val aspect: Aspect = Aspect.Port,
    val dpSize: DpSize = DpSize(wM_low.dp, hM_low.dp),
    val isRound: Boolean = false
)

sealed class Width(val smallerThanDp: Dp) : Comparable<Width> {

    /**
     * Typically indicates the width of a watch display
     */
    object XSmall : Width(smallerThanDp = BreakPoints.breakPoints.widthXSDpBelow)

    /**
     * Typically indicates the width of a small / normal phone display (like a Nexus One) in portrait
     */
    object Small : Width(smallerThanDp = BreakPoints.breakPoints.widthSDpBelow)

    /**
     * Typically indicates the width of a large phone display (like a Pixel XL) in portrait
     */
    object Medium : Width(smallerThanDp = BreakPoints.breakPoints.widthMDpBelow)

    /**
     * Typically indicates the width of a tablet display (like a Pixel C) in portrait
     */
    object Large : Width(smallerThanDp = BreakPoints.breakPoints.widthLDpBelow)

    /**
     * Typically indicates the width of a wall display or large desktop screen in portrait
     */
    object XLarge : Width(smallerThanDp = BreakPoints.breakPoints.widthXLDpBelow)

    override operator fun compareTo(other: Width) = smallerThanDp.compareTo(other.smallerThanDp)

    companion object {
        @Composable
        fun width(window: DpSize): Width {
            val widthDp = window.width
            return when {
                widthDp < XSmall.smallerThanDp -> XSmall
                widthDp < Small.smallerThanDp -> Small
                widthDp < Medium.smallerThanDp -> Medium
                widthDp < Large.smallerThanDp -> Large
                else -> XLarge
            }
        }
    }
}

sealed class Height(val smallerThanDp: Dp) : Comparable<Height> {

    /**
     * Typically indicates the height of a watch display
     */
    object XSmall : Height(smallerThanDp = BreakPoints.breakPoints.heightXSDpBelow)

    /**
     * Typically indicates the height of a small / normal phone display (like a Nexus One) in portrait
     */
    object Small : Height(smallerThanDp = BreakPoints.breakPoints.heightSDpBelow)

    /**
     * Typically indicates the height of a normal phone display (like a Pixel XL) in portrait
     */
    object Medium : Height(smallerThanDp = BreakPoints.breakPoints.heightMDpBelow)

    /**
     * Typically indicates the height of a tablet display (like a Pixel C) in portrait
     */
    object Large : Height(smallerThanDp = BreakPoints.breakPoints.heightLDpBelow)

    /**
     * Typically indicates the height of a wall display or large desktop screen in portrait
     */
    object XLarge : Height(smallerThanDp = BreakPoints.breakPoints.heightXLDpBelow)

    override operator fun compareTo(other: Height) = smallerThanDp.compareTo(other.smallerThanDp)

    companion object {
        @Composable
        fun height(window: DpSize): Height {
            val heightDp = window.height
            return when {
                heightDp < XSmall.smallerThanDp -> XSmall
                heightDp < Small.smallerThanDp -> Small
                heightDp < Medium.smallerThanDp -> Medium
                heightDp < Large.smallerThanDp -> Large
                else -> XLarge
            }
        }
    }
}

sealed class MinDim(val smallerThanDp: Dp) : Comparable<MinDim> {

    /**
     * Typically indicates a watch display, but could be a narrow & long display of any type
     */
    object XSmall : MinDim(smallerThanDp = BreakPoints.breakPoints.minDimXSDpBelow)

    /**
     * Typically indicates the a small / normal phone display (like a Nexus One) regardless of
     * orientation
     */
    object Small : MinDim(smallerThanDp = BreakPoints.breakPoints.minDimSDpBelow)

    /**
     * Typically indicates a large phone display (like a Pixel XL) regardless of orientation, but
     * could also be a folded foldable in any orientation
     */
    object Medium : MinDim(smallerThanDp = BreakPoints.breakPoints.minDimMDpBelow)

    /**
     * Typically indicates a tablet display (like a Pixel C) regardless of orientation
     */
    object Large : MinDim(smallerThanDp = BreakPoints.breakPoints.minDimLDpBelow)

    /**
     * Typically indicates a large display such as a wall display or large desktop screen
     * regardless of orientation
     */
    object XLarge : MinDim(smallerThanDp = BreakPoints.breakPoints.minDimXLDpBelow)

    override operator fun compareTo(other: MinDim) = smallerThanDp.compareTo(other.smallerThanDp)

    companion object {
        @Composable
        fun minDim(window: DpSize): MinDim {

            val widthDp = window.width
            val heightDp = window.height
            val minDim = min(widthDp, heightDp)

            return when {
                minDim < XSmall.smallerThanDp -> XSmall
                minDim < Small.smallerThanDp -> Small
                minDim < Medium.smallerThanDp -> Medium
                minDim < Large.smallerThanDp -> Large
                else -> XLarge
            }
        }
    }
}

sealed class Aspect {

    object Land : Aspect()
    object Port : Aspect()
    object Squarish : Aspect()

    companion object {
        @Composable
        fun aspect(window: DpSize, includeAlmostSquare: Boolean = true): Aspect {

            val widthDp = window.width
            val heightDp = window.height
            val ratio = max(widthDp, heightDp).div(min(widthDp, heightDp))

            return when {
                (includeAlmostSquare && ratio < BreakPoints.breakPoints.consideredSquarishBelowRatio) || widthDp == heightDp
                -> Squarish
                heightDp > widthDp -> Port
                else -> Land
            }
        }
    }
}
