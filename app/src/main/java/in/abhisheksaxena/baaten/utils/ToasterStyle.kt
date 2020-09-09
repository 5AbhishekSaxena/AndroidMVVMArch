package `in`.abhisheksaxena.baaten.utils


/**
 * @author Abhishek Saxena
 * @since 09-09-2020 10:57
 */

data class ToasterStyle(
    var message: String,
    var type: Type,
    var drawableRes:Int?
){
    enum class Type{
        DEFAULT,
        DEFAULT_WITH_IMG,
        SUCCESS,
        WARNING,
        ERROR,
        CUSTOM
    }
}