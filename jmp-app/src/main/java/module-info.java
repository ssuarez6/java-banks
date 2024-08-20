module com.epam.jmp_app {
    requires com.epam.jmp_bank_api;
    requires com.epam.jmp_cloud_bank_impl;
    requires com.epam.jmp_service_api;
    requires com.epam.jmp_cloud_service_impl;
    requires com.epam.jmp_dto;

    uses com.epam.jmp_bank_api.Bank;
}