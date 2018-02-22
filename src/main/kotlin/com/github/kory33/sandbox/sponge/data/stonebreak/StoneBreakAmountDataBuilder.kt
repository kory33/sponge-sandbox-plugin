package com.github.kory33.sandbox.sponge.data.stonebreak

import com.github.kory33.sandbox.sponge.KeyRepository
import org.spongepowered.api.data.DataHolder
import org.spongepowered.api.data.DataView
import org.spongepowered.api.data.manipulator.DataManipulatorBuilder
import org.spongepowered.api.data.persistence.AbstractDataBuilder
import java.util.*

class StoneBreakAmountDataBuilder:
        AbstractDataBuilder<StoneBreakAmountData>(StoneBreakAmountData::class.java, CONTENT_VERSION),
        DataManipulatorBuilder<StoneBreakAmountData, ImmutableStoneBreakAmountData> {

    override fun buildContent(container: DataView): Optional<StoneBreakAmountData> = container
            .getObject(KeyRepository.breakAmount.query, Int::class.java)
            .map { StoneBreakAmountData(it) }

    override fun createFrom(dataHolder: DataHolder): Optional<StoneBreakAmountData> = create().fill(dataHolder)

    override fun create() = StoneBreakAmountData()

    companion object {
        const val CONTENT_VERSION = 1
    }
}