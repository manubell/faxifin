package be.faxifin.androidtv.presentation.presenters

data class CardRow(
    val title: String?,
    val mShadow: Boolean = true,
    val cards: List<Card>,
    val type: Int = TYPE_DEFAULT
) {
    // Used to determine whether the row shall use shadows when displaying its cards or not.
    fun useShadow(): Boolean {
        return mShadow
    }

    companion object Type {
        // default is a list of cards
        const val TYPE_DEFAULT = 0

        // section header
        const val TYPE_SECTION_HEADER = 1

        // divider
        const val TYPE_DIVIDER = 2
    }
}