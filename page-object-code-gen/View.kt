data class View(val resourceId: String, val viewType: String, val packages: String) {

    fun toKaspressoExpression(): String {
        return "val ${resourceId.parseElementName()} = K$viewType{ withId(R.id.$resourceId) }"
    }

    fun String.parseElementName(): String {
        return replace(Regex("_[a-z]")){
            it.value.last().uppercase()
        }
    }
}
