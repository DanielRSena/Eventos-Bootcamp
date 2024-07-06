package software.dan.credit.app.system.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import software.dan.credit.app.system.dto.CreditDto
import software.dan.credit.app.system.dto.CustomerDto
import software.dan.credit.app.system.dto.UpdateCustomerDto
import software.dan.credit.app.system.entity.Customer
import software.dan.credit.app.system.repository.CustomerRepository
import java.math.BigDecimal
import java.time.LocalDate
import java.time.Month
import java.time.Year

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
@ActiveProfiles("test")
class CreditResourceTest {

    @Autowired
    lateinit var customerRepository: CustomerRepository
    @Autowired
    private lateinit var mockMvc: MockMvc
    @Autowired
    private lateinit var objectMapper: ObjectMapper //class -> String

    companion object {
        const val URL = "/api/credit"
    }

    @BeforeEach fun setup() = customerRepository.deleteAll()
    @AfterEach fun tearDown() = customerRepository.deleteAll()

    @Test
    fun createCreditTest() {

        val customer = customerRepository.save(builderCustomerDTO().toEntity())
        val creditDto = buildCredit(customerId = customer.id!!)
        val strCreditDTO = objectMapper.writeValueAsString(creditDto)

        mockMvc.perform(
            MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(strCreditDTO)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)/*
             .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Yuri"))
             .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Alpha"))
             .andExpect(MockMvcResultMatchers.jsonPath("$.cpf").value("56454758010"))
             .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("yurialpha@ainzsama.com"))
             .andExpect(MockMvcResultMatchers.jsonPath("$.zipCode").value("11111111"))
             .andExpect(MockMvcResultMatchers.jsonPath("$.street").value("Grande Tumba de Nazarick"))
             //.andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
             */.andDo(MockMvcResultHandlers.print())
    }

