package com.caixa.caixa.modulos.comum.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Component
public class SalvarImagemUtils {

    @Value("${imagem.disco.raiz}")
    private String raiz;

    @Value("${imagem.disco.diretorio-fotos}")
    private String diretorioFotos;

    public String salvarFoto(MultipartFile imagem) {
        return this.salvar(this.diretorioFotos, imagem);
    }

    public String salvar(String diretorio, MultipartFile imagem) {

        String randomName = RadomStingUtils.generateRandomString();
        String[] nomeDaImagem = imagem.getOriginalFilename().split("\\.");
        String extensao = nomeDaImagem[1];
        String nomeFinal = randomName + "." + extensao;
        String urlImagem = raiz + "/" + diretorioFotos + "/" + nomeFinal;

        Path diretorioPath = Paths.get(this.raiz, diretorio);
        Path arquivoPath = diretorioPath.resolve(Objects.requireNonNull(nomeFinal));

        try {
            Files.createDirectories(diretorioPath);
            imagem.transferTo(arquivoPath.toFile());
            return urlImagem;
        } catch (IOException e) {
            throw new RuntimeException("Problemas na tentativa de salvar arquivo.", e);
        }

    }
}