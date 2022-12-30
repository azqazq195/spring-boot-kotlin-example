package com.example.springbootkotlinexample.domain.example.entity.repository

import io.kotest.core.spec.style.DescribeSpec
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class ExampleRepositoryTest(
    @Autowired
    val exampleRepository: ExampleRepository
) : DescribeSpec({
//    describe("ExampleRepository") {
//        context("initialization") {
//            it("should be defined") {
//                exampleRepository shouldNotBe null
//            }
//        }
//
//        context("save") {
//            it("should save an example") {
//                // given
//                val example = Example(
//                    name = "example",
//                    content = "content",
//                    count = 1,
//                    enum = ExampleEnum.MASTER
//                )
//
//                // when
//                val savedExample = withContext(Dispatchers.IO) {
//                    exampleRepository.save(example)
//                }
//
//                // then
//                example.id shouldNotBe null
//                example.name shouldBe savedExample.name
//                example.content shouldBe savedExample.content
//                example.count shouldBe savedExample.count
//                example.enum shouldBe savedExample.enum
//            }
//        }
//    }
})
