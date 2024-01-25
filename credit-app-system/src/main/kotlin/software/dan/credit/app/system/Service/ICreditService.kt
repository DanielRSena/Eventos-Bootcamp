package software.dan.credit.app.system.Service

import java.util.UUID
import software.dan.credit.app.system.entity.Credit

interface ICreditService {

    fun save(credit: Credit): Credit


     fun findAllByCustomer(customerId: Long): List<Credit>

     fun findByCreditCode(customerId: Long, creditCode: UUID): Credit

}