package tiki.walletbaby.commands

import tiki.walletbaby.main.WalletBabyBot
import eu.vendeli.tgbot.api.message

class InvalidCommand(private val chatId: Long) : Command {
    override suspend fun excute() {
        message("command invalid").send(chatId, WalletBabyBot.instance())
    }

}