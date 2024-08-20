BEGIN;
/* Notas minhas para o banco PostgreSQL

- Usar serial ao invés do auto increment 
- Text - Para tipos de textos sem valores máximos definidos
- Char - Usar quando tiver uma quantidade exata (tipo code char(5))
- Varchar - Usado quando tem alguma quantidade máxima tipo um endereço
*/

CREATE TABLE IF NOT EXISTS "Usuario"(
    "id_Usuario" SERIAL PRIMARY KEY,
    "email_Usuario" TEXT NOT NULL UNIQUE,
    "nome_Usuario" TEXT NOT NULL,
    "sobrenome_Usuario" TEXT NOT NULL,
    "fone_Usuario" TEXT NOT NULL,
    "senha" TEXT NOT NULL,
    /* Atributo cargo vai definir o tipo de login */
    "cargo" TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS "Cliente" (
    "id_Cliente" SERIAL PRIMARY KEY,
    "id_Usuario" INTEGER NOT NULL REFERENCES "Usuario"("id_Usuario")
);

CREATE TABLE IF NOT EXISTS "Funcionario" (
    "id_Fun" SERIAL PRIMARY KEY,
    "id_Usuario" INTEGER NOT NULL REFERENCES "Usuario"("id_Usuario")
);

CREATE TABLE IF NOT EXISTS "Movimentacao" (
    "id_Mov" SERIAL PRIMARY KEY,
    "data_Mov" TEXT NOT NULL,
    "fk_Cliente_id_Cliente" INTEGER NOT NULL REFERENCES "Cliente"("id_Cliente"),
    "fk_Fun_id_Fun" INTEGER NOT NULL REFERENCES "Funcionario"("id_Fun")
);

CREATE TABLE IF NOT EXISTS "F_Pgto" (
    "id_Pgto" SERIAL PRIMARY KEY,
    "nome_Pgto" TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS "Mov_Pgto_Tem" (
    "fk_Movimentacao_id_Mov" INTEGER NOT NULL REFERENCES "Movimentacao"("id_Mov"),
    "fk_F_Pgto_id_Pgto" INTEGER NOT NULL REFERENCES "F_Pgto"("id_Pgto"),
    PRIMARY KEY("fk_Movimentacao_id_Mov", "fk_F_Pgto_id_Pgto")
);

CREATE TABLE IF NOT EXISTS "categ_Prod" (
    "id_Categ" SERIAL PRIMARY KEY,
    "tipo_Categ" TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS "Produto" (
    "id_Produto" SERIAL PRIMARY KEY,
    "nome_Produto" TEXT NOT NULL,
    "preco_Produto" INTEGER NOT NULL,
    "qntd_Produto" INTEGER NOT NULL,
    "fk_Movimentacao_Id_Mov" INTEGER NOT NULL REFERENCES "Movimentacao"("id_Mov"),
    "fk_categProd_id_Categ" INTEGER NOT NULL REFERENCES "categ_Prod"("id_Categ")
);

CREATE TABLE IF NOT EXISTS "Comanda" (
    "id_Comanda" SERIAL PRIMARY KEY,
    "desc_Comanda" TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS "Mov_Coma_Gera" (
    "fk_Mov_Id_Mov" INTEGER NOT NULL REFERENCES "Movimentacao"("id_Mov"),
    "fk_Comanda_id_Comanda" INTEGER NOT NULL REFERENCES "Comanda"("id_Comanda"),
    PRIMARY KEY("fk_Mov_Id_Mov", "fk_Comanda_id_Comanda")
);

COMMIT;
