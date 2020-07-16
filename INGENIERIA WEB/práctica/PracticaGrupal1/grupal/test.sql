--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.15
-- Dumped by pg_dump version 9.6.15

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Data for Name: module; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO "public"."module" ("oid", "moduleid", "modulename") VALUES (1, 'sv3', 'cliente');
INSERT INTO "public"."module" ("oid", "moduleid", "modulename") VALUES (2, 'sv2', 'administracion');


--
-- Data for Name: group; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO "public"."group" ("oid", "groupname", "module_oid") VALUES (1, 'cliente', 1);
INSERT INTO "public"."group" ("oid", "groupname", "module_oid") VALUES (2, 'admin', 2);


--
-- Data for Name: group_module; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: herramienta; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO "public"."herramienta" ("oid", "nombre", "version") VALUES (1, 'WebRatio', '7.2.18');
INSERT INTO "public"."herramienta" ("oid", "nombre", "version") VALUES (2, 'Visual Studio', '16.3.1');
INSERT INTO "public"."herramienta" ("oid", "nombre", "version") VALUES (3, 'Ionic', '5.0.0');


--
-- Data for Name: lenguaje; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO "public"."lenguaje" ("oid", "nombre") VALUES (1, 'Java');
INSERT INTO "public"."lenguaje" ("oid", "nombre") VALUES (2, 'C++');
INSERT INTO "public"."lenguaje" ("oid", "nombre") VALUES (3, 'C#');
INSERT INTO "public"."lenguaje" ("oid", "nombre") VALUES (4, 'Swift');


--
-- Data for Name: tecnologia; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO "public"."tecnologia" ("oid", "tipo") VALUES (1, 'Web');
INSERT INTO "public"."tecnologia" ("oid", "tipo") VALUES (2, 'Multiplataforma');
INSERT INTO "public"."tecnologia" ("oid", "tipo") VALUES (3, 'Android');
INSERT INTO "public"."tecnologia" ("oid", "tipo") VALUES (4, 'ChatBot');


--
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO "public"."user" ("oid", "email", "password", "expiration_date", "code", "username", "group_oid") VALUES (3, 'cliente2@gmail.com', '123', NULL, NULL, 'cliente2', 1);
INSERT INTO "public"."user" ("oid", "email", "password", "expiration_date", "code", "username", "group_oid") VALUES (2, 'admin@gmail.com', 'JPdl0geNfhN56xaW1C8H+vf/+2PnLc4vWPklMEEfDAboPzVUeS7NAfmqRJJt0MLjRhhrnlVEjpnRD+MS2J1/tnFO3qU=', NULL, NULL, 'admin123', 2);
INSERT INTO "public"."user" ("oid", "email", "password", "expiration_date", "code", "username", "group_oid") VALUES (1, 'josemygel@gmail.com', 'oi1xn2fpaXU08fPF2wyYO/lKL2Ub3wiYa71Cw4ScXkBaFN8GesQpJJT+gk1H4JJIdNCptxf3zeym1C24sPKtcHhjAag=', NULL, NULL, 'usuario123', 1);


--
-- Data for Name: pedido; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO "public"."pedido" ("oid", "descripcion", "nombre", "estado", "tecnologia_oid", "user_oid", "herramienta_oid", "lenguaje_oid") VALUES (1, 'Necesito un proyecto para mi tienda de ruedas. Que muestre un listado, etc', 'Ruedas Pepe', NULL, 1, 1, 1, 1);
INSERT INTO "public"."pedido" ("oid", "descripcion", "nombre", "estado", "tecnologia_oid", "user_oid", "herramienta_oid", "lenguaje_oid") VALUES (2, 'quiero esto', 'nuevo pedido', 'Revision', 1, 1, 1, 1);


--
-- Data for Name: presupuesto; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO "public"."presupuesto" ("oid", "mensaje", "estado") VALUES (1, 'Presupuesto para Proyecto Ruedas Pepe', NULL);


--
-- Data for Name: lineapresupuesto; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO "public"."lineapresupuesto" ("oid", "tiempo", "pvp", "pedido_oid", "presupuesto_oid") VALUES (1, 150, 5000, 1, 1);


--
-- Data for Name: user_group; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- PostgreSQL database dump complete
--

