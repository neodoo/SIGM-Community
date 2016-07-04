-- GENERAR LA VISTA DE INFORMES DE CERTIFICADOS PARA REGISTROS DE ENTRADA
CREATE OR REPLACE FORCE VIEW SCR_IREGREPORTSCERT 
AS
    SELECT 1 AS IDBOOK, FDRID, TIMESTAMP, FLD1, FLD2, FLD3, FLD4, FLD5, OFIC.NAME AS FLD5_TEXT, 
        DIROFIC.ADDRESS AS FLD5_ADDRESS, DIROFIC.CITY AS FLD5_CITY, DIROFIC.ZIP AS FLD5_ZIP, 
        DIROFIC.COUNTRY AS FLD5_COUNTRY, DIROFIC.TELEPHONE AS FLD5_TELEPHONE, DIROFIC.FAX AS FLD5_FAX, 
        DIROFIC.EMAIL AS FLD5_EMAIL, FLD6, FLD7, NVL2(ORG.NAME,  CONCAT(CONCAT(ORG.CODE,'-'), ORG.NAME), '') AS FLD7_TEXT, 
        FLD8, NVL2(ORG.NAME,  CONCAT(CONCAT(ORG.CODE,'-'), ORG.NAME), '') AS FLD8_TEXT, FLD9, FLD10, FLD11, FLD12, 
        FLD13, ORG.CODE AS FLD13_TEXT, FLD14, FLD15, FLD16, 
        NVL2(CA.MATTER, CONCAT(CONCAT(CA.CODE,'-'), CA.MATTER), '') AS FLD16_TEXT, FLD17, FLD19, FLD20 
    FROM A1SF
        LEFT JOIN SCR_OFIC OFIC ON OFIC.ID = A1SF.FLD5 
        LEFT JOIN SCR_DIROFIC DIROFIC ON DIROFIC.ID_OFIC = A1SF.FLD5 
        LEFT JOIN SCR_ORGS ORG ON ORG.ID = A1SF.FLD7 
        LEFT JOIN SCR_ORGS ORG2 ON ORG2.ID = A1SF.FLD8 
        LEFT JOIN SCR_CA CA ON CA.ID = A1SF.FLD16
    UNION 
    SELECT 5 AS IDBOOK, FDRID, TIMESTAMP, FLD1, FLD2, FLD3, FLD4, FLD5, OFIC.NAME AS FLD5_TEXT, 
        DIROFIC.ADDRESS AS FLD5_ADDRESS, DIROFIC.CITY AS FLD5_CITY, DIROFIC.ZIP AS FLD5_ZIP, 
        DIROFIC.COUNTRY AS FLD5_COUNTRY, DIROFIC.TELEPHONE AS FLD5_TELEPHONE, DIROFIC.FAX AS FLD5_FAX, 
        DIROFIC.EMAIL AS FLD5_EMAIL, FLD6, FLD7, NVL2(ORG.NAME,  CONCAT(CONCAT(ORG.CODE,'-'), ORG.NAME), '') AS FLD7_TEXT, 
        FLD8, NVL2(ORG.NAME,  CONCAT(CONCAT(ORG.CODE,'-'), ORG.NAME), '') AS FLD8_TEXT, FLD9, FLD10, FLD11, FLD12, 
        FLD13, ORG.CODE AS FLD13_TEXT, FLD14, FLD15, FLD16, 
        NVL2(CA.MATTER, CONCAT(CONCAT(CA.CODE,'-'), CA.MATTER), '') AS FLD16_TEXT, FLD17, FLD19, FLD20 
    FROM A5SF 
        LEFT JOIN SCR_OFIC OFIC ON OFIC.ID = A5SF.FLD5 
        LEFT JOIN SCR_DIROFIC DIROFIC ON DIROFIC.ID_OFIC = A5SF.FLD5 
        LEFT JOIN SCR_ORGS ORG ON ORG.ID = A5SF.FLD7 
        LEFT JOIN SCR_ORGS ORG2 ON ORG2.ID = A5SF.FLD8 
        LEFT JOIN SCR_CA CA ON CA.ID = A5SF.FLD16 
    UNION 
    SELECT 6 AS IDBOOK, FDRID, TIMESTAMP, FLD1, FLD2, FLD3, FLD4, FLD5, OFIC.NAME AS FLD5_TEXT, 
        DIROFIC.ADDRESS AS FLD5_ADDRESS, DIROFIC.CITY AS FLD5_CITY, DIROFIC.ZIP AS FLD5_ZIP, 
        DIROFIC.COUNTRY AS FLD5_COUNTRY, DIROFIC.TELEPHONE AS FLD5_TELEPHONE, DIROFIC.FAX AS FLD5_FAX, 
        DIROFIC.EMAIL AS FLD5_EMAIL, FLD6, FLD7, NVL2(ORG.NAME,  CONCAT(CONCAT(ORG.CODE,'-'), ORG.NAME), '') AS FLD7_TEXT, 
        FLD8, NVL2(ORG.NAME,  CONCAT(CONCAT(ORG.CODE,'-'), ORG.NAME), '') AS FLD8_TEXT, FLD9, FLD10, FLD11, FLD12, 
        FLD13, ORG.CODE AS FLD13_TEXT, FLD14, FLD15, FLD16, 
        NVL2(CA.MATTER, CONCAT(CONCAT(CA.CODE,'-'), CA.MATTER), '') AS FLD16_TEXT, FLD17, FLD19, FLD20 
    FROM A6SF 
        LEFT JOIN SCR_OFIC OFIC ON OFIC.ID = A6SF.FLD5 
        LEFT JOIN SCR_DIROFIC DIROFIC ON DIROFIC.ID_OFIC = A6SF.FLD5 
        LEFT JOIN SCR_ORGS ORG ON ORG.ID = A6SF.FLD7 
        LEFT JOIN SCR_ORGS ORG2 ON ORG2.ID = A6SF.FLD8 
        LEFT JOIN SCR_CA CA ON CA.ID = A6SF.FLD16;

