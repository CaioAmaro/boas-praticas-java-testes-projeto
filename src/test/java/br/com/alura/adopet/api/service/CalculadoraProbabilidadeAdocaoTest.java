package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.CadastroAbrigoDto;
import br.com.alura.adopet.api.dto.CadastroPetDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.ProbabilidadeAdocao;
import br.com.alura.adopet.api.model.TipoPet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraProbabilidadeAdocaoTest {

    @Test
    void deveriaRetornarProbabilidadeAltaParaPetComPesoBaixoIdadeBaixa(){
        //idade 4 anos e 4kg - ALTA

//TRIPLE A
        //ARRANGE
        CadastroPetDto cadastroPetDto = new CadastroPetDto(
                TipoPet.GATO,
                "TESTE",
                "FELINO",
                4,
                "PRETO",
                4.0f
        );

        CadastroAbrigoDto cadastroAbrigoDto = new CadastroAbrigoDto(
                "abrigopets",
                "92994322932",
                "caioamaro@gmail.com"
        );

        Abrigo abrigo = new Abrigo(cadastroAbrigoDto);

        Pet pet = new Pet(cadastroPetDto, abrigo);

        //ACT
        CalculadoraProbabilidadeAdocao calculadoraProbabilidadeAdocao = new CalculadoraProbabilidadeAdocao();
        ProbabilidadeAdocao probabilidadeAdocao = calculadoraProbabilidadeAdocao.calcular(pet);


        //ASSERT
        Assertions.assertEquals(ProbabilidadeAdocao.ALTA, probabilidadeAdocao);

    }

    @Test
    void deveriaRetornarProbabilidadeMediaParaPetComPesoBaixoIdadeAlta(){
        //idade 15 anos e 4kg - MEDIA
        CadastroPetDto cadastroPetDto = new CadastroPetDto(
                TipoPet.GATO,
                "TESTE",
                "FELINO",
                15,
                "PRETO",
                4.0f
        );

        CadastroAbrigoDto cadastroAbrigoDto = new CadastroAbrigoDto(
                "abrigopets",
                "92994322932",
                "caioamaro@gmail.com"
        );

        Abrigo abrigo = new Abrigo(cadastroAbrigoDto);

        Pet pet = new Pet(cadastroPetDto, abrigo);

        CalculadoraProbabilidadeAdocao calculadoraProbabilidadeAdocao = new CalculadoraProbabilidadeAdocao();
        ProbabilidadeAdocao probabilidadeAdocao = calculadoraProbabilidadeAdocao.calcular(pet);

        Assertions.assertEquals(ProbabilidadeAdocao.MEDIA, probabilidadeAdocao);

    }

}