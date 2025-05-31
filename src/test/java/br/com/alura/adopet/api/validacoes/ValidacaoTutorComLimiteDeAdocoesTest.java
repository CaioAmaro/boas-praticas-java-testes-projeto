package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ValidacaoTutorComLimiteDeAdocoesTest {

    @InjectMocks
    private ValidacaoTutorComLimiteDeAdocoes validacaoTutorComLimiteDeAdocoes;

    @Mock
    private AdocaoRepository adocaoRepository;

    @Mock
    private TutorRepository tutorRepository;

    @Mock
    private Adocao adocoes1;

    @Mock
    private Adocao adocoes2;

    @Mock
    private Adocao adocoes3;

    @Mock
    private Adocao adocoes4;

    @Mock
    private Adocao adocoes5;

    @Mock
    private Adocao adocoes6;

    @Mock
    private Tutor tutor;

    private List<Adocao> adocoes = new ArrayList<>();

    @Mock
    private SolicitacaoAdocaoDto dto;

    @Test
    void tutorAtigiLimiteAdocao(){
        //ARRANGE
        Collections.addAll(adocoes, adocoes1, adocoes2, adocoes3, adocoes4, adocoes5, adocoes6);
        given(adocoes1.getTutor()).willReturn(tutor);
        given(adocoes1.getStatus()).willReturn(StatusAdocao.APROVADO);
        given(adocoes2.getTutor()).willReturn(tutor);
        given(adocoes2.getStatus()).willReturn(StatusAdocao.APROVADO);
        given(adocoes3.getTutor()).willReturn(tutor);
        given(adocoes3.getStatus()).willReturn(StatusAdocao.APROVADO);
        given(adocoes4.getTutor()).willReturn(tutor);
        given(adocoes4.getStatus()).willReturn(StatusAdocao.APROVADO);
        given(adocoes5.getTutor()).willReturn(tutor);
        given(adocoes5.getStatus()).willReturn(StatusAdocao.APROVADO);
        given(adocoes6.getTutor()).willReturn(tutor);
        given(adocoes6.getStatus()).willReturn(StatusAdocao.APROVADO);

        given(adocaoRepository.findAll()).willReturn(adocoes);
        given(tutorRepository.getReferenceById(dto.idTutor())).willReturn(tutor);

        //ACT AND ASSERT
        Assertions.assertThrows(ValidacaoException.class, () -> validacaoTutorComLimiteDeAdocoes.validar(dto));
    }

    @Test
    void tutorNaoAtigiLimiteAdocao(){
        //ARRANGE
        Collections.addAll(adocoes, adocoes1, adocoes2, adocoes3, adocoes4, adocoes5, adocoes6);
        given(adocoes1.getTutor()).willReturn(tutor);
        given(adocoes1.getStatus()).willReturn(StatusAdocao.APROVADO);


        given(adocaoRepository.findAll()).willReturn(adocoes);
        given(tutorRepository.getReferenceById(dto.idTutor())).willReturn(tutor);

        //ACT AND ASSERT
        Assertions.assertDoesNotThrow(() -> validacaoTutorComLimiteDeAdocoes.validar(dto));
    }

}