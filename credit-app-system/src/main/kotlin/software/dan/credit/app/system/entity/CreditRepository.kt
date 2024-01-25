package software.dan.credit.app.system.entity;

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface CreditRepository : JpaRepository<Credit, Long> {
    fun findByCreditCode(creditCode: UUID): Credit?

    @Query(value = "SELECT * FROM CREDIT WHERE CREDIT_ID = ?1", nativeQuery = true)
    fun findAllByCustomer(customerId: Long): List<Credit>
}