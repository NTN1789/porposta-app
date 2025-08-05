package service;

import dto.PropostaRequestDto;
import dto.PropostaResponseDto;
import org.springframework.stereotype.Service;

@Service
public class PropostaService {



  /*  public PropostaResponseDto criar(PropostaRequestDto requestDto) {
        Proposta proposta = PropostaMapper.INSTANCE.convertDtoToProposta(requestDto);
        propostaRepository.save(proposta);

        notificarRabbitMQ(proposta);

        return PropostaMapper.INSTANCE.convertEntityToDto(proposta);
    }
*/
}
