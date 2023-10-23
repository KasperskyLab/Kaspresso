data class View(val resourceId: String, val viewType: String, val packages: String) {

    fun toKaspressoExpression(): String {
        return "val ${parseElementName(resourceId)} = K$viewType{ withId(R.id.$resourceId) }"
    }
}
