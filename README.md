# ms-app-medica-citas-db

Capa **DB** del módulo **Citas**. Expone un CRUD REST sobre la entidad `Cita` y persiste en MySQL (`app_medica_citas`) vía Spring Data JPA + Hibernate. El esquema se gestiona con **Flyway** (migraciones versionadas).

| | |
|---|---|
| **Puerto** | `8092` |
| **Patrón** | Controller → Service → Repository (CSR) |
| **Ruta base** | `/api/v1/citas` |
| **Persistencia** | MySQL `app_medica_citas` (JPA/Hibernate) |
| **Migraciones** | **Flyway** — `src/main/resources/db/migration/V1__create_citas_table.sql` |
| **Pruebas** | `CitaServiceTest` (JUnit 5 + Mockito) |
| **Swagger** | `http://localhost:8092/swagger-ui.html` |

No llama a ningún otro microservicio.

## Ejecución

```bash
# Con todo el ecosistema (recomendado), desde app-medica-et-fullstack-1/
docker compose up --build

# Individual
./mvnw spring-boot:run     # mvnw.cmd en Windows
./mvnw test
```
