package com.example.weatherapp.domain.util

/**
 * this interface are used to help implementing methods for mapping between domain models and network (DTO) models.
 */
interface DomainMapper <T,DomainModel> {
    fun mapToDomainModel(model: T): DomainModel
    fun mapFromDomainModel(domainModel: DomainModel) : T

}