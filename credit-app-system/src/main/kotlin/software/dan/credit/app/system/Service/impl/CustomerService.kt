package software.dan.credit.app.system.Service.impl

import org.springframework.stereotype.Service
import software.dan.credit.app.system.Service.ICustomerService
import software.dan.credit.app.system.entity.Customer
import software.dan.credit.app.system.repository.CustomerRepository

@Service
data class CustomerService(private val customerRepository: CustomerRepository
) : ICustomerService {

    override fun save(customer: Customer): Customer = this.customerRepository.save(customer)

    override fun findCustomerById(customerId: Long): Customer =
    this.customerRepository.findById(customerId).orElseThrow {
        throw RuntimeException("Id $customerId not founded")
    }

    override fun delete(customerId: Long) = this.customerRepository.deleteById(customerId)
}