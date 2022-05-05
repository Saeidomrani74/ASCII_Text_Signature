package signature

import java.io.File

fun main() {
    print("Enter name and surname: ")
    val (firstName, lastName) = readln().split(" ")
    print("Enter person's status: ")
    val status = readln()
    drawer(firstName, lastName, status)
}

fun drawer(fn: String, ln: String, st: String) {

    val line = MutableList(15) { "" }

    for (c in fn) for (k in 1..10) line[k] += font(k, c)
    for (s in 1..10) line[s] += "          "
    for (c in ln) for (k in 1..10) line[k] += font(k, c)

    for (c in st) for (k in 11..13) if (c != ' ') line[k] += font(k, c)
    else line[k] += "     "

    if (line[1].length > line[11].length) {
        var space = ""
        repeat(((line[1].length) - line[11].length) / 2) {
            space += " "
        }
        for (s in 11..13) line[s] = "$space${line[s]}$space"
        val add = (line[1].length - line[11].length) % 2 == 1
        if (add) for (s in 11..13) line[s] += " "
    } else {
        var space = ""
        repeat((line[11].length - line[1].length) / 2) {
            space += " "
        }
        for (s in 1..10) line[s] = "$space${line[s]}$space"
        val add = (line[11].length - line[1].length) % 2 == 1
        if (add) for (s in 1..10) line[s] += " "
    }

    for (s in 1..13) line[s] = "88  ${line[s]}  88"

    repeat(line[1].length) {
        line[0] += "8"
        line[14] += "8"
    }

    for (f in line.indices) println(line[f])

}

fun font(line: Int, char: Char): String {

    val romanFont = "src\\roman.txt"
    val romans = File(romanFont).readLines()
    val mediumFont = "src\\medium.txt"
    val mediums = File(mediumFont).readLines()
    val (sizeR, lettersR) = romans[0].split(" ")
    val (sizeM, lettersM) = mediums[0].split(" ")
    val sizeRI = sizeR.toInt()
    val sizeMI = sizeM.toInt()
    val lettersRI = lettersR.toInt()
    val lettersMI = lettersM.toInt()

    val rLetters = MutableList(lettersRI + 1) { MutableList(sizeRI + 1) { "" } }
    for (z in romans.indices) {
        if (z > 0) {
            val let = ((z - 1) / (sizeRI + 1)) + 1
            val cat = (z - 1) % (sizeRI + 1)
            rLetters[let][cat] = romans[z]
        }
    }

    val mLetters = MutableList(lettersMI + 1) { MutableList(sizeMI + 1) { "" } }
    for (z in mediums.indices) {
        if (z > 0) {
            val let = ((z - 1) / (sizeMI + 1)) + 1
            val cat = (z - 1) % (sizeMI + 1)
            mLetters[let][cat] = mediums[z]
        }
    }

    val isBIG = char.code < 91
    val word = if (isBIG) char.code - 38 else char.code - 96

    return if (line < 11) rLetters[word][line] else mLetters[word][line - 10]

}