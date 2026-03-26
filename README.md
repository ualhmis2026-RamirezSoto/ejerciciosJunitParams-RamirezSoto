# ejerciciosJunitParams-RamirezSoto

Resolución de la sesión 06 de HMIS con JUnit 5, tests parametrizados y cobertura.

## Ejecutar tests

En Windows:

```powershell
.\gradlew.bat test
```

## Generar cobertura

```powershell
.\gradlew.bat jacocoTestReport
```

Reporte HTML generado en:

`build/reports/jacoco/test/html/index.html`

## Estructura

- Código fuente: `src/main/java`
- Tests: `src/test/java`
- Datos CSV y fixtures: `src/test/resources`
