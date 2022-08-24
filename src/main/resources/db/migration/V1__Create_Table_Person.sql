CREATE TABLE public.person (
    id SERIAL,
    first_name character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL,
    address character varying(255) NOT NULL,
    gender character varying(255) NOT NULL,
    CONSTRAINT person_pkey PRIMARY KEY (id)

);
