package software.dan.credit.app.system.service.impl

import org.springframework.stereotype.Service
import software.dan.credit.app.system.entity.Customer
import software.dan.credit.app.system.exception.BusinessException
import software.dan.credit.app.system.repository.CustomerRepository
import software.dan.credit.app.system.service.ICustomerService

@Service
class CustomerService(
    private val customerRepository: CustomerRepository
) : ICustomerService {

    override fun save(customer: Customer): Customer = this.customerRepository.save(customer)

    override fun findById(id: Long): Customer =
        this.customerRepository.findById(id).orElseThrow {
            throw BusinessException("Id $id not founded")
        }

    override fun delete(id: Long) {

        val customer: Customer = this.findById(id)
        this.customerRepository.delete(customer)
    }
}