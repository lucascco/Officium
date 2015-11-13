/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.officium.ws.service.utils;

import com.google.gson.Gson;
import java.util.List;

/**
 *
 * @author Lucas Corrêa
 */
public final class JsonGenerator {

    private static Gson gson = new Gson();

    public static String generateJson(Object obj) {
        return null == obj ? "" : gson.toJson(obj);
    }
    
    public static String generateJson(List<Object> objs){
        return null == objs ? "" : gson.toJson(objs);
    }

    public static Object generateTOfromJson(String json, Class<?> class1) {
        return gson.fromJson(json, class1);
    }

    public static String generateErrorJson(Exception exception, int code) {

        ErrorJson errorTO = new ErrorJson();
        errorTO.setErrorCode(code);
        errorTO.setErrorMessage(exception.getMessage());
        return generateJson(errorTO);
    }

    public static String generateSuccessJson(String message) {
        SucessJson successTO = new SucessJson();
        successTO.setCode(0);
        successTO.setMessage(message);
        return generateJson(successTO);
    }
}
