package com.github.kory33.sandbox.sponge.data.stonebreak

import com.github.kory33.sandbox.sponge.KeyRepository
import org.spongepowered.api.Sponge
import org.spongepowered.api.data.manipulator.immutable.common.AbstractImmutableSingleData
import org.spongepowered.api.data.value.immutable.ImmutableValue

class ImmutableStoneBreakAmountData(private val amount: Int):
        AbstractImmutableSingleData<Int, ImmutableStoneBreakAmountData, StoneBreakAmountData>(amount, KeyRepository.breakAmount) {

    operator fun plus(amount: Int) = ImmutableStoneBreakAmountData(getValue() + amount)

    override fun getContentVersion() = StoneBreakAmountDataBuilder.CONTENT_VERSION

    override fun asMutable() = StoneBreakAmountData(amount)

    private val cachedValueGetter: ImmutableValue<Int> = Sponge.getRegistry().valueFactory
            .createValue(KeyRepository.breakAmount, amount).asImmutable()

    override fun getValueGetter() = cachedValueGetter

}