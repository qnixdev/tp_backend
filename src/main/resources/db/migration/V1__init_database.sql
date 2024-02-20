--
-- PostgreSQL database init
--
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
-- Name: min(uuid, uuid); Type: FUNCTION; Schema: public
--
CREATE FUNCTION public.min(uuid, uuid) RETURNS uuid
    LANGUAGE plpgsql IMMUTABLE PARALLEL SAFE
AS $_$
begin
    return least($1, $2);
end
$_$;
ALTER FUNCTION public.min(uuid, uuid) OWNER TO postgres;


--
-- Name: min(uuid); Type: AGGREGATE; Schema: public
--
CREATE AGGREGATE public.min(uuid) (
    SFUNC = public.min,
    STYPE = uuid,
    COMBINEFUNC = public.min,
    SORTOP = OPERATOR(pg_catalog.<),
    PARALLEL = safe
    );
ALTER AGGREGATE public.min(uuid) OWNER TO postgres;


SET default_tablespace = '';
SET default_table_access_method = heap;