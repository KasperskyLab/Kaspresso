package com.kaspersky.kaspressample.dsl_tests.data

class TestCaseData(
    val companies: MutableList<Company> = mutableListOf(),
    val owners: MutableList<Owner> = mutableListOf()
) {
    fun makeOwner(ownerSurname: String, companyName: String) {
        companies
            .filter { company ->
                company.name == companyName
            }
            .map { company ->
                company.owner =
                    owners.first { owner -> owner.secondName == ownerSurname }
            }
    }
}
