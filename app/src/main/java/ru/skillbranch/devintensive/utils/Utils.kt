package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?>{
        val parts : List<String?>? = fullName?.trim()?.split(" ")

        var firstName: String? = parts?.getOrNull(0)
        var lastName: String? = parts?.getOrNull(1)

        if(firstName == "")
            firstName = null
        if(lastName == "")
            lastName = null

        //return Pair(firstName, lastName)
        return firstName to lastName
    }

    fun transliteration(payload: String, divider: String = " "): String {
        val input = mutableMapOf<Char, String>()
        input['А'] = "A"
        input['а'] = "a"
        input['Б'] = "B"
        input['б'] = "b"
        input['В'] = "V"
        input['в'] = "v"
        input['Г'] = "G"
        input['г'] = "g"
        input['Д'] = "D"
        input['д'] = "d"
        input['Е'] = "E"
        input['е'] = "e"
        input['Ё'] = "E"
        input['ё'] = "e"
        input['Ж'] = "Zh"
        input['ж'] = "zh"
        input['З'] = "Z"
        input['з'] = "z"
        input['И'] = "I"
        input['и'] = "i"
        input['Й'] = "I"
        input['й'] = "i"
        input['К'] = "K"
        input['к'] = "k"
        input['Л'] = "L"
        input['л'] = "l"
        input['М'] = "M"
        input['м'] = "m"
        input['Н'] = "N"
        input['н'] = "n"
        input['О'] = "O"
        input['о'] = "o"
        input['П'] = "P"
        input['п'] = "p"
        input['Р'] = "R"
        input['р'] = "r"
        input['С'] = "S"
        input['с'] = "s"
        input['Т'] = "T"
        input['т'] = "t"
        input['У'] = "U"
        input['у'] = "u"
        input['Ф'] = "F"
        input['ф'] = "f"
        input['Х'] = "H"
        input['х'] = "h"
        input['Ц'] = "C"
        input['ц'] = "c"
        input['Ч'] = "Ch"
        input['ч'] = "ch"
        input['Ш'] = "Sh"
        input['ш'] = "sh"
        input['Щ'] = "Sh'"
        input['щ'] = "sh'"
        input['Ъ'] = ""
        input['ъ'] = ""
        input['Ы'] = "I"
        input['ы'] = "i"
        input['Ь'] = ""
        input['ь'] = ""
        input['Э'] = "E"
        input['э'] = "e"
        input['Ю'] = "Yu"
        input['ю'] = "yu"
        input['Я'] = "Ya"
        input['я'] = "ya"

        val regexSpace = """( +)""".toRegex()
        var ret = regexSpace.replace(payload, divider)

        for (s in ret) {
            if(s in input.keys)
                ret = ret.replace(s.toString(), input.getValue(s))
        }

        return ret
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        var ret: String? = null

        val firstN = firstName?.trim()?.getOrNull(0)
        val lastN = lastName?.trim()?.getOrNull(0)

        if(firstN != null && firstN.isLetter()) {
            ret = firstN.toString().toUpperCase()
        }
        if(lastN != null && lastN.isLetter()) {
            if(ret != null)
                ret += lastN.toString().toUpperCase()
            else
                ret = lastN.toString().toUpperCase()
        }

        return ret
    }

}