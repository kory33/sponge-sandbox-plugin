package com.github.kory33.sandbox.sponge

import com.github.kory33.sandbox.sponge.data.stonebreak.ImmutableStoneBreakAmountData
import com.github.kory33.sandbox.sponge.data.stonebreak.StoneBreakAmountData
import com.github.kory33.sandbox.sponge.data.stonebreak.StoneBreakAmountDataBuilder
import org.spongepowered.api.Game
import org.spongepowered.api.data.DataRegistration
import org.spongepowered.api.entity.living.player.Player
import org.spongepowered.api.event.Listener
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
        DataRegistration.builder()
                .dataClass(StoneBreakAmountData::class.java)
                .immutableClass(ImmutableStoneBreakAmountData::class.java)
                .builder(StoneBreakAmountDataBuilder())
                .manipulatorId("break-amount")
                .dataName("Break Amount")
                .buildAndRegister(container)
    }

    @Listener
    fun onStoneBreak(event: ChangeBlockEvent.Break) {
        val causePlayer = event.cause.mapNotNull { it as? Player }.firstOrNull() ?: return

        causePlayer.getOrCreate(StoneBreakAmountData::class.java).map { it + 1 }.ifPresent {
            causePlayer.offer(it)
            println(it.amount)
        }
    }

    companion object {
        const val ID = "spongesandboxplugin"
    }
}