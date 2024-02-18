package software.dan.credit.app.system.service

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles
import software.dan.credit.app.system.entity.Address
import software.dan.credit.app.system.entity.Credit
import software.dan.credit.app.system.entity.Customer
import software.dan.credit.app.system.repository.CreditRepository
import software.dan.credit.app.system.repository.CreditRepositoryTest
import software.dan.credit.app.system.service.impl.CreditService
import java.math.BigDecimal
import java.time.LocalDate
import java.time.Month
import java.util.*

@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
class CreditServiceTest {

    @MockK lateinit var creditRepository: CreditRepository
    @InjectMockKs lateinit var creditService: CreditService

    @Test
    fun buildFakeCredit() {

        //given
        val fakeCustomer = buildCustomer()
        val fakeCredit = fakeCustomer.id?.let { buildCredit(customer = fakeCustomer) }

        every { creditRepository.save(any()) } returns fakeCredit!!

        //when
        val actual: Credit = creditService.save(fakeCredit)

        //then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isSameAs(fakeCredit)
        verify(exactly = 1) { creditRepository.save(fakeCredit) }
    }

    /*
    @Test
    fun fakeFindCustomerById() {

        val fakeId: Long = Random().nextLong()
        val fakeCustomer: Customer = buildCustomer(id = fakeId)
        every { customerRepository.findById(fakeId) } returns Optional.of(fakeCustomer)

        val actual: Customer = customerService.findById(fakeId)

        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isExactlyInstanceOf(Customer::class.java)
        Assertions.assertThat(actual).isSameAs(fakeCustomer)
        verify(exactly = 1) { customerRepository.findById(fakeId) }
    }
*/

    /*
    @Test
    fun BusinessExceptionFakeCustomerById() {

        val fakeId: Long = Random().nextLong()
        every { customerRepository.findById(fakeId) } returns Optional.empty()

        Assertions.assertThatExceptionOfType(BusinessException::class.java)
            .isThrownBy { customerService.findById(fakeId) }
            .withMessage("Id $fakeId not founded")
        verify(exactly = 1) { customerRepository.findById(fakeId) }
    }
*/

    /*
    @Test
    fun deleteFakeCustomer() {

        val fakeId: Long = Random().nextLong()
        val fakeCustomer: Customer = buildCustomer(id = fakeId)
        every { customerRepository.findById(fakeId) } returns Optional.of(fakeCustomer)
        every { customerRepository.delete(fakeCustomer) } just runs

        customerService.delete(fakeId)

        verify(exactly = 1) { customerRepository.findById(fakeId) }
        verify(exactly = 1) { customerRepository.delete(fakeCustomer) }
    }
*/

    private fun buildCredit(

        creditValue: BigDecimal = BigDecimal.valueOf(1200.0),
        dayFirstOfInstallment: LocalDate = LocalDate.of(2024, Month.MARCH, 23),
        numberOfInstallments: Int = 12,
        customer: Customer
    ): Credit = Credit(

        creditValue = creditValue,
        dayFirstInstallment = dayFirstOfInstallment,
        numberOfInstallment = numberOfInstallments,
        customer = customer
    )

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
    ) = Customer(

        firstName = firstName,
        lastName = lastName,
        cpf = cpf,
        email = email,
        password = password,
        address = Address(
            zipCode = zipCode,
            street = street
        ),
        income = income,
        id = id
    )

}