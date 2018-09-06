DROP PROCEDURE IF EXISTS CREATE_PORTAL_USER;
CREATE PROCEDURE CREATE_PORTAL_USER (
	IN USER_NAME VARCHAR(40)
	, IN USER_PASSWORD	VARCHAR(40)
	, IN USER_REAL_NAME	VARCHAR(40)
	, IN USER_STATE	INT
	, IN JOB_ID	INT
	, IN USER_ADDR	VARCHAR(80)
	, IN USER_TEL	VARCHAR(20)
	, IN USER_GLOBAL_ID	INT
	, IN ROLE_ID	INT
	, IN DEP_ID	INT
  , OUT ERROR_NO INT
)
BEGIN
	DECLARE NEW_USER_ID INT;
	DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET ERROR_NO=0;
	SET ERROR_NO = 1;

	START TRANSACTION;

	INSERT INTO SYS_USER (
    		  USER_NAME,
    		  USER_PASSWORD,
    		  USER_REAL_NAME,
    		  USER_STATE,
    		  JOB_ID,
    		  USER_ADDR,
    		  USER_TEL,
    		  CRE_TIME,
    		  UPD_TIME
	) VALUES (
			USER_NAME
			,USER_PASSWORD
			,USER_REAL_NAME
			,USER_STATE
			,JOB_ID
			,USER_ADDR
			,USER_TEL
			,NOW()
			,NOW()
	);

	SET NEW_USER_ID =LAST_INSERT_ID();

	INSERT INTO USER_ROLE_RELA (
			USER_ID
			,ROLE_ID
			,CRE_TIME
			,UPD_TIME
		) VALUES (NEW_USER_ID, ROLE_ID, NOW(), NOW());

	INSERT INTO USER_DEP_RELA (
			USER_ID, DEP_ID
		) VALUES (NEW_USER_ID, DEP_ID);

	IF ERROR_NO = 0 THEN
		ROLLBACK;
	ELSE
		COMMIT;
	END IF;

END;