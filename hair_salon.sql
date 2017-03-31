--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.2
-- Dumped by pg_dump version 9.6.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: clients; Type: TABLE; Schema: public; Owner: nathanielmeyer
--

CREATE TABLE clients (
    id integer NOT NULL,
    name character varying,
    phone_number character varying,
    client_since timestamp without time zone,
    stylist_id integer
);


ALTER TABLE clients OWNER TO nathanielmeyer;

--
-- Name: clients_id_seq; Type: SEQUENCE; Schema: public; Owner: nathanielmeyer
--

CREATE SEQUENCE clients_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE clients_id_seq OWNER TO nathanielmeyer;

--
-- Name: clients_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: nathanielmeyer
--

ALTER SEQUENCE clients_id_seq OWNED BY clients.id;


--
-- Name: stylists; Type: TABLE; Schema: public; Owner: nathanielmeyer
--

CREATE TABLE stylists (
    id integer NOT NULL,
    name character varying,
    phone_number character varying,
    weeknights boolean,
    weekdays boolean,
    weekends boolean
);


ALTER TABLE stylists OWNER TO nathanielmeyer;

--
-- Name: stylists_id_seq; Type: SEQUENCE; Schema: public; Owner: nathanielmeyer
--

CREATE SEQUENCE stylists_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE stylists_id_seq OWNER TO nathanielmeyer;

--
-- Name: stylists_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: nathanielmeyer
--

ALTER SEQUENCE stylists_id_seq OWNED BY stylists.id;


--
-- Name: clients id; Type: DEFAULT; Schema: public; Owner: nathanielmeyer
--

ALTER TABLE ONLY clients ALTER COLUMN id SET DEFAULT nextval('clients_id_seq'::regclass);


--
-- Name: stylists id; Type: DEFAULT; Schema: public; Owner: nathanielmeyer
--

ALTER TABLE ONLY stylists ALTER COLUMN id SET DEFAULT nextval('stylists_id_seq'::regclass);


--
-- Data for Name: clients; Type: TABLE DATA; Schema: public; Owner: nathanielmeyer
--

COPY clients (id, name, phone_number, client_since, stylist_id) FROM stdin;
1	Lila	415-555-1212	2017-03-31 15:26:00.498	2
2	Ilana	415-765-4321	2010-08-03 15:26:00.498	3
3	Nathaniel	415-987-6543	2007-06-22 15:26:00.498	1
4	Agnes	415-876-5432	2010-01-01 15:26:00.498	2
5	Agatha	415-654-3210	2010-01-01 15:26:00.498	2
6	Jermaine	415-543-2109	2010-01-01 15:26:00.498	2
7	Jack	415-432-1098	2010-01-01 15:26:00.498	3
\.


--
-- Name: clients_id_seq; Type: SEQUENCE SET; Schema: public; Owner: nathanielmeyer
--

SELECT pg_catalog.setval('clients_id_seq', 7, true);


--
-- Data for Name: stylists; Type: TABLE DATA; Schema: public; Owner: nathanielmeyer
--

COPY stylists (id, name, phone_number, weeknights, weekdays, weekends) FROM stdin;
1	NO STYLIST		f	f	f
2	Kelsi	415-555-1313	t	t	f
3	Mysti	415-555-4444	f	f	t
\.


--
-- Name: stylists_id_seq; Type: SEQUENCE SET; Schema: public; Owner: nathanielmeyer
--

SELECT pg_catalog.setval('stylists_id_seq', 3, true);


--
-- Name: clients clients_pkey; Type: CONSTRAINT; Schema: public; Owner: nathanielmeyer
--

ALTER TABLE ONLY clients
    ADD CONSTRAINT clients_pkey PRIMARY KEY (id);


--
-- Name: stylists stylists_pkey; Type: CONSTRAINT; Schema: public; Owner: nathanielmeyer
--

ALTER TABLE ONLY stylists
    ADD CONSTRAINT stylists_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

