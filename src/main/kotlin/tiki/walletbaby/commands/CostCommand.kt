package tiki.walletbaby.commands

import tiki.walletbaby.data.CostRepository
import tiki.walletbaby.main.WalletBabyBot
import eu.vendeli.tgbot.api.message
import tiki.walletbaby.model.CostItem


class CostCommand(val costItem: CostItem) : Command {
    companion object {
        const val NAME = "/cost"
    }
    override suspend fun excute() {
        CostRepository.insertCostItem(costItem)
        val total = CostRepository.getTotalCost()
        message("record successfully, total cost: $total").send(costItem.chatId, WalletBabyBot.instance())
    }
}