--
-- PostgreSQL database dump
--

-- Dumped from database version 9.4.4
-- Dumped by pg_dump version 9.4.0
-- Started on 2015-11-23 11:04:26

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = officium, pg_catalog;

--
-- TOC entry 2037 (class 0 OID 24854)
-- Dependencies: 187
-- Data for Name: autorizacoes; Type: TABLE DATA; Schema: officium; Owner: postgres
--

INSERT INTO autorizacoes VALUES (1, 'ROLE_USER');


--
-- TOC entry 2036 (class 0 OID 24817)
-- Dependencies: 180
-- Data for Name: status_tarefas; Type: TABLE DATA; Schema: officium; Owner: postgres
--

INSERT INTO status_tarefas VALUES (2, 'Em Curso');
INSERT INTO status_tarefas VALUES (3, 'A Fazer');
INSERT INTO status_tarefas VALUES (4, 'Atrasada');
INSERT INTO status_tarefas VALUES (1, 'Concluida');


--
-- TOC entry 2042 (class 0 OID 0)
-- Dependencies: 179
-- Name: status_tarefas_id_seq; Type: SEQUENCE SET; Schema: officium; Owner: postgres
--

SELECT pg_catalog.setval('status_tarefas_id_seq', 1, true);


-- Completed on 2015-11-23 11:04:26

--
-- PostgreSQL database dump complete
--

