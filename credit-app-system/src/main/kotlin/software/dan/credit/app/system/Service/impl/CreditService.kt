package software.dan.credit.app.system.Service.impl

import java.util.UUID
import org.springframework.stereotype.Service
import software.dan.credit.app.system.Service.ICreditService
import software.dan.credit.app.system.entity.Credit
import software.dan.credit.app.system.repository.CreditRepository

@Service
class CreditService(private val creditRepository: CreditRepository, private val customerService: CustomerService): ICreditService {
    override fun save(credit: Credit): Credit {
        credit.apply {
            customer = customerService.findCustomerById(credit.customer?.id!!)
        }
        return this.creditRepository.save(credit)
    }


    override fun findAllByCustomer(customerId: Long): List<Credit> = this.creditRepository.findAllByCustomer(customerId)



    override fun findByCreditCode(customerId: Long, creditCode: UUID): Credit {
        val credit = this.creditRepository.findByCreditCode(creditCode)
            ?: throw RuntimeException("Credit code not founded")
        return if (credit.customer?.id == customerId) credit else throw RuntimeException("contact admin")
    }
}