package software.dan.credit.app.system.repository

import org.springframework.data.jpa.repository.JpaRepository
import software.dan.credit.app.system.entity.Customer

interface CustomerRepository : JpaRepository<Customer, Long>