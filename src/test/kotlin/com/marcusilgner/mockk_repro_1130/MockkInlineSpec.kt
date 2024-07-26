package com.marcusilgner.mockk_repro_1130

import arrow.core.NonEmptySet
import io.kotest.core.spec.style.DescribeSpec
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify

class MockkMePlease(val foo: NonEmptySet<Int>) {
    fun doStuff() {
        throw Exception("This should be mocked")
    }
}

class MockkInlineSpec : DescribeSpec({
    describe("NonEmptySet in constructor") {
        it("uses the mock") {
            val mock = mockk<MockkMePlease>()
            justRun { mock.doStuff() }
            mock.doStuff()
            verify { mock.doStuff() }
        }
    }
})
