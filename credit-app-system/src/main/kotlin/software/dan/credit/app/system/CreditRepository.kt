package software.dan.credit.app.system

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import software.dan.credit.app.system.entity.Credit

@Repository
interface CreditRepository: JpaRepository <Credit, Long>{

}