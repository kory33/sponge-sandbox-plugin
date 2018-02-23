package com.github.kory33.sandbox.sponge

import org.spongepowered.api.data.DataQuery
import org.spongepowered.api.data.key.Key
import org.spongepowered.api.data.value.mutable.Value
import org.spongepowered.api.util.TypeTokens

object KeyRepository {
    val breakAmount: Key<Value<Int>> = Key.builder()
            .type(TypeTokens.INTEGER_VALUE_TOKEN)
            .id("break_amount")
            .name("Break Amount")
            .query(DataQuery.of("BreakAmount"))
            .build()
}