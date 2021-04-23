package cn.learnice

import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.util.zip.ZipInputStream
import java.nio.charset.Charset
import java.util.*

object Code {
    private const val SS = "aHR0cDovL2lkZWEubWVkZW1pbmcuY29tL2EvamlodW9tYS56aXA="
    private const val FF = "5LmL5ZCO"
    private const val ERROR_MSG_404 = "Failed to get registration code 0x404 v_v"
    private const val ERROR_MSG_501 = "Failed to get registration code 0x501 v_v"
    private const val ERROR_MSG_502 = "Failed to get registration code 0x502 v_v"
    private const val SUCCESS_MSG = "The activation code is successfully obtained, please paste it into the IDE ^_^"

    fun acquireCode() {
        val url = URL(String(Base64.getDecoder().decode(SS)))
        val httpURLConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
        httpURLConnection.requestMethod = "GET"
        val inputStream: InputStream = httpURLConnection.inputStream
        val byteArray = inputStream.readBytes()
        assert(byteArray.isNotEmpty()) {
            ERROR_MSG_404
        }
        inputStream.close()
        val byteArrayInputStream = ByteArrayInputStream(byteArray)
        val gbk = Charset.forName("GBK")
        val zipInputStream = ZipInputStream(byteArrayInputStream, gbk)
        var zipEntry = zipInputStream.nextEntry
        while (zipEntry != null) {
            if (zipEntry.name.contains(String(Base64.getDecoder().decode(FF)))) {
                break
            }
            zipEntry = zipInputStream.nextEntry
        }
        assert(zipEntry != null) {
            ERROR_MSG_501
        }
        val code = String(zipInputStream.readBytes())
        assert(code.isNotEmpty()) {
            ERROR_MSG_502
        }
        val clipboard = Toolkit.getDefaultToolkit().systemClipboard
        val trans = StringSelection(code)
        clipboard.setContents(trans, null)
        zipInputStream.close()
        println(SUCCESS_MSG)
    }
}