package com.alura.LiteraturaChallengeAPI.service;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
