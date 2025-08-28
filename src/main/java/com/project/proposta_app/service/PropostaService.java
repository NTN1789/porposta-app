package com.project.proposta_app.service;

import com.project.proposta_app.dto.PropostaRequestDto;
import com.project.proposta_app.dto.PropostaResponseDto;
import com.project.proposta_app.entity.Proposta;
import com.project.proposta_app.mapper.PropostaMapper;
import com.project.proposta_app.repository.PropostaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PropostaService {


private PropostaRepository propostaRepository;

    public PropostaResponseDto criar(PropostaRequestDto requestDto) {
        Proposta proposta = PropostaMapper.INSTANCE.convertDtoToProposta(requestDto);
        propostaRepository.save(proposta);

   //     notificarRabbitMQ(proposta);

     //   return PropostaMapper.INSTANCE.convertEntityToDto(proposta);

    return  null;
    }

}
