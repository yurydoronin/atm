package atm.config

import atm.core.application.GetBalanceService
import atm.core.application.LoadBanknotesService
import atm.core.application.WithdrawMoneyService
import atm.core.application.port.`in`.GetBalanceUseCase
import atm.core.application.port.`in`.LoadBanknotesUseCase
import atm.core.application.port.`in`.WithdrawMoneyUseCase
import atm.core.application.port.out.BanknoteBalancePort
import atm.core.application.port.out.BanknoteLoaderPort
import atm.core.application.port.out.BanknoteWithdrawPort
import atm.infrastructura.adapter.out.persistence.InMemoryBanknoteStorage
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AtmConfiguration {

    @Bean
    fun inMemoryStorage(): InMemoryBanknoteStorage =
        InMemoryBanknoteStorage() // реализует все три порта

    @Bean
    fun loadBanknotesService(storage: BanknoteLoaderPort): LoadBanknotesUseCase =
        LoadBanknotesService(storage)

    @Bean
    fun withdrawMoneyService(storage: BanknoteWithdrawPort, balance: BanknoteBalancePort): WithdrawMoneyUseCase =
        WithdrawMoneyService(storage, balance)

    @Bean
    fun getBalanceService(balance: BanknoteBalancePort): GetBalanceUseCase =
        GetBalanceService(balance)
}
