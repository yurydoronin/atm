package atm.config

import atm.core.application.GetBalanceService
import atm.core.application.LoadBanknotesService
import atm.core.application.WithdrawMoneyService
import atm.core.application.ports.input.GetBalanceUseCase
import atm.core.application.ports.input.LoadBanknotesUseCase
import atm.core.application.ports.input.WithdrawMoneyUseCase
import atm.core.application.ports.output.BanknoteBalancePort
import atm.core.application.ports.output.BanknoteLoaderPort
import atm.core.application.ports.output.BanknoteWithdrawPort
import atm.infrastructure.output.adapters.persistence.InMemoryBanknoteStorage
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
    fun withdrawMoneyService(
        storage: BanknoteWithdrawPort,
        balance: BanknoteBalancePort,
    ): WithdrawMoneyUseCase = WithdrawMoneyService(storage, balance)

    @Bean
    fun getBalanceService(balance: BanknoteBalancePort): GetBalanceUseCase =
        GetBalanceService(balance)
}
