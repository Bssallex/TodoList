CREATE TABLE todolist (
    id serial PRIMARY KEY,
    nome_tarefa VARCHAR(255) NOT NULL,
    descricao_tarefa VARCHAR(255),
    data_criacao DATE,
    status VARCHAR(255)
);