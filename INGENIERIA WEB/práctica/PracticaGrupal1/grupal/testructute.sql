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
-- Name: SCHEMA "public"; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA "public" IS 'standard public schema';


--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS "plpgsql" WITH SCHEMA "pg_catalog";


--
-- Name: EXTENSION "plpgsql"; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION "plpgsql" IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: group; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "public"."group" (
    "oid" integer NOT NULL,
    "groupname" character varying(255),
    "module_oid" integer
);


ALTER TABLE "public"."group" OWNER TO "postgres";

--
-- Name: group_module; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "public"."group_module" (
    "group_oid" integer NOT NULL,
    "module_oid" integer NOT NULL
);


ALTER TABLE "public"."group_module" OWNER TO "postgres";

--
-- Name: herramienta; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "public"."herramienta" (
    "oid" integer NOT NULL,
    "nombre" character varying(255),
    "version" character varying(255)
);


ALTER TABLE "public"."herramienta" OWNER TO "postgres";

--
-- Name: lenguaje; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "public"."lenguaje" (
    "oid" integer NOT NULL,
    "nombre" character varying(255)
);


ALTER TABLE "public"."lenguaje" OWNER TO "postgres";

--
-- Name: lineapresupuesto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "public"."lineapresupuesto" (
    "oid" integer NOT NULL,
    "tiempo" integer,
    "pvp" double precision,
    "pedido_oid" integer,
    "presupuesto_oid" integer
);


ALTER TABLE "public"."lineapresupuesto" OWNER TO "postgres";

--
-- Name: module; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "public"."module" (
    "oid" integer NOT NULL,
    "moduleid" character varying(255),
    "modulename" character varying(255)
);


ALTER TABLE "public"."module" OWNER TO "postgres";

--
-- Name: pedido; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "public"."pedido" (
    "oid" integer NOT NULL,
    "descripcion" character varying(255),
    "nombre" character varying(255),
    "tecnologia_oid" integer,
    "user_oid" integer,
    "herramienta_oid" integer,
    "lenguaje_oid" integer
);


ALTER TABLE "public"."pedido" OWNER TO "postgres";

--
-- Name: presupuesto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "public"."presupuesto" (
    "oid" integer NOT NULL,
    "mensaje" character varying(255)
);


ALTER TABLE "public"."presupuesto" OWNER TO "postgres";

--
-- Name: presupuesto_subtotal_view; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW "public"."presupuesto_subtotal_view" AS
 SELECT "al1"."oid",
    "sum"("al2"."pvp") AS "der_attr"
   FROM ("public"."presupuesto" "al1"
     LEFT JOIN "public"."lineapresupuesto" "al2" ON (("al1"."oid" = "al2"."presupuesto_oid")))
  GROUP BY "al1"."oid";


ALTER TABLE "public"."presupuesto_subtotal_view" OWNER TO "postgres";

--
-- Name: presupuesto_tax_view; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW "public"."presupuesto_tax_view" AS
 SELECT "al1"."oid",
    ((0.21)::double precision * "al2"."der_attr") AS "der_attr"
   FROM ("public"."presupuesto" "al1"
     LEFT JOIN "public"."presupuesto_subtotal_view" "al2" ON (("al1"."oid" = "al2"."oid")));


ALTER TABLE "public"."presupuesto_tax_view" OWNER TO "postgres";

--
-- Name: presupuesto_total_view; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW "public"."presupuesto_total_view" AS
 SELECT "al1"."oid",
    ("al2"."der_attr" + "al3"."der_attr") AS "der_attr"
   FROM (("public"."presupuesto" "al1"
     LEFT JOIN "public"."presupuesto_subtotal_view" "al2" ON (("al1"."oid" = "al2"."oid")))
     LEFT JOIN "public"."presupuesto_tax_view" "al3" ON (("al1"."oid" = "al3"."oid")));


ALTER TABLE "public"."presupuesto_total_view" OWNER TO "postgres";

--
-- Name: tecnologia; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "public"."tecnologia" (
    "oid" integer NOT NULL,
    "tipo" character varying(255)
);


ALTER TABLE "public"."tecnologia" OWNER TO "postgres";

--
-- Name: user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "public"."user" (
    "oid" integer NOT NULL,
    "username" character varying(255),
    "password" character varying(255),
    "email" character varying(255),
    "group_oid" integer,
    "expiration_date" "date",
    "code" character varying(255)
);


ALTER TABLE "public"."user" OWNER TO "postgres";

--
-- Name: user_group; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "public"."user_group" (
    "user_oid" integer NOT NULL,
    "group_oid" integer NOT NULL
);


