# Agenda de Contactos — Laboratorio Collections (TIFC2DEV-JAVA-MJ10)

Autora: Dayana Marcela Vallejo Ortega

Agenda de contactos en consola que aplica el Java Collections Framework:

| Estructura | Implementacion | Uso |
|---|---|---|
| `Map<String, Contacto>` | `TreeMap` | Busqueda rapida por nombre, orden alfabetico |
| `Set<String>` | `TreeSet` | Grupos unicos y ordenados |
| `Map<String, Set<String>>` | `TreeMap` de `TreeSet` | Grupo -> nombres de contactos |
| `List<Contacto>` | `ArrayList` | Historial en orden de insercion |

## Archivos
- `src/Contacto.java`
- `src/Agenda.java`
- `src/MenuPrincipal.java`
- `Evidencias/` — capturas de la ejecucion

## Como ejecutar
```
cd src
javac *.java
java MenuPrincipal
```
