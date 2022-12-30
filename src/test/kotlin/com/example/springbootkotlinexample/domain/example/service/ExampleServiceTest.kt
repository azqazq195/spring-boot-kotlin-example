package com.example.springbootkotlinexample.domain.example.service

import io.kotest.core.spec.style.BehaviorSpec
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ExampleServiceTest(
    @Autowired
    val exampleService: ExampleService
) : BehaviorSpec({
//    Given("CreateExampleDto") {
//        val createExampleDto = createExampleDto
//        When("create") {
//            val example = withContext(Dispatchers.IO) {
//                exampleService.create(createExampleDto)
//            }
//            Then("생성 확인") {
//                example.id shouldNotBe null
//                example.name shouldBe createExampleDto.name
//                example.content shouldBe createExampleDto.content
//                example.count shouldBe createExampleDto.count
//                example.enum shouldBe createExampleDto.enum
//            }
//            Then("조회 확인") {
//                val foundExample = withContext(Dispatchers.IO) {
//                    exampleService.findById(example.id!!)
//                }
//                foundExample.id shouldBe example.id
//                foundExample.name shouldBe example.name
//                foundExample.content shouldBe example.content
//                foundExample.count shouldBe example.count
//                foundExample.enum shouldBe example.enum
//            }
//        }
//    }
})
// {
//    companion object {
//        val createExampleDto = CreateExampleDto(
//            name = "example",
//            content = "content",
//            count = 1,
//            enum = ExampleEnum.MASTER
//        )
//    }
// }
