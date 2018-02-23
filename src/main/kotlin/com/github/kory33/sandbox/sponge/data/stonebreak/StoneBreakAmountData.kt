package com.github.kory33.sandbox.sponge.data.stonebreak

import com.github.kory33.sandbox.sponge.KeyRepository
import org.spongepowered.api.Sponge
import org.spongepowered.api.data.DataContainer
import org.spongepowered.api.data.DataHolder
import org.spongepowered.api.data.manipulator.mutable.common.AbstractSingleData
import org.spongepowered.api.data.merge.MergeFunction
import org.spongepowered.api.data.value.mutable.Value
import java.util.*

class StoneBreakAmountData(val amount: Int):
        AbstractSingleData<Int, StoneBreakAmountData, ImmutableStoneBreakAmountData>(amount, KeyRepository.breakAmount) {

    constructor(): this(0)

    operator fun plus(amount: Int): StoneBreakAmountData = StoneBreakAmountData(super.getValue() + amount)

    override fun from(container: DataContainer): Optional<StoneBreakAmountData> = container
            .getObject(KeyRepository.breakAmount.query, Int::class.java)
            .map { setValue(it) }

    override fun copy() = StoneBreakAmountData(amount)

    override fun fill(dataHolder: DataHolder, overlap: MergeFunction): Optional<StoneBreakAmountData> {
        val merged = overlap.merge(this, dataHolder.get(StoneBreakAmountData::class.java).orElse(null))

        return setValue(merged.value).let { Optional.of(it) }
    }

    override fun getContentVersion() = StoneBreakAmountDataBuilder.CONTENT_VERSION

    override fun asImmutable() = ImmutableStoneBreakAmountData(amount)

    override fun getValueGetter(): Value<*> = Sponge.getRegistry().valueFactory
            .createValue(KeyRepository.breakAmount, amount)

}