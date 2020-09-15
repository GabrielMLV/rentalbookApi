INSERT INTO book(
	created_at, created_by, description, price, status_book, title, updated_at)
	SELECT now(), 'system', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry.', 29.99, 2, 'O encontro', now() WHERE NOT EXISTS (
    SELECT 1 FROM book WHERE title='O encontro');

INSERT INTO book(
	created_at, created_by, description, price, status_book, title, updated_at)
	SELECT now(), 'system', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry.', 39.99, 2, 'O carro', now() WHERE NOT EXISTS (
    SELECT 1 FROM book WHERE title='O carro');

INSERT INTO book(
	created_at, created_by, description, price, status_book, title, updated_at)
	SELECT now(), 'system', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry.', 15.99, 3, 'Eu sou a lenda', now() WHERE NOT EXISTS (
    SELECT 1 FROM book WHERE title='Eu sou a lenda');

INSERT INTO book(
	created_at, created_by, description, price, status_book, title, updated_at)
	SELECT now(), 'system', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry.', 59.99, 1, 'Mistérios do mundo', now() WHERE NOT EXISTS (
    SELECT 1 FROM book WHERE title='Mistérios do mundo');

INSERT INTO book(
	created_at, created_by, description, price, status_book, title, updated_at)
	SELECT now(), 'system', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry.', 22.19, 1, 'Manual de sobrevivência', now() WHERE NOT EXISTS (
    SELECT 1 FROM book WHERE title='Manual de sobrevivência');

INSERT INTO book(
	created_at, created_by, description, price, status_book, title, updated_at)
	SELECT now(), 'system', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry.', 27.89, 1, 'A cabana', now() WHERE NOT EXISTS (
    SELECT 1 FROM book WHERE title='A cabana');

INSERT INTO book(
	created_at, created_by, description, price, status_book, title, updated_at)
	SELECT now(), 'system', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry.', 21.59, 4, 'Caminhos', now() WHERE NOT EXISTS (
    SELECT 1 FROM book WHERE title='Caminhos');


INSERT INTO client(
	created_at, created_by, email, name, phone, updated_at)
	SELECT now(), 'system', 'joao@email.com', 'João da Silva', '8397897578', now() WHERE NOT EXISTS (
    SELECT 1 FROM client WHERE email='joao@email.com');

INSERT INTO client(
	created_at, created_by, email, name, phone, updated_at)
	SELECT now(), 'system', 'pedro@email.com', 'Pedro Silva', '8394577575', now() WHERE NOT EXISTS (
    SELECT 1 FROM client WHERE email='pedro@email.com');

INSERT INTO client(
	created_at, created_by, email, name, phone, updated_at)
	SELECT now(), 'system', 'maria@email.com', 'Maria Pereira', '8394577111', now() WHERE NOT EXISTS (
    SELECT 1 FROM client WHERE email='maria@email.com');

INSERT INTO client(
	created_at, created_by, email, name, phone, updated_at)
	SELECT now(), 'system', 'jose@email.com', 'José da Silva', '8394577529', now() WHERE NOT EXISTS (
    SELECT 1 FROM client WHERE email='jose@email.com');

INSERT INTO status_book(
	id, status_book)
	SELECT 1, 'Disponível' WHERE NOT EXISTS (
    SELECT 1 FROM status_book WHERE status_book='Disponível');

INSERT INTO status_book(
	id, status_book)
	SELECT 2, 'Alugado' WHERE NOT EXISTS (
    SELECT 1 FROM status_book WHERE status_book='Alugado');

INSERT INTO status_book(
	id, status_book)
	SELECT 3, 'Reservado' WHERE NOT EXISTS (
    SELECT 1 FROM status_book WHERE status_book='Reservado');

INSERT INTO status_book(
	id, status_book)
	SELECT 4, 'Bloqueado' WHERE NOT EXISTS (
    SELECT 1 FROM status_book WHERE status_book='Bloqueado');

INSERT INTO status_rental(
	id, status_name)
	SELECT 1, 'Alugado' WHERE NOT EXISTS (
    SELECT 1 FROM status_rental WHERE status_name='Alugado');

INSERT INTO status_rental(
	id, status_name)
	SELECT 2, 'Reservado' WHERE NOT EXISTS (
    SELECT 1 FROM status_rental WHERE status_name='Reservado');

INSERT INTO status_rental(
	id, status_name)
	SELECT 3, 'Cancelado' WHERE NOT EXISTS (
    SELECT 1 FROM status_rental WHERE status_name='Cancelado');

INSERT INTO status_rental(
	id, status_name)
	SELECT 4, 'Devolvido' WHERE NOT EXISTS (
    SELECT 1 FROM status_rental WHERE status_name='Devolvido');

INSERT INTO rentalbooks(
	created_at, created_by, date_end_rental, date_start_rental, id_book, id_client, id_status_rental, updated_at)
	SELECT now(), 'system', '2020-09-24 23:48:07', '2020-09-16 23:48:07', 1, 1, 1, now() WHERE NOT EXISTS (
    SELECT 1 FROM rentalbooks WHERE id=1);

INSERT INTO rentalbooks(
	created_at, created_by, date_end_rental, date_start_rental, id_book, id_client, id_status_rental, updated_at)
	SELECT now(), 'system', '2020-09-20 23:48:07', '2020-09-14 23:48:07', 2, 3, 1, now() WHERE NOT EXISTS (
    SELECT 1 FROM rentalbooks WHERE id=2);

INSERT INTO rentalbooks(
	created_at, created_by, date_end_rental, date_start_rental, id_book, id_client, id_status_rental, updated_at)
	SELECT now(), 'system', '2020-09-19 23:48:07', '2020-09-12 23:48:07', 3, 2, 1, now() WHERE NOT EXISTS (
    SELECT 1 FROM rentalbooks WHERE id=3);

INSERT INTO rentalbooks(
	created_at, created_by, date_end_rental, date_start_rental, id_book, id_client, id_status_rental, updated_at)
	SELECT now(), 'system', '2020-09-29 23:48:07', '2020-09-22 23:48:07', 2, 4, 2, now() WHERE NOT EXISTS (
    SELECT 1 FROM rentalbooks WHERE id=4);