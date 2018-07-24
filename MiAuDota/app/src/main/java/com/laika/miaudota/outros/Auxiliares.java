package com.laika.miaudota.outros;

import java.text.Normalizer;
import java.util.regex.Pattern;

import static com.laika.miaudota.outros.Config.BLANK;

public class Auxiliares {

    // Retira Acentos de uma String
    public static String tiraAcento(String str){
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll(BLANK);
    }
}
