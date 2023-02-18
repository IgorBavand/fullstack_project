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

    public void salvarFoto(MultipartFile foto) {
        this.salvar(this.diretorioFotos, foto);
    }

    public void salvar(String diretorio, MultipartFile arquivo) {
        Path diretorioPath = Paths.get(this.raiz, diretorio);
        Path arquivoPath = diretorioPath.resolve(Objects.requireNonNull(arquivo.getOriginalFilename()));

        try {
            Files.createDirectories(diretorioPath);
            arquivo.transferTo(arquivoPath.toFile());
        } catch (IOException e) {
            throw new RuntimeException("Problemas na tentativa de salvar arquivo.", e);
        }

    }
}