    CREATE OR REPLACE PACKAGE PKG_ORDENES AS
        PROCEDURE CREAR_ORDEN(
            P_CLIENTE IN VARCHAR2,
            P_PRODUCTOS IN VARCHAR2,
            P_ID_ORDEN OUT NUMBER
        );

        PROCEDURE CONSULTAR_ORDENES(
            P_CLIENTE IN VARCHAR2,
            P_FECHA_INICIO IN DATE,
            P_FECHA_FIN IN DATE,
            P_CURSOR OUT SYS_REFCURSOR
        );
    END PKG_ORDENES;
    /
    CREATE OR REPLACE PACKAGE BODY PKG_ORDENES AS

        PROCEDURE CREAR_ORDEN(
            P_CLIENTE IN VARCHAR2,
            P_PRODUCTOS IN VARCHAR2,
            P_ID_ORDEN OUT NUMBER
        ) AS
        BEGIN
            INSERT INTO ORDEN (CLIENTE, FECHA_ORDEN)
            VALUES (P_CLIENTE, SYSDATE)
            RETURNING ID_ORDEN INTO P_ID_ORDEN;


            FOR r IN (
                SELECT REGEXP_SUBSTR(P_PRODUCTOS, '[^|]+', 1, LEVEL) AS ITEM
                FROM DUAL
                CONNECT BY REGEXP_SUBSTR(P_PRODUCTOS, '[^|]+', 1, LEVEL) IS NOT NULL
            ) LOOP
                INSERT INTO ORDEN_DETALLE (ID_ORDEN, ID_PRODUCTO, CANTIDAD)
                VALUES (
                    P_ID_ORDEN,
                    TO_NUMBER(REGEXP_SUBSTR(r.ITEM, '[^:]+', 1, 1)),
                    TO_NUMBER(REGEXP_SUBSTR(r.ITEM, '[^:]+', 1, 2))
                );
            END LOOP;
        END;

        --------------------------------------------------------------------

    PROCEDURE CONSULTAR_ORDENES(
        P_CLIENTE IN VARCHAR2,
        P_FECHA_INICIO IN DATE,
        P_FECHA_FIN IN DATE,
        P_CURSOR OUT SYS_REFCURSOR
    ) AS
    BEGIN
        OPEN P_CURSOR FOR
            SELECT O.ID_ORDEN, 
                O.CLIENTE, 
                O.FECHA_ORDEN,
                OD.ID_PRODUCTO, 
                P.NOMBRE AS NOMBRE_PRODUCTO,
                OD.CANTIDAD
            FROM ORDEN O
            JOIN ORDEN_DETALLE OD ON O.ID_ORDEN = OD.ID_ORDEN
            JOIN PRODUCTO P ON OD.ID_PRODUCTO = P.ID_PRODUCTO
            WHERE O.CLIENTE LIKE '%'||P_CLIENTE||'%'
            AND O.FECHA_ORDEN BETWEEN P_FECHA_INICIO AND P_FECHA_FIN;
    END;

    END PKG_ORDENES;
    /
