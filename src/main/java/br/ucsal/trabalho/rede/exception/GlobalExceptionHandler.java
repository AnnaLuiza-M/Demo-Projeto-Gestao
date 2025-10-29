package br.ucsal.trabalho.rede.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<Erro> trataExcecaoGenerica(Exception e) {
        String mensagem = e.getMessage();
        if (e instanceof MethodArgumentTypeMismatchException) {
            mensagem = "Tipo inválido";
        }
        Erro erro = new Erro(mensagem, 400);
        e.printStackTrace();
        return ResponseEntity.badRequest().body(erro);
    }

    @ExceptionHandler
    public ResponseEntity<Erro> trataNotFound(NotFoundException e) {
        Erro erro = new Erro(e.getMessage(), 404);
        return ResponseEntity.badRequest().body(erro);
    }
}
