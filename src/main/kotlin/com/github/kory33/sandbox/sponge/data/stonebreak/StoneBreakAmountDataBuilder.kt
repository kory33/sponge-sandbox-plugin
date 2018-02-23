package com.github.kory33.sandbox.sponge.data.stonebreak

import com.github.kory33.sandbox.sponge.KeyRepository
import org.spongepowered.api.data.DataHolder
import org.spongepowered.api.data.DataView
import org.spongepowered.api.data.manipulator.DataManipulatorBuilder
import org.spongepowered.api.data.persistence.AbstractDataBuilder
import org.spongepowered.api.data.persistence.DataContentUpdater
import java.util.*


private const val CONTENT_VERSION = 1

object StoneBreakAmountDataBuilder:
        AbstractDataBuilder<StoneBreakAmountData>(StoneBreakAmountData::class.java, CONTENT_VERSION),
        DataManipulatorBuilder<StoneBreakAmountData, ImmutableStoneBreakAmountData> {

    override fun buildContent(container: DataView): Optional<StoneBreakAmountData> = container
            .getObject(KeyRepository.breakAmount.query, Int::class.java)
            .map { StoneBreakAmountData(it) }

    override fun createFrom(dataHolder: DataHolder): Optional<StoneBreakAmountData> = create().fill(dataHolder)

    override fun create() = StoneBreakAmountData()

    object ContentUpdater: DataContentUpdater {

        override fun getInputVersion() = contentVersion

        override fun getOutputVersion() = contentVersion

        override fun update(content: DataView): DataView = content.set(KeyRepository.breakAmount, 0)

    }

    const val contentVersion = CONTENT_VERSION
}