package cl.arellano.wallmart.wallmartbackend.api.mapper;

import cl.arellano.wallmart.wallmartbackend.api.domain.SearchCriteria;
import cl.arellano.wallmart.wallmartbackend.api.dto.SearchCriteriaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SearchCriteriaMapper {

    SearchCriteriaMapper INSTANCE = Mappers.getMapper(SearchCriteriaMapper.class);

    SearchCriteriaDTO fromDomainToDTO(SearchCriteria searchCriteria);
    SearchCriteria fromDtoToDomain(SearchCriteriaDTO searchCriteriaDTO);
}
