package software.dan.credit.app.system.repository

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.ActiveProfiles
import software.dan.credit.app.system.entity.Address
import software.dan.credit.app.system.entity.Credit
import software.dan.credit.app.system.entity.Customer
import java.math.BigDecimal
import java.time.LocalDate
import java.time.Month
import java.util.UUID

@ActiveProfiles("test")
@DataJpaTest //essa class testa a parte do bd
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CreditRepositoryTest {
    @Autowired
    lateinit var creditRepository: CreditRepository
    @Autowired
    lateinit var testEntityManager: TestEntityManager

    private lateinit var customer: Customer
    private lateinit var credit1: Credit
    private lateinit var credit2: Credit

    @BeforeEach
    fun setup() {
        customer = testEntityManager.persist(buildCustomer())
        credit1 = testEntityManager.persist(buildCredit(customer = customer))
        credit2 = testEntityManager.persist(buildCredit(customer = customer))
    }

    @Test
    fun findCreditByCreditCodeTest() {

        val creditCode1 = UUID.fromString("3a9f0393-ded5-494e-83d0-353f4ae35f10")
        credit1.creditCode = creditCode1
        val creditCode2 = UUID.fromString("ba001cfd-4b38-49e9-9e84-8a5882829650")
        credit2.creditCode = creditCode2

        val fakeCredit1: Credit = creditRepository.findByCreditCode(creditCode1)!!
        val fakeCredit2: Credit = creditRepository.findByCreditCode(creditCode2)!!

        Assertions.assertThat(fakeCredit1).isNotNull
        Assertions.assertThat(fakeCredit2).isNotNull
        Assertions.assertThat(fakeCredit1).isSameAs(credit1)
        Assertions.assertThat(fakeCredit2).isSameAs(credit2)
    }

    @Test
    fun findAllByCustomerTest() {

        val customerId: Long = 1L

        val creditList: List<Credit> = creditRepository.findAllByCustomer(customerId)

        Assertions.assertThat(creditList).isNotEmpty
        Assertions.assertThat(creditList).size().isEqualTo(2)
        Assertions.assertThat(creditList).contains(credit1, credit2)

    }


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
    )
}
