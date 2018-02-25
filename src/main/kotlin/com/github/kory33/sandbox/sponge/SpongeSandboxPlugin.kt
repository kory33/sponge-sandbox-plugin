package com.github.kory33.sandbox.sponge

import com.github.kory33.sandbox.sponge.data.stonebreak.ImmutableStoneBreakAmountData
import com.github.kory33.sandbox.sponge.data.stonebreak.StoneBreakAmountData
import com.github.kory33.sandbox.sponge.data.stonebreak.StoneBreakAmountDataBuilder
import org.spongepowered.api.Game
import org.spongepowered.api.Sponge
import org.spongepowered.api.block.BlockSnapshot
import org.spongepowered.api.block.BlockType
import org.spongepowered.api.block.BlockTypes
import org.spongepowered.api.data.DataRegistration
import org.spongepowered.api.entity.living.player.Player
import org.spongepowered.api.event.Listener
import org.spongepowered.api.event.Order
import org.spongepowered.api.event.block.ChangeBlockEvent
import org.spongepowered.api.event.game.state.GameInitializationEvent
import org.spongepowered.api.plugin.Plugin
import org.spongepowered.api.plugin.PluginContainer
import javax.inject.Inject

@Plugin(id=SpongeSandboxPlugin.ID, name="Sponge Sandbox Plugin")
class SpongeSandboxPlugin {

    @Inject lateinit var container: PluginContainer
    @Inject lateinit var game: Game

    @Listener
    fun onInit(event: GameInitializationEvent) {
        val dataManager = Sponge.getDataManager()

        dataManager.registerContentUpdater(StoneBreakAmountData::class.java, StoneBreakAmountDataBuilder.ContentUpdater)

        DataRegistration.builder()
                .dataClass(StoneBreakAmountData::class.java)
                .immutableClass(ImmutableStoneBreakAmountData::class.java)
                .builder(StoneBreakAmountDataBuilder)
                .manipulatorId("{break-amount|")
                .dataName("Break")
                .buildAndRegister(container)
    }

    @Listener(order=Order.POST)
    fun onStoneBreak(event: ChangeBlockEvent.Break) {
        val causePlayer = event.cause.mapNotNull { it as? Player }.firstOrNull() ?: return

        val brokeStoneNumber = event.transactions
                .filter { it.isValid && it.original.state.type == BlockTypes.STONE }
                .size

        causePlayer.getOrCreate(StoneBreakAmountData::class.java)
                .map { it + brokeStoneNumber }
                .ifPresent {
                    causePlayer.offer(it)
                    println(it.amount)
                }
    }

    companion object {
        const val ID = "spongesandboxplugin"
    }
}