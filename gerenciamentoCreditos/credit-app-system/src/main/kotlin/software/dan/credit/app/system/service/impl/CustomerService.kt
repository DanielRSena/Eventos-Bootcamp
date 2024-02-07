package software.dan.credit.app.system.service.impl

import org.springframework.stereotype.Service
import software.dan.credit.app.system.entity.Customer
import software.dan.credit.app.system.repository.CustomerRepository
import software.dan.credit.app.system.service.ICustomerService

@Service
data class CustomerService(private val customerRepository: CustomerRepository
) : ICustomerService {

    override fun save(customer: Customer): Customer =
        this.customerRepository.save(customer)

    override fun findById(id: Long): Customer =
    this.customerRepository.findById(id).orElseThrow {
        throw RuntimeException("Id $id not founded")
    }

    override fun delete(id: Long) = this.customerRepository.deleteById(id)

}