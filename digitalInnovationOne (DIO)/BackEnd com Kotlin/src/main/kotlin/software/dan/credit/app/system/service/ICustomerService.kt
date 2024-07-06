package software.dan.credit.app.system.service

import software.dan.credit.app.system.entity.Customer

interface ICustomerService {

    fun save(customer: Customer): Customer
    fun findById(id: Long): Customer
    fun delete(id: Long)
}