-- Crear tabla de citas
CREATE TABLE IF NOT EXISTS citas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_paciente BIGINT NOT NULL,
    fecha VARCHAR(255) NOT NULL,
    hora VARCHAR(255) NOT NULL,
    estado VARCHAR(20) DEFAULT 'PENDIENTE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Crear índices para mejora de consultas
CREATE INDEX idx_id_paciente ON citas(id_paciente);
CREATE INDEX idx_fecha ON citas(fecha);
CREATE INDEX idx_estado ON citas(estado);
