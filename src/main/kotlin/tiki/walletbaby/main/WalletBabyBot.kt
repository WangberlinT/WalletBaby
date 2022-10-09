package tiki.walletbaby.main

import eu.vendeli.tgbot.TelegramBot
import java.io.File

object WalletBabyBot {
    val bot by lazy {
        val key = File("data/bot-key").readLines()[0]
        TelegramBot(
            key
        )
    }
    fun instance() = bot
}