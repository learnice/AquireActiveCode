package cn.learnice

import java.awt.Color
import java.awt.Desktop
import java.awt.Dimension
import java.awt.FlowLayout
import java.net.URI
import javax.swing.*

object Activity {
    private var jStatement: JLabel
    private var jOpenSource: JButton
    private val jButtonCopy: JButton
    private const val TITLE = "Acquire Active Code"
    private const val STATEMENT = "<html><body>" +
            "<p align=\"center\">声明：本软件仅仅用于开发学习，请勿用作商业用途</p>" +
            "<p align=\"center\">Disclaimer: This software is only for development learning, please do not use for commercial purposes</p>" +
            "<body></html>"
    private const val GITHUB = "https://github.com/learnice/AquireActiveCode"
    private val githubUrl = URI(GITHUB)
    private const val OPEN_SOURCE = "open source"
    private const val COPY_ACTION = "copy to clipboard"

    init {
        val jFrame = JFrame(TITLE)
        jStatement = JLabel()
        jStatement.text = STATEMENT
        val jStatementD = Dimension(600, 50)
        jStatement.preferredSize = jStatementD
        jStatement.horizontalAlignment = SwingConstants.CENTER
        jStatement.foreground = Color.red
        jOpenSource = JButton()
        jOpenSource.text = OPEN_SOURCE
        jOpenSource.preferredSize = Dimension(100, 50)
        jOpenSource.addActionListener {
            try {
                Desktop.getDesktop().browse(githubUrl)
            } catch (e: Exception) {
            }
        }
        jButtonCopy = JButton()
        jButtonCopy.text = COPY_ACTION
        val dimension = Dimension(200, 50)
        jButtonCopy.preferredSize = dimension
        jButtonCopy.addActionListener {
            Code.acquireCode()
        }
        val panel1 = JPanel(FlowLayout(FlowLayout.CENTER))
        panel1.add(jStatement)
        panel1.add(jButtonCopy)
        panel1.add(jOpenSource)
        jFrame.isResizable = false
        jFrame.setSize(600, 200)
        jFrame.setLocationRelativeTo(null)
        jFrame.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        jFrame.contentPane = panel1
        jFrame.isVisible = true
    }
}