ALTER TABLE "public"."user_group" OWNER TO "postgres";

--
-- Name: group_module group_module_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "public"."group_module"
    ADD CONSTRAINT "group_module_pkey" PRIMARY KEY ("group_oid", "module_oid");


--
-- Name: group group_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "public"."group"
    ADD CONSTRAINT "group_pkey" PRIMARY KEY ("oid");


--
-- Name: herramienta herramienta_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "public"."herramienta"
    ADD CONSTRAINT "herramienta_pkey" PRIMARY KEY ("oid");


--
-- Name: lenguaje lenguaje_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "public"."lenguaje"
    ADD CONSTRAINT "lenguaje_pkey" PRIMARY KEY ("oid");


--
-- Name: lineapresupuesto lineapresupuesto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "public"."lineapresupuesto"
    ADD CONSTRAINT "lineapresupuesto_pkey" PRIMARY KEY ("oid");


--
-- Name: module module_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "public"."module"
    ADD CONSTRAINT "module_pkey" PRIMARY KEY ("oid");


--
-- Name: pedido pedido_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "public"."pedido"
    ADD CONSTRAINT "pedido_pkey" PRIMARY KEY ("oid");


--
-- Name: presupuesto presupuesto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "public"."presupuesto"
    ADD CONSTRAINT "presupuesto_pkey" PRIMARY KEY ("oid");


--
-- Name: tecnologia tecnologia_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "public"."tecnologia"
    ADD CONSTRAINT "tecnologia_pkey" PRIMARY KEY ("oid");


--
-- Name: user_group user_group_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "public"."user_group"
    ADD CONSTRAINT "user_group_pkey" PRIMARY KEY ("user_oid", "group_oid");


--
-- Name: user user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "public"."user"
    ADD CONSTRAINT "user_pkey" PRIMARY KEY ("oid");


--
-- Name: group fk_group_module; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "public"."group"
    ADD CONSTRAINT "fk_group_module" FOREIGN KEY ("module_oid") REFERENCES "public"."module"("oid");


--
-- Name: group_module fk_group_module_group; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "public"."group_module"
    ADD CONSTRAINT "fk_group_module_group" FOREIGN KEY ("group_oid") REFERENCES "public"."group"("oid");


--
-- Name: group_module fk_group_module_module; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "public"."group_module"
    ADD CONSTRAINT "fk_group_module_module" FOREIGN KEY ("module_oid") REFERENCES "public"."module"("oid");


--
-- Name: lineapresupuesto fk_lineapresupuesto_pedido; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "public"."lineapresupuesto"
    ADD CONSTRAINT "fk_lineapresupuesto_pedido" FOREIGN KEY ("pedido_oid") REFERENCES "public"."pedido"("oid");


--
-- Name: lineapresupuesto fk_lineapresupuesto_presupuest; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "public"."lineapresupuesto"
    ADD CONSTRAINT "fk_lineapresupuesto_presupuest" FOREIGN KEY ("presupuesto_oid") REFERENCES "public"."presupuesto"("oid");


--
-- Name: pedido fk_pedido_herramienta; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "public"."pedido"
    ADD CONSTRAINT "fk_pedido_herramienta" FOREIGN KEY ("herramienta_oid") REFERENCES "public"."herramienta"("oid");


--
-- Name: pedido fk_pedido_lenguaje; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "public"."pedido"
    ADD CONSTRAINT "fk_pedido_lenguaje" FOREIGN KEY ("lenguaje_oid") REFERENCES "public"."lenguaje"("oid");


--
-- Name: pedido fk_pedido_tecnologia; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "public"."pedido"
    ADD CONSTRAINT "fk_pedido_tecnologia" FOREIGN KEY ("tecnologia_oid") REFERENCES "public"."tecnologia"("oid");


--
-- Name: pedido fk_pedido_user; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "public"."pedido"
    ADD CONSTRAINT "fk_pedido_user" FOREIGN KEY ("user_oid") REFERENCES "public"."user"("oid");


--
-- Name: user fk_user_group; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "public"."user"
    ADD CONSTRAINT "fk_user_group" FOREIGN KEY ("group_oid") REFERENCES "public"."group"("oid");


--
-- Name: user_group fk_user_group_group; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "public"."user_group"
    ADD CONSTRAINT "fk_user_group_group" FOREIGN KEY ("group_oid") REFERENCES "public"."group"("oid");


--
-- Name: user_group fk_user_group_user; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "public"."user_group"
    ADD CONSTRAINT "fk_user_group_user" FOREIGN KEY ("user_oid") REFERENCES "public"."user"("oid");


--
-- PostgreSQL database dump complete
--

