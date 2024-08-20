module com.epam.jmp_cloud_bank_impl {
    requires transitive com.epam.jmp_bank_api;
    requires com.epam.jmp_dto;
    exports com.epam.jmp_cloud_bank_impl;

    provides com.epam.jmp_bank_api.Bank with com.epam.jmp_cloud_bank_impl.RetailBank,
                                             com.epam.jmp_cloud_bank_impl.CentralBank,
                                             com.epam.jmp_cloud_bank_impl.InvestmentBank;
}