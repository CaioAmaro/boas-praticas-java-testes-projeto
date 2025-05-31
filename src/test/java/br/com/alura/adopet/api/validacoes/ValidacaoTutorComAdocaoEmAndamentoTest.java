package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ValidacaoTutorComAdocaoEmAndamentoTest {

    @InjectMocks
    private ValidacaoTutorComAdocaoEmAndamento validacaoTutorComAdocaoEmAndamento;

    @Mock
    private SolicitacaoAdocaoDto dto;

    @Mock
    private Tutor tutor;

    @Mock
    private AdocaoRepository adocaoRepository;

    @Mock
    private TutorRepository tutorRepository;

    @Mock
    private Adocao adocao;

    private List<Adocao> adocoes = new ArrayList<>();

    @Test
    void tutorTemAvaliacaoAndamento(){

        //ARRANGE
        adocoes.add(adocao);
        given(adocaoRepository.findAll()).willReturn(adocoes);


        given(tutorRepository.getReferenceById(dto.idTutor())).willReturn(tutor);


        given(adocao.getTutor()).willReturn(tutor);
        given(adocao.getStatus()).willReturn(StatusAdocao.AGUARDANDO_AVALIACAO);

        //ACT AND ASSERT
        Assertions.assertThrows(ValidacaoException.class, () -> validacaoTutorComAdocaoEmAndamento.validar(dto));
    }

    @Test
    void tutorNaoTemAvaliacaoAndamento(){

        //ARRANGE
        adocoes.add(adocao);
        given(adocaoRepository.findAll()).willReturn(adocoes);


        given(tutorRepository.getReferenceById(dto.idTutor())).willReturn(tutor);


        given(adocao.getTutor()).willReturn(tutor);
        given(adocao.getStatus()).willReturn(StatusAdocao.REPROVADO);

        //ACT AND ASSERT
        Assertions.assertDoesNotThrow( () -> validacaoTutorComAdocaoEmAndamento.validar(dto) );
    }

}