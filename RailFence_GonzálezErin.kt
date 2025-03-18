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

fun main() {
    val message = JOptionPane.showInputDialog("Introduce el mensaje a cifrar: ")
    val key = JOptionPane.showInputDialog("Introduce la clave (número de rieles): ").toInt()

    val encryptedText = encryptRailFence(message, key)
    JOptionPane.showMessageDialog(null, "Texto cifrado: $encryptedText")
}