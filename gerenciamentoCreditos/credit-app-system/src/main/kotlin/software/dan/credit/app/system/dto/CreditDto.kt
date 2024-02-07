package software.dan.credit.app.system.dto

import software.dan.credit.app.system.entity.Credit
import software.dan.credit.app.system.entity.Customer
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDto(
    val creditValue: BigDecimal,
    val dayFirstofInstallment: LocalDate,
    val numberOfInstallments: Int,
    val customerId: Long
) {
    fun toEntity(): Credit = Credit(
        creditValue = this.creditValue,
        dayFirstInstallment = this.dayFirstofInstallment,
        numberOfInstallment =  this.numberOfInstallments,
        customer = Customer(id = this.customerId)
    )

}
