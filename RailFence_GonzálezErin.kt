import javax.swing.JOptionPane

fun encryptRailFence(text: String, key: Int): String {
    if (key <= 1) return text

    val rail = Array(key) { StringBuilder() }
    var directionDown = false
    var row = 0

    for (char in text) {
        rail[row].append(char)
        if (row == 0 || row == key - 1) directionDown = !directionDown
        row += if (directionDown) 1 else -1
    }

    return rail.joinToString("") { it.toString() }
}

fun decryptRailFence(text: String, key: Int): String {
    if (key <= 1) return text

    val rail = Array(key) { CharArray(text.length) { '\u0000' } }
    var directionDown = false
    var row = 0

    for (i in text.indices) {
        rail[row][i] = '*'
        if (row == 0 || row == key - 1) directionDown = !directionDown
        row += if (directionDown) 1 else -1
    }

    var index = 0
    for (i in 0 until key) {
        for (j in text.indices) {
            if (rail[i][j] == '*' && index < text.length) {
                rail[i][j] = text[index++]
            }
        }
    }

    val result = StringBuilder()
    row = 0
    directionDown = false
    for (i in text.indices) {
        result.append(rail[row][i])
        if (row == 0 || row == key - 1) directionDown = !directionDown
        row += if (directionDown) 1 else -1
    }

    return result.toString()
}

fun main() {
    val options = arrayOf("Cifrar", "Descifrar")
    val choice = JOptionPane.showOptionDialog(null, "Elige una opción:", "Rail Fence Cipher",
        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0])

    val message = JOptionPane.showInputDialog("Introduce el mensaje:")
    val key = JOptionPane.showInputDialog("Introduce la clave (número de rieles):").toInt()

    if (choice == 0) {
        val encryptedText = encryptRailFence(message, key)
        JOptionPane.showMessageDialog(null, "Texto cifrado: $encryptedText")

        val decryptOption = JOptionPane.showConfirmDialog(null, "¿Deseas descifrar el mensaje?", "Descifrar", JOptionPane.YES_NO_OPTION)
        if (decryptOption == JOptionPane.YES_OPTION) {
            val decryptionKey = JOptionPane.showInputDialog("Introduce la clave para descifrar:").toInt()
            val decryptedText = decryptRailFence(encryptedText, decryptionKey)
            JOptionPane.showMessageDialog(null, "Texto descifrado: $decryptedText")
        }
    } else {
        val decryptedText = decryptRailFence(message, key)
        JOptionPane.showMessageDialog(null, "Texto descifrado: $decryptedText")
    }
}
