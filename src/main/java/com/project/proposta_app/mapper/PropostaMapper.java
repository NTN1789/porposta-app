package com.project.proposta_app.mapper;

import com.project.proposta_app.dto.PropostaRequestDto;
import com.project.proposta_app.dto.PropostaResponseDto;
import com.project.proposta_app.entity.Proposta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;

@Mapper
public interface PropostaMapper {
    PropostaMapper INSTANCE = Mappers.getMapper(PropostaMapper.class);

    @Mapping(target = "usuario.nome", source = "nome")
    @Mapping(target = "usuario.sobrenome", source = "sobrenome")
    @Mapping(target = "usuario.cpf", source = "cpf")
    @Mapping(target = "usuario.telefone", source = "telefone")
    @Mapping(target = "usuario.renda", source = "renda")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "aprovada", ignore = true)
    @Mapping(target = "integrada", constant = "true")
    @Mapping(target = "observacao", ignore = true)
        // Se o DTO.valorSolicitado for String, converta:
        // @Mapping(target = "valorSolicitado", expression = "java(parseValor(propostaRequestDto.getValorSolicitado()))")
    Proposta convertDtoToProposta(PropostaRequestDto propostaRequestDto);

    @Mapping(target = "nome", source = "usuario.nome")
    @Mapping(target = "sobrenome", source = "usuario.sobrenome")
    @Mapping(target = "telefone", source = "usuario.telefone")
    @Mapping(target = "cpf", source = "usuario.cpf")
    @Mapping(target = "renda", source = "usuario.renda")
    @Mapping(target = "valorSolicitadoFmt", expression = "java(setValorSolicitadoFmt(proposta))")
    PropostaResponseDto convertEntityToDto(Proposta proposta);

    List<PropostaResponseDto> convertListEntityToListDto(Iterable<Proposta> propostas);

    // Caso o DTO chegue com String (descomente o @Mapping acima)
    default BigDecimal parseValor(String raw) {
        if (raw == null || raw.isBlank()) return null;
        // remove "R$ ", espaços e separador de milhar . ; troca vírgula por ponto
        String norm = raw.replaceAll("[^0-9,.-]", "")
                .replace(".", "")
                .replace(",", ".");
        return new BigDecimal(norm);
    }

    default String setValorSolicitadoFmt(Proposta proposta) {
        if (proposta == null || proposta.getValorSolicitado() == null) return null;

        var nf = java.text.NumberFormat.getCurrencyInstance(new java.util.Locale("pt","BR"));
        Object v = proposta.getValorSolicitado();

        if (v instanceof Number n) {
            return nf.format(n);
        } else {

            String s = v.toString();
            try {
                // normaliza: remove tudo que não número/sep, tira milhar e troca vírgula por ponto
                String norm = s.replaceAll("[^0-9,.-]", "")
                        .replace(".", "")
                        .replace(",", ".");
                java.math.BigDecimal bd = new java.math.BigDecimal(norm);
                return nf.format(bd);
            } catch (Exception e) {

                return s;
            }
        }
    }

}