-- GENERAR LA VISTA DE INFORMES DE CERTIFICADOS PARA REGISTROS DE SALIDA
CREATE OR REPLACE FORCE VIEW SCR_OREGREPORTSCERT
AS 
    SELECT 2 AS IDBOOK, FDRID, TIMESTAMP, FLD1, FLD2, FLD3, FLD4, FLD5, OFIC.NAME AS FLD5_TEXT, 
        DIROFIC.ADDRESS AS FLD5_ADDRESS, DIROFIC.CITY AS FLD5_CITY, DIROFIC.ZIP AS FLD5_ZIP, 
        DIROFIC.COUNTRY AS FLD5_COUNTRY, DIROFIC.TELEPHONE AS FLD5_TELEPHONE, DIROFIC.FAX AS FLD5_FAX, 
        DIROFIC.EMAIL AS FLD5_EMAIL, FLD6, FLD7, NVL2(ORG.NAME,  CONCAT(CONCAT(ORG.CODE,'-'), ORG.NAME), '') AS FLD7_TEXT, 
        FLD8, NVL2(ORG.NAME,  CONCAT(CONCAT(ORG.CODE,'-'), ORG.NAME), '') AS FLD8_TEXT, FLD9, FLD10, FLD11, FLD12, 
        NVL2(CA.MATTER, CONCAT(CONCAT(CA.CODE,'-'), CA.MATTER), '') AS FLD12_TEXT, FLD13, FLD15 
    FROM A2SF 
        LEFT JOIN SCR_OFIC OFIC ON OFIC.ID = A2SF.FLD5 
        LEFT JOIN SCR_DIROFIC DIROFIC ON DIROFIC.ID_OFIC = A2SF.FLD5 
        LEFT JOIN SCR_ORGS ORG ON ORG.ID = A2SF.FLD7 
        LEFT JOIN SCR_ORGS ORG2 ON ORG2.ID = A2SF.FLD8 
        LEFT JOIN SCR_CA CA ON CA.ID = A2SF.FLD12
    UNION 
    SELECT 7 AS IDBOOK, FDRID, TIMESTAMP, FLD1, FLD2, FLD3, FLD4, FLD5, OFIC.NAME AS FLD5_TEXT, 
        DIROFIC.ADDRESS AS FLD5_ADDRESS, DIROFIC.CITY AS FLD5_CITY, DIROFIC.ZIP AS FLD5_ZIP, 
        DIROFIC.COUNTRY AS FLD5_COUNTRY, DIROFIC.TELEPHONE AS FLD5_TELEPHONE, DIROFIC.FAX AS FLD5_FAX, 
        DIROFIC.EMAIL AS FLD5_EMAIL, FLD6, FLD7, NVL2(ORG.NAME,  CONCAT(CONCAT(ORG.CODE,'-'), ORG.NAME), '') AS FLD7_TEXT, 
        FLD8, NVL2(ORG.NAME,  CONCAT(CONCAT(ORG.CODE,'-'), ORG.NAME), '') AS FLD8_TEXT, FLD9, FLD10, FLD11, FLD12, 
        NVL2(CA.MATTER, CONCAT(CONCAT(CA.CODE,'-'), CA.MATTER), '') AS FLD12_TEXT, FLD13, FLD15 
    FROM A7SF 
        LEFT JOIN SCR_OFIC OFIC ON OFIC.ID = A7SF.FLD5 
        LEFT JOIN SCR_DIROFIC DIROFIC ON DIROFIC.ID_OFIC = A7SF.FLD5 
        LEFT JOIN SCR_ORGS ORG ON ORG.ID = A7SF.FLD7
        LEFT JOIN SCR_ORGS ORG2 ON ORG2.ID = A7SF.FLD8
        LEFT JOIN SCR_CA CA ON CA.ID = A7SF.FLD12;