    /*
    @Test
    fun errorDuplicatedCustomerDTO(){

        customerRepository.save(builderCustomerDTO().toEntity())
        val customerDto = builderCustomerDTO()
        val strCustomerDTO = objectMapper.writeValueAsString(customerDto)

        mockMvc.perform(MockMvcRequestBuilders.post(URL)
            .contentType(MediaType.APPLICATION_JSON)
            .content(strCustomerDTO))
            .andExpect(MockMvcResultMatchers.status().isConflict)
            .andExpect(MockMvcResultMatchers.status().isConflict)
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Conflict Request! Consult the documentation"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(409))
            .andExpect(
                MockMvcResultMatchers.jsonPath("$.exception")
                    .value("class org.springframework.dao.DataIntegrityViolationException")
            )
            .andExpect(MockMvcResultMatchers.jsonPath("$.details[*]").isNotEmpty)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun errorEmptyFirstNameCustomerDTO(){

        val customerDto = builderCustomerDTO(firstName = "")
        val strCustomerDTO = objectMapper.writeValueAsString(customerDto)

        mockMvc.perform(MockMvcRequestBuilders.post(URL)
            .contentType(MediaType.APPLICATION_JSON)
            .content(strCustomerDTO))
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Bad Request! Consult the documentation"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(400))
            .andExpect(
                MockMvcResultMatchers.jsonPath("$.exception")
                    .value("class org.springframework.web.bind.MethodArgumentNotValidException")
            )
            .andExpect(MockMvcResultMatchers.jsonPath("$.details[*]").isNotEmpty)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun findCustomerByIdTest(){

        val customer: Customer = customerRepository.save(builderCustomerDTO().toEntity())

        mockMvc.perform(MockMvcRequestBuilders
            .get("$URL/${customer.id}")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Yuri"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Alpha"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.cpf").value("56454758010"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("yurialpha@ainzsama.com"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.zipCode").value("11111111"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.street").value("Grande Tumba de Nazarick"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.income").value(1000.0))
            //.andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun findCustomerById400Test(){

        mockMvc.perform(MockMvcRequestBuilders
            .get("$URL/${404}")
            .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Bad Request! Consult the documentation"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(400))
            .andExpect(
                MockMvcResultMatchers.jsonPath("$.exception")
                    .value("class software.dan.credit.app.system.exception.BusinessException")
            )
            .andExpect(MockMvcResultMatchers.jsonPath("$.details[*]").isNotEmpty)
            .andDo(MockMvcResultHandlers.print())

    }

    @Test
    fun deleteCustomerTest(){

        val customer: Customer = customerRepository.save(builderCustomerDTO().toEntity())

        mockMvc.perform(MockMvcRequestBuilders
            .delete("$URL/${customer.id}")
            .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNoContent)
            .andDo(MockMvcResultHandlers.print())

    }

    @Test
    fun deleteCustomerById400Test(){

        mockMvc.perform(MockMvcRequestBuilders
            .delete("$URL/${404}")
            .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Bad Request! Consult the documentation"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(400))
            .andExpect(
                MockMvcResultMatchers.jsonPath("$.exception")
                    .value("class software.dan.credit.app.system.exception.BusinessException")
            )
            .andExpect(MockMvcResultMatchers.jsonPath("$.details[*]").isNotEmpty)
            .andDo(MockMvcResultHandlers.print())
    }


    @Test
    fun updateCustomerTest(){

        val customer: Customer = customerRepository.save(builderCustomerDTO().toEntity())
        val updateCustomerDto: UpdateCustomerDto = builderUpdatedCustomerDTO()
        val strUpdateCustomerDto: String = objectMapper.writeValueAsString(updateCustomerDto)

        mockMvc.perform(
            MockMvcRequestBuilders.patch("$URL?costumerId=${customer.id}")
                .contentType(MediaType.APPLICATION_JSON)
                .content(strUpdateCustomerDto)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Lupusregina"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Beta"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.zipCode").value("22222222"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.cpf").value("56454758010"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("yurialpha@ainzsama.com"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.street").value("Grande Tumba de Nazarick"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.income").value("900.0"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun updateCustomer404Test(){

        val updateCustomerDto: UpdateCustomerDto = builderUpdatedCustomerDTO()
        val strUpdateCustomerDto: String = objectMapper.writeValueAsString(updateCustomerDto)

        mockMvc.perform(
            MockMvcRequestBuilders.patch("$URL?costumerId=404")
                .contentType(MediaType.APPLICATION_JSON)
                .content(strUpdateCustomerDto)
        )
            .andExpect(MockMvcResultMatchers.status().isBadRequest).andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Bad Request! Consult the documentation"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(400))
            .andExpect(
                MockMvcResultMatchers.jsonPath("$.exception")
                    .value("class software.dan.credit.app.system.exception.BusinessException")
            )
            .andExpect(MockMvcResultMatchers.jsonPath("$.details[*]").isNotEmpty)
            .andDo(MockMvcResultHandlers.print())
    }
*/

    private fun builderCustomerDTO(

        firstName: String = "Yuri",
        lastName: String = "Alpha",
        cpf: String = "56454758010",
        email: String = "yurialpha@ainzsama.com",
        password: String = "yurialpha123",
        zipCode: String = "11111111",
        street: String = "Grande Tumba de Nazarick",
        income: BigDecimal = BigDecimal.valueOf(1000.0)
    ) = CustomerDto(

        firstName = firstName,
        lastName = lastName,
        cpf = cpf,
        income = income,
        email = email,
        password = password,
        zipCode = zipCode,
        street = street
    )

   private fun buildCredit(
       creditValue: BigDecimal = BigDecimal.valueOf(1220.0),
       dayFirstOfInstallment: LocalDate = LocalDate.of(2025, Month.MARCH, 12),
       numberOfInstallments: Int = 15,
       customerId: Long = 1

   ) = CreditDto (
       creditValue = creditValue,
       dayFirstOfInstallment = dayFirstOfInstallment,
       numberOfInstallments = numberOfInstallments,
       customerId = customerId
   )

}