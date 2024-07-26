package com.marcusilgner.mockk_repro_1130

import arrow.core.NonEmptySet
import arrow.core.nonEmptySetOf
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot

interface QueryFacade {
    fun <AF> doStuff(
        aggregateFunctions: NonEmptySet<AF>
    ): AF
}

class MockkTypeParametersSpec : DescribeSpec({
    it("can mock the interface") {
        val facade = mockk<QueryFacade>()
        val slot = slot<NonEmptySet<String>>()
        every { facade.doStuff(capture(slot)) } returns "World"

        facade.doStuff(nonEmptySetOf("Hello")).shouldBe("World")
    }
})