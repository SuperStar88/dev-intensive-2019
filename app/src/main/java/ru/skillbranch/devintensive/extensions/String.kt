package ru.skillbranch.devintensive.extensions

fun String.truncate(value: Int = 16): String {

    var ret = this.trim()

    if(ret.length > value) {
        ret = ret.substring(0, value)
        ret = ret.trim() + "..."
    }

    return ret
}

fun String.stripHtml(): String {

    var ret = this

    val regexTag = """(<.*?>)""".toRegex()
    ret = regexTag.replace(ret, "")

    val regexEscape = """(&amp;|&lt;|&gt;|&#39;|&quot;)""".toRegex()
    ret = regexEscape.replace(ret, "")

    val regexSpace = """( +)""".toRegex()
    ret = regexSpace.replace(ret, " ")

    return ret
}