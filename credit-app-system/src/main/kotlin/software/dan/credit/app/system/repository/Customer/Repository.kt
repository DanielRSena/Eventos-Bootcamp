package software.dan.credit.app.system.repository.Customer

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import software.dan.credit.app.system.entity.Customer

@Repository
interface Repository: JpaRepository <Customer, Long> {
    
}