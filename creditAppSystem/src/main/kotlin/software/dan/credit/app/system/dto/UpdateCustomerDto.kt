package software.dan.credit.app.system.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import software.dan.credit.app.system.entity.Customer
import java.math.BigDecimal

data class UpdateCustomerDto(
    @field:NotEmpty(message = "first Name value is empty") val firstName: String,
    @field:NotEmpty(message = "last Name value is empty") val lastName: String,
    @field:NotNull(message = "The income value is null") val income: BigDecimal,
    @field:NotEmpty(message = "zipCode Name value is empty") val zipCode: String,
    @field:NotEmpty(message = "street value is empty") val street: String
) {
    fun toEntity(customer: Customer): Customer {

        customer.firstName = this.firstName
        customer.lastName = this.lastName
        customer.income = this.income
        customer.address.zipCode = this.zipCode
        customer.address.street = this.street

        return customer
    }
}