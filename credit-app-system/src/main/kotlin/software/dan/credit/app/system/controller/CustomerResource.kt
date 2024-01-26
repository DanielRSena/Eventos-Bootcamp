package software.dan.credit.app.system.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import software.dan.credit.app.system.Service.impl.CustomerService
import software.dan.credit.app.system.dto.CustomerDto
import software.dan.credit.app.system.dto.CustomerView
import software.dan.credit.app.system.dto.UpdateCustomerDto
import software.dan.credit.app.system.entity.Customer

@RestController
@RequestMapping("/api/customers")
class CustomerResource (private val customerService: CustomerService) {

    @PostMapping
    fun saveCustomer(@RequestBody CustomerDto: CustomerDto): ResponseEntity <String>{
        val savedCustomer = this.customerService.save(CustomerDto.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED).body("Customer ${savedCustomer.email} saved"
        )
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity <CustomerView> {
    val customer: Customer = this.customerService.findCustomerById(id)
        return ResponseEntity.status(HttpStatus.OK).body(CustomerView(customer)
        )
    }

    @DeleteMapping("/{id}")
    fun deleteCustomer(@PathVariable id: Long) = this.customerService.delete(id)

    @PatchMapping
    fun updateCustomer(@RequestParam(value = "costumerId") id: Long,
                       @RequestBody customerUpdateDto: UpdateCustomerDto): ResponseEntity <CustomerView> {
        val customer: Customer = this.customerService.findCustomerById(id)
        val customerToUpdate: Customer = customerUpdateDto.toEntity(customer)
        val customerUpdated: Customer = this.customerService.save(customerToUpdate)

        return  ResponseEntity.status(HttpStatus.OK).body(CustomerView(customerUpdated))
    }
}