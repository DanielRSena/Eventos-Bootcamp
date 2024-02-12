package software.dan.credit.app.system.service

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles
import software.dan.credit.app.system.entity.Address
import software.dan.credit.app.system.entity.Customer
import software.dan.credit.app.system.exception.BusinessException
import software.dan.credit.app.system.repository.CustomerRepository
import software.dan.credit.app.system.service.impl.CustomerService
import java.math.BigDecimal
import java.util.Optional
import java.util.Random

@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
class CustomerServiceTest {

    @MockK lateinit var customerRepository: CustomerRepository
    @InjectMockKs lateinit var customerService: CustomerService

    @Test
    fun buildFakeCustomer(){

        //given
        val fakeCustomer = buildCustomer()
        every { customerRepository.save(any()) } returns fakeCustomer

        //when
        val actual: Customer = customerService.save(fakeCustomer)

        //then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isSameAs(fakeCustomer)
        verify(exactly = 1) { customerRepository.save(fakeCustomer) }
    }

    @Test
    fun fakeFindCustomerById(){

        val fakeId: Long = Random().nextLong()
        val fakeCustomer: Customer = buildCustomer(id = fakeId)
        every { customerRepository.findById(fakeId) } returns  Optional.of(fakeCustomer)

        val actual: Customer = customerService.findById(fakeId)

        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isExactlyInstanceOf(Customer::class.java)
        Assertions.assertThat(actual).isSameAs(fakeCustomer)
        verify(exactly = 1) { customerRepository.findById(fakeId) }
    }

    @Test
    fun BusinessExceptionFakeCustomerById(){

        val fakeId: Long = Random().nextLong()
        every { customerRepository.findById(fakeId) } returns  Optional.empty()

        Assertions.assertThatExceptionOfType(BusinessException::class.java)
            .isThrownBy { customerService.findById(fakeId) }
            .withMessage("Id $fakeId not founded")
        verify(exactly = 1) { customerRepository.findById(fakeId) }
    }

    @Test
    fun deleteFakeCustomer(){

        val fakeId: Long = Random().nextLong()
        val fakeCustomer: Customer = buildCustomer(id = fakeId)
        every { customerRepository.findById(fakeId) } returns  Optional.of(fakeCustomer)
        every { customerRepository.delete(fakeCustomer) } just runs

        customerService.delete(fakeId)

        verify(exactly = 1) { customerRepository.findById(fakeId) }
        verify(exactly = 1) { customerRepository.delete(fakeCustomer) }
    }


    private fun buildCustomer(

        firstName: String = "Yuri",
        lastName: String = "Alpha",
        cpf: String = "56454758010",
        email: String = "yurialpha@ainzsama.com",
        password: String = "yurialpha123",
        zipCode: String = "11111111",
        street: String = "Grande Tumba de Nazarick",
        income: BigDecimal = BigDecimal.valueOf(1000.0),
        id: Long = 1
    ) = Customer (

        firstName = firstName,
        lastName = lastName,
        cpf = cpf,
        email = email,
        password = password,
        address = Address(
            zipCode = zipCode,
            street = street
        ),
        income =  income,
        id =  id
    )
}