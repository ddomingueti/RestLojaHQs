package com.ZupAceleracaoSenior.RestLojaHQs.Validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CpfValidator implements ConstraintValidator<CpfConstraint, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return false;
        if (value.equals("11111111111") || value.equals("22222222222") || value.equals("33333333333")
        || value.equals("44444444444") || value.equals("55555555555") || value.equals("66666666666")
        || value.equals("77777777777") || value.equals("88888888888") || value.equals("99999999999")
        || value.equals("00000000000")) return false;

        int soma = calculaSoma(value, 10, 9);
        char dv1 = defineDv(soma);
        
        if (dv1 == value.charAt(9)) {
            int soma2 = calculaSoma(value, 11, 10);
            char dv2 = defineDv(soma2);
            if (dv2 == value.charAt(10)) return true;
        }

        return false;
    }

    private int calculaSoma(String value, int peso, int ultimoDigito) {
        int soma = 0;
        int pesoIndex = peso;
        for (int i = 0; i < ultimoDigito; i++) {
            int digito = (int)(value.charAt(i) - 48);
            soma += (digito * pesoIndex);
            pesoIndex -= 1;
        }
        return soma;
    }

    private char defineDv(int soma) {
        int resto = 11 - (soma % 11);
        if (resto == 10 || resto == 11) {
            return '0';
        } else {
            return (char)(resto + 48);
        }
    }

    
    
}
