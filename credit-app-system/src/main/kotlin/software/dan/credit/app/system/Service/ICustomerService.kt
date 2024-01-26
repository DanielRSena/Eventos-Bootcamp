package software.dan.credit.app.system.Service
import software.dan.credit.app.system.entity.Customer

interface ICustomerService {
   fun save(customer: Customer): Customer

    fun findCustomerById(customerId: Long): Customer
    fun delete(customerId: Long)
}