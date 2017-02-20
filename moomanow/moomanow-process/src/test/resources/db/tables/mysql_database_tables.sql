CREATE TABLE IF NOT EXISTS SYS_M_ACTION (
ID_ACTION BIGINT AUTO_INCREMENT PRIMARY KEY,
PAGE_NAME VARCHAR(50) NOT NULL,
ACTION_NAME VARCHAR(255) DEFAULT NULL,
NAME_SPACE VARCHAR(50) DEFAULT NULL,
URL VARCHAR(255) DEFAULT NULL,
TYPE CHAR(1) NOT NULL,
ID_MENU BIGINT DEFAULT NULL,
ID_FUNCTION VARCHAR(32) NOT NULL,
INPUT_RESULT_NAME VARCHAR(255) DEFAULT NULL,
STATUS CHAR(1) DEFAULT 'A',
CREATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
CREATE_USER VARCHAR(255) DEFAULT NULL,
UPDATE_DATE TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
UPDATE_USER VARCHAR(255) DEFAULT NULL
);

/*Table structure for table SYS_M_ADDRESS */

CREATE TABLE IF NOT EXISTS SYS_M_ADDRESS (
ID_ADDRESS BIGINT AUTO_INCREMENT PRIMARY KEY,
HOUSING_NO VARCHAR(255) DEFAULT NULL,
STREET_ADDRESS1 VARCHAR(255) DEFAULT NULL,
STREET_ADDRESS2 VARCHAR(255) DEFAULT NULL,
ID_COUNTRY BIGINT DEFAULT NULL,
ID_PROVINCE BIGINT DEFAULT NULL,
ID_CITY BIGINT DEFAULT NULL,
ID_COUNTY BIGINT DEFAULT NULL,
ZIPCODE VARCHAR(255) DEFAULT NULL,
LAT VARCHAR(255) DEFAULT NULL,
LNG VARCHAR(255) DEFAULT NULL,
DESCRIPTION TEXT,
STATUS CHAR(1) DEFAULT 'A',
CREATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
CREATE_USER VARCHAR(255) DEFAULT NULL,
UPDATE_DATE TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
UPDATE_USER VARCHAR(255) DEFAULT NULL
);

/*Table structure for table SYS_M_ADDRESS_LANG */

CREATE TABLE IF NOT EXISTS SYS_M_ADDRESS_LANG (
ID_ADDRESS_LANG BIGINT AUTO_INCREMENT PRIMARY KEY,
ID_ADDRESS BIGINT NOT NULL,
LANG_CODE3 VARCHAR(3) NOT NULL,
HOUSING_NO VARCHAR(255) DEFAULT NULL,
STREET_ADDRESS1 VARCHAR(255) DEFAULT NULL,
STREET_ADDRESS2 VARCHAR(255) DEFAULT NULL,
ZIPCODE VARCHAR(255) DEFAULT NULL,
DESCRIPTION TEXT,
STATUS CHAR(1) DEFAULT 'A',
CREATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
CREATE_USER VARCHAR(255) DEFAULT NULL,
UPDATE_DATE TIMESTAMP NULL DEFAULT NULL,
UPDATE_USER VARCHAR(255) DEFAULT NULL
);

/*Table structure for table SYS_M_ADDRESS_MAP_PHONE */

CREATE TABLE IF NOT EXISTS SYS_M_ADDRESS_MAP_PHONE (
ID_ADDRESS_MAP_PHONE BIGINT AUTO_INCREMENT PRIMARY KEY,
ID_ADDRESS BIGINT NOT NULL,
ID_PHONE BIGINT NOT NULL,
STATUS CHAR(1) DEFAULT 'A',
CREATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
CREATE_USER VARCHAR(255) DEFAULT NULL,
UPDATE_DATE TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
UPDATE_USER VARCHAR(255) DEFAULT NULL
);

/*Table structure for table SYS_M_CITY */

CREATE TABLE IF NOT EXISTS SYS_M_CITY (
ID_COUNTRY BIGINT NOT NULL,
ID_PROVINCE BIGINT NOT NULL,
ID_CITY BIGINT,
NAME VARCHAR(255) DEFAULT NULL,
INDEX_ROW BIGINT DEFAULT NULL,
CODE VARCHAR(255) DEFAULT NULL,
CODE2 VARCHAR(2) DEFAULT NULL,
CODE3 VARCHAR(3) DEFAULT NULL,
STATUS CHAR(1) DEFAULT 'A',
CREATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
CREATE_USER VARCHAR(255) DEFAULT NULL,
UPDATE_DATE TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
UPDATE_USER VARCHAR(255) DEFAULT NULL,
PRIMARY KEY (ID_COUNTRY,ID_PROVINCE,ID_CITY)
);
ALTER TABLE SYS_M_CITY CHANGE ID_CITY ID_CITY BIGINT(20) NOT NULL AUTO_INCREMENT, ADD KEY(ID_CITY);
/*Table structure for table SYS_M_CITY_LANG */

CREATE TABLE IF NOT EXISTS SYS_M_CITY_LANG (
ID_CITY_LANG BIGINT AUTO_INCREMENT PRIMARY KEY,
ID_COUNTRY BIGINT DEFAULT NULL,
ID_PROVINCE BIGINT DEFAULT NULL,
ID_CITY BIGINT DEFAULT NULL,
LANG_CODE3 VARCHAR(3) DEFAULT NULL,
NAME VARCHAR(255) DEFAULT NULL,
STATUS CHAR(1) DEFAULT 'A',
CREATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
CREATE_USER VARCHAR(255) DEFAULT NULL,
UPDATE_DATE TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
UPDATE_USER VARCHAR(255) DEFAULT NULL
);

/*Table structure for table SYS_M_CMS */

CREATE TABLE IF NOT EXISTS SYS_M_CMS (
LANG_CODE3 VARCHAR(3) NOT NULL,
PAGE_NAME VARCHAR(255) NOT NULL,
CONTENT TEXT,
STATUS CHAR(1) DEFAULT 'A',
CREATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
CREATE_USER VARCHAR(255) DEFAULT NULL,
UPDATE_DATE TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
UPDATE_USER VARCHAR(255) DEFAULT NULL,
PRIMARY KEY (LANG_CODE3,PAGE_NAME)
);

/*Table structure for table SYS_M_CONFIG */

CREATE TABLE IF NOT EXISTS SYS_M_CONFIG (
CONFIG_KEY VARCHAR(100) NOT NULL,
CONFIG_VALUE TEXT NOT NULL,
CONFIG_GROUP VARCHAR(100) NOT NULL,
`DESC` VARCHAR(255),
STATUS CHAR(1) DEFAULT 'A',
CREATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
CREATE_USER VARCHAR(255) DEFAULT NULL,
UPDATE_DATE TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
UPDATE_USER VARCHAR(255) DEFAULT NULL,
PRIMARY KEY (CONFIG_KEY)
);

/*Table structure for table SYS_M_CONFIG_BY_CODE */

CREATE TABLE IF NOT EXISTS SYS_M_CONFIG_BY_CODE (
CONFIG_KEY VARCHAR(100) NOT NULL,
CONFIG_CODE BIGINT NOT NULL,
CONFIG_VALUE VARCHAR(255) NOT NULL,
CONFIG_GROUP VARCHAR(100) NOT NULL,
`DESC` VARCHAR(255) DEFAULT NULL,
STATUS CHAR(1) DEFAULT 'A',
CREATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
CREATE_USER VARCHAR(255) DEFAULT NULL,
UPDATE_DATE TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
UPDATE_USER VARCHAR(255) DEFAULT NULL,
PRIMARY KEY (CONFIG_KEY,CONFIG_CODE)
);

/*Table structure for table SYS_M_COUNTRY */

CREATE TABLE IF NOT EXISTS SYS_M_COUNTRY (
ID_COUNTRY BIGINT AUTO_INCREMENT,
NAME VARCHAR(255) DEFAULT NULL,
CODE2 VARCHAR(2) DEFAULT NULL,
CODE3 VARCHAR(3) DEFAULT NULL,
ID_REGION BIGINT DEFAULT NULL,
ID_LANGUAGE BIGINT DEFAULT NULL,
ID_CURRENCY BIGINT DEFAULT NULL,
PHONE_CODE VARCHAR(10) DEFAULT NULL,
STATUS CHAR(1) DEFAULT 'A',
CREATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
CREATE_USER VARCHAR(255) DEFAULT NULL,
UPDATE_DATE TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
UPDATE_USER VARCHAR(255) DEFAULT NULL,
PRIMARY KEY (ID_COUNTRY)
);

/*Table structure for table SYS_M_COUNTRY_LANG */

CREATE TABLE IF NOT EXISTS SYS_M_COUNTRY_LANG (
ID_COUNTRY_LANG BIGINT AUTO_INCREMENT PRIMARY KEY,
ID_COUNTRY BIGINT DEFAULT NULL,
LANG_CODE3 VARCHAR(3) DEFAULT NULL,
NAME VARCHAR(255) DEFAULT NULL,
STATUS CHAR(1) DEFAULT 'A',
CREATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
CREATE_USER VARCHAR(255) DEFAULT NULL,
UPDATE_DATE TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
UPDATE_USER VARCHAR(255) DEFAULT NULL
);

/*Table structure for table SYS_M_COUNTRY_MAP_PHONE_CODE */

CREATE TABLE IF NOT EXISTS SYS_M_COUNTRY_MAP_PHONE_CODE (
ID_COUNTRY_MAP_PHONE_CODE BIGINT AUTO_INCREMENT PRIMARY KEY,
ID_COUNTRY BIGINT DEFAULT NULL,
ID_PHONE_CODE BIGINT DEFAULT NULL,
STATUS CHAR(1) DEFAULT 'A',
CREATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
CREATE_USER VARCHAR(255) DEFAULT NULL,
UPDATE_DATE TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
UPDATE_USER VARCHAR(255) DEFAULT NULL
);

/*Table structure for table SYS_M_COUNTY */

CREATE TABLE IF NOT EXISTS SYS_M_COUNTY (
ID_COUNTRY BIGINT NOT NULL,
ID_PROVINCE BIGINT NOT NULL,
ID_CITY BIGINT NOT NULL,
ID_COUNTY BIGINT,
NAME VARCHAR(255) DEFAULT NULL,
INDEX_ROW BIGINT DEFAULT NULL,
CODE VARCHAR(255) DEFAULT NULL,
CODE2 VARCHAR(2) DEFAULT NULL,
STATUS CHAR(1) DEFAULT 'A',
CREATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
CREATE_USER VARCHAR(255) DEFAULT NULL,
UPDATE_DATE TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
UPDATE_USER VARCHAR(255) DEFAULT NULL,
PRIMARY KEY (ID_COUNTRY,ID_PROVINCE,ID_CITY,ID_COUNTY)
);
ALTER TABLE SYS_M_COUNTY CHANGE ID_COUNTY ID_COUNTY BIGINT(20) NOT NULL AUTO_INCREMENT, ADD KEY(ID_COUNTY);
/*Table structure for table SYS_M_COUNTY_LANG */

CREATE TABLE IF NOT EXISTS SYS_M_COUNTY_LANG (
ID_COUNTY_LANG BIGINT AUTO_INCREMENT PRIMARY KEY,
ID_COUNTRY BIGINT DEFAULT NULL,
ID_PROVINCE BIGINT DEFAULT NULL,
ID_CITY BIGINT DEFAULT NULL,
ID_COUNTY BIGINT DEFAULT NULL,
LANG_CODE3 VARCHAR(3) DEFAULT NULL,
NAME VARCHAR(255) DEFAULT NULL,
STATUS CHAR(1) DEFAULT 'A',
CREATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
CREATE_USER VARCHAR(255) DEFAULT NULL,
UPDATE_DATE TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
UPDATE_USER VARCHAR(255) DEFAULT NULL
);

/*Table structure for table SYS_M_CURRENCY */

CREATE TABLE IF NOT EXISTS SYS_M_CURRENCY (
ID_CURRENCY BIGINT AUTO_INCREMENT PRIMARY KEY,
SEQ SMALLINT(6) DEFAULT NULL,
CODE3 CHAR(3) DEFAULT NULL,
NAME VARCHAR(255) DEFAULT NULL,
STATUS CHAR(1) DEFAULT 'A',
CREATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
CREATE_USER VARCHAR(255) DEFAULT NULL,
UPDATE_DATE TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
UPDATE_USER VARCHAR(255) DEFAULT NULL
);

/*Table structure for table SYS_M_CURRENCY_LANG */

CREATE TABLE IF NOT EXISTS SYS_M_CURRENCY_LANG (
ID_CURRENCY_LANG BIGINT AUTO_INCREMENT PRIMARY KEY,
ID_CURRENCY BIGINT DEFAULT NULL,
LANG_CODE3 VARCHAR(3) NOT NULL,
NAME VARCHAR(255) DEFAULT NULL,
STATUS CHAR(1) DEFAULT 'A',
CREATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
CREATE_USER VARCHAR(255) DEFAULT NULL,
UPDATE_DATE TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
UPDATE_USER VARCHAR(255) DEFAULT NULL
);

/*Table structure for table SYS_M_CURRENCY_USD_RATE */

CREATE TABLE IF NOT EXISTS SYS_M_CURRENCY_USD_RATE (
ID_CURRENCY_USD_RATE BIGINT AUTO_INCREMENT PRIMARY KEY,
ID_CURRENCY BIGINT NOT NULL,
SEQ SMALLINT(6) DEFAULT NULL,
CODE3 CHAR(3) DEFAULT NULL,
RATE DECIMAL(20,7) DEFAULT NULL,
STATUS CHAR(1) DEFAULT 'A',
CREATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
CREATE_USER VARCHAR(255) DEFAULT NULL,
UPDATE_DATE TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
UPDATE_USER VARCHAR(255) DEFAULT NULL
);

/*Table structure for table SYS_M_FIELD_HELP */

CREATE TABLE IF NOT EXISTS SYS_M_FIELD_HELP (
ID_FIELD_HELP BIGINT AUTO_INCREMENT PRIMARY KEY,
PAGE VARCHAR(255) DEFAULT NULL,
FIELD VARCHAR(255) DEFAULT NULL,
HELP_MESSAGE VARCHAR(255) DEFAULT NULL,
HELP_MESSAGE_PARAMETER VARCHAR(255) DEFAULT NULL,
STATUS CHAR(1) DEFAULT 'A',
CREATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
CREATE_USER VARCHAR(255) DEFAULT NULL,
UPDATE_DATE TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
UPDATE_USER VARCHAR(255) DEFAULT NULL
);

/*Table structure for table SYS_M_FIELD_VALIDATOR */

CREATE TABLE IF NOT EXISTS SYS_M_FIELD_VALIDATOR (
ID_FIELD_VALIDATOR BIGINT AUTO_INCREMENT PRIMARY KEY,
CODE VARCHAR(3) NOT NULL DEFAULT '---',
PAGE VARCHAR(255) DEFAULT NULL,
FIELD VARCHAR(255) DEFAULT NULL,
PRE_CON BIGINT DEFAULT NULL,
SEQ INT(11) DEFAULT NULL,
TYPE VARCHAR(255) DEFAULT NULL,
PARAMETER VARCHAR(1024) DEFAULT NULL,
MESSAGE_KEY VARCHAR(255) DEFAULT NULL,
MESSAGE VARCHAR(255) DEFAULT NULL,
MESSAGE_PARAMETER VARCHAR(255) DEFAULT NULL,
STATUS CHAR(1) DEFAULT 'A',
CREATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
CREATE_USER VARCHAR(255) DEFAULT NULL,
UPDATE_DATE TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
UPDATE_USER VARCHAR(255) DEFAULT NULL
);

/*Table structure for table SYS_M_FUNCTION */

CREATE TABLE IF NOT EXISTS SYS_M_FUNCTION (
ID_FUNCTION VARCHAR(32) NOT NULL PRIMARY KEY,
NAME VARCHAR(255) NOT NULL,
LEVEL SMALLINT(11) NOT NULL,
ID_PARENT VARCHAR(32) DEFAULT NULL,
`DESC` VARCHAR(255) DEFAULT NULL,
STATUS CHAR(1) DEFAULT 'A',
CREATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
CREATE_USER VARCHAR(255) DEFAULT NULL,
UPDATE_DATE TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
UPDATE_USER VARCHAR(255) DEFAULT NULL
);

/*Table structure for table SYS_M_GEN_KEY */

CREATE TABLE IF NOT EXISTS SYS_M_GEN_KEY (
ID_GEN_KEY BIGINT AUTO_INCREMENT PRIMARY KEY,
USER_ID BIGINT NOT NULL,
KEY_GEN VARCHAR(255) NOT NULL,
KEY_TYPE VARCHAR(2) NOT NULL,
JSON_DATA TEXT,
TIME_OUT TIMESTAMP NULL DEFAULT NULL,
USED CHAR(1) DEFAULT 'N',
STATUS CHAR(1) DEFAULT 'A',
CREATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
CREATE_USER VARCHAR(255) DEFAULT NULL,
UPDATE_DATE TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
UPDATE_USER VARCHAR(255) DEFAULT NULL
);

/*Table structure for table SYS_M_LABEL */

CREATE TABLE IF NOT EXISTS SYS_M_LABEL (
LANGUAGE VARCHAR(50) NOT NULL,
PAGE VARCHAR(50) NOT NULL,
LABEL VARCHAR(50) NOT NULL,
DISPLAY_TEXT TEXT NOT NULL,
`DESC` TEXT,
STATUS CHAR(1) DEFAULT 'A',
CREATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
CREATE_USER VARCHAR(255) DEFAULT NULL,
UPDATE_DATE TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
UPDATE_USER VARCHAR(255) DEFAULT NULL,
PRIMARY KEY (LANGUAGE,PAGE,LABEL)
);

/*Table structure for table SYS_M_MENU */

CREATE TABLE IF NOT EXISTS SYS_M_MENU (
ID_MENU BIGINT AUTO_INCREMENT PRIMARY KEY,
NAME VARCHAR(50) NOT NULL,
LEVEL SMALLINT(11) NOT NULL,
MENU_TYPE VARCHAR(1) DEFAULT NULL COMMENT 'T=TOP, S=SUB MENU, N=NAVIGATE, R=ROOT',
ID_PARENT BIGINT DEFAULT NULL,
ID_ACTION BIGINT DEFAULT NULL,
SEQ INT(11) DEFAULT NULL,
ID_FUNCTION VARCHAR(32) NOT NULL,
STATUS CHAR(1) DEFAULT 'A',
CREATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
CREATE_USER VARCHAR(255) DEFAULT NULL,
UPDATE_DATE TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
UPDATE_USER VARCHAR(255) DEFAULT NULL
);

/*Table structure for table SYS_M_MESSAGE */

CREATE TABLE IF NOT EXISTS SYS_M_MESSAGE (
MESSAGE_CODE VARCHAR(50) NOT NULL,
MESSAGE_LANG VARCHAR(10) NOT NULL,
DISPLAY_TEXT VARCHAR(255) NOT NULL,
MESSAGE_DESC VARCHAR(255) DEFAULT NULL,
MESSAGE_TYPE VARCHAR(10) NOT NULL,
SOLUTION VARCHAR(255) DEFAULT NULL,
REMARK VARCHAR(255) DEFAULT NULL,
STATUS CHAR(1) DEFAULT 'A',
CREATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
CREATE_USER VARCHAR(255) DEFAULT NULL,
UPDATE_DATE TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
UPDATE_USER VARCHAR(255) DEFAULT NULL,
PRIMARY KEY (MESSAGE_CODE,MESSAGE_LANG)
);

/*Table structure for table SYS_M_PERMISSION_GROUP */

CREATE TABLE IF NOT EXISTS SYS_M_PERMISSION_GROUP (
ID_PERMISSION_GROUP BIGINT NOT NULL,
MODULE VARCHAR(255) NOT NULL,
NAME VARCHAR(255) NOT NULL,
STATUS CHAR(1) DEFAULT 'A',
CREATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
CREATE_USER VARCHAR(255) DEFAULT NULL,
UPDATE_DATE TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
UPDATE_USER VARCHAR(255) DEFAULT NULL
);

/*Table structure for table SYS_M_PERMISSION_GROUP_MAP_FUNCTION */

CREATE TABLE IF NOT EXISTS SYS_M_PERMISSION_GROUP_MAP_FUNCTION (
ID_PERMISSION_GROUP_MAP_FUNCTION BIGINT AUTO_INCREMENT PRIMARY KEY,
ID_PERMISSION_GROUP BIGINT NOT NULL,
ID_FUNCTION VARCHAR(32) NOT NULL,
STATUS CHAR(1) DEFAULT 'A',
CREATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
CREATE_USER VARCHAR(255) DEFAULT NULL,
UPDATE_DATE TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
UPDATE_USER VARCHAR(255) DEFAULT NULL
);

/*Table structure for table SYS_M_PERMISSION_GROUP_MAP_USER */

CREATE TABLE IF NOT EXISTS SYS_M_PERMISSION_GROUP_MAP_USER (
ID_PERMISSION_GROUP_MAP_USER BIGINT AUTO_INCREMENT PRIMARY KEY,
ID_PERMISSION_GROUP BIGINT NOT NULL,
ID_USER BIGINT NOT NULL,
KEY_FUNCTION VARCHAR(32) NOT NULL,
ID_GEN_KEY BIGINT DEFAULT NULL,
STATUS CHAR(1) DEFAULT 'A',
CREATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
CREATE_USER VARCHAR(255) DEFAULT NULL,
UPDATE_DATE TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
UPDATE_USER VARCHAR(255) DEFAULT NULL
);

/*Table structure for table SYS_M_PERMISSION_TRANSLATE */

CREATE TABLE IF NOT EXISTS SYS_M_PERMISSION_TRANSLATE (
ID_PERMISSION_TRANSLATE BIGINT AUTO_INCREMENT PRIMARY KEY,
ID_USER BIGINT DEFAULT NULL,
ID_LANGUAGE BIGINT DEFAULT NULL,
STATUS CHAR(1) DEFAULT 'A',
CREATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
CREATE_USER VARCHAR(255) DEFAULT NULL,
UPDATE_DATE TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
UPDATE_USER VARCHAR(255) DEFAULT NULL
);

/*Table structure for table SYS_M_PHONE */

CREATE TABLE IF NOT EXISTS SYS_M_PHONE (
ID_PHONE BIGINT AUTO_INCREMENT PRIMARY KEY,
ID_PHONE_TYPE BIGINT DEFAULT NULL,
PHONE_PREFIX VARCHAR(255) DEFAULT NULL,
ID_PHONE_CODE BIGINT DEFAULT NULL,
PHONE_NO VARCHAR(255) DEFAULT NULL,
PHONE_EXT VARCHAR(255) DEFAULT NULL,
TIME_START TIME DEFAULT NULL,
TIME_END TIME DEFAULT NULL,
TIME_DURATION_MINUTE INT(11) DEFAULT NULL,
SEQ SMALLINT(6) DEFAULT NULL,
NAME VARCHAR(255) DEFAULT NULL,
STATUS CHAR(1) DEFAULT 'A',
CREATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
CREATE_USER VARCHAR(255) DEFAULT NULL,
UPDATE_DATE TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
UPDATE_USER VARCHAR(255) DEFAULT NULL
);

/*Table structure for table SYS_M_PHONE_CODE */

CREATE TABLE IF NOT EXISTS SYS_M_PHONE_CODE (
ID_PHONE_CODE BIGINT AUTO_INCREMENT PRIMARY KEY,
NAME VARCHAR(10) DEFAULT NULL,
REF_COUNTRY VARCHAR(255) DEFAULT NULL,
REF_COUNTRY_CODE VARCHAR(2) DEFAULT NULL,
REF_INDEX INT(11) DEFAULT NULL,
REF_C VARCHAR(255) DEFAULT NULL,
STATUS CHAR(1) DEFAULT 'A',
CREATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
CREATE_USER VARCHAR(255) DEFAULT NULL,
UPDATE_DATE TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
UPDATE_USER VARCHAR(255) DEFAULT NULL
);

/*Table structure for table SYS_M_PHONE_TYPE */

CREATE TABLE IF NOT EXISTS SYS_M_PHONE_TYPE (
ID_PHONE_TYPE BIGINT AUTO_INCREMENT PRIMARY KEY,
SEQ SMALLINT(6) DEFAULT NULL,
NAME VARCHAR(255) DEFAULT NULL,
STATUS CHAR(1) DEFAULT 'A',
CREATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
CREATE_USER VARCHAR(255) DEFAULT NULL,
UPDATE_DATE TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
UPDATE_USER VARCHAR(255) DEFAULT NULL
);

/*Table structure for table SYS_M_PHONE_TYPE_LANG */

CREATE TABLE IF NOT EXISTS SYS_M_PHONE_TYPE_LANG (
ID_PHONE_TYPE_LANG BIGINT AUTO_INCREMENT PRIMARY KEY,
ID_PHONE_TYPE BIGINT NOT NULL,
LANG_CODE3 VARCHAR(3) DEFAULT NULL,
SEQ SMALLINT(6) DEFAULT NULL,
NAME VARCHAR(255) DEFAULT NULL,
STATUS CHAR(1) DEFAULT 'A',
CREATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
CREATE_USER VARCHAR(255) DEFAULT NULL,
UPDATE_DATE TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
UPDATE_USER VARCHAR(255) DEFAULT NULL
);

/*Table structure for table SYS_M_PREFIX_NAME */

CREATE TABLE IF NOT EXISTS SYS_M_PREFIX_NAME (
ID_PREFIX_NAME BIGINT AUTO_INCREMENT PRIMARY KEY,
SEQ SMALLINT(6) DEFAULT NULL,
NAME VARCHAR(255) DEFAULT NULL,
STATUS CHAR(1) DEFAULT 'A',
CREATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
CREATE_USER VARCHAR(255) DEFAULT NULL,
UPDATE_DATE TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
UPDATE_USER VARCHAR(255) DEFAULT NULL
);

/*Table structure for table SYS_M_PREFIX_NAME_LANG */

CREATE TABLE IF NOT EXISTS SYS_M_PREFIX_NAME_LANG (
ID_PREFIX_NAME_LANG BIGINT AUTO_INCREMENT PRIMARY KEY,
ID_PREFIX_NAME BIGINT DEFAULT NULL,
LANG_CODE3 VARCHAR(3) NOT NULL,
NAME VARCHAR(255) DEFAULT NULL,
STATUS CHAR(1) DEFAULT 'A',
CREATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
CREATE_USER VARCHAR(255) DEFAULT NULL,
UPDATE_DATE TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
UPDATE_USER VARCHAR(255) DEFAULT NULL
);

/*Table structure for table SYS_M_PROVINCE */

CREATE TABLE IF NOT EXISTS SYS_M_PROVINCE (
ID_COUNTRY BIGINT NOT NULL,
ID_PROVINCE BIGINT AUTO_INCREMENT,
ID_ZONE BIGINT DEFAULT NULL,
NAME VARCHAR(255) DEFAULT NULL,
CODE2 VARCHAR(2) DEFAULT NULL,
CODE3 VARCHAR(3) DEFAULT NULL,
STATUS CHAR(1) DEFAULT 'A',
CREATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
CREATE_USER VARCHAR(255) DEFAULT NULL,
UPDATE_DATE TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
UPDATE_USER VARCHAR(255) DEFAULT NULL,
PRIMARY KEY (ID_PROVINCE,ID_COUNTRY)
);

/*Table structure for table SYS_M_PROVINCE_LANG */

CREATE TABLE IF NOT EXISTS SYS_M_PROVINCE_LANG (
ID_PROVINCE_LANG BIGINT AUTO_INCREMENT PRIMARY KEY,
ID_COUNTRY BIGINT DEFAULT NULL,
ID_PROVINCE BIGINT DEFAULT NULL,
LANG_CODE3 VARCHAR(3) DEFAULT NULL,
NAME VARCHAR(255) DEFAULT NULL,
STATUS CHAR(1) DEFAULT 'A',
CREATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
CREATE_USER VARCHAR(255) DEFAULT NULL,
UPDATE_DATE TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
UPDATE_USER VARCHAR(255) DEFAULT NULL
);

/*Table structure for table SYS_M_ROLE */

CREATE TABLE IF NOT EXISTS SYS_M_ROLE (
ID_ROLE BIGINT AUTO_INCREMENT PRIMARY KEY,
ROLE_NAME VARCHAR(64) NOT NULL,
`DESC` VARCHAR(255) DEFAULT NULL,
STATUS CHAR(1) DEFAULT 'A',
CREATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
CREATE_USER VARCHAR(255) DEFAULT NULL,
UPDATE_DATE TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
UPDATE_USER VARCHAR(255) DEFAULT NULL
);

/*Table structure for table SYS_M_ROLE_MAP_FUNCTION */

CREATE TABLE IF NOT EXISTS SYS_M_ROLE_MAP_FUNCTION (
ID_ROLE BIGINT NOT NULL,
ID_FUNCTION VARCHAR(32) NOT NULL,
STATUS CHAR(1) DEFAULT 'A',
CREATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
CREATE_USER VARCHAR(255) DEFAULT NULL,
UPDATE_DATE TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
UPDATE_USER VARCHAR(255) DEFAULT NULL,
PRIMARY KEY (ID_ROLE,ID_FUNCTION)
);

/*Table structure for table SYS_M_SOCIAL_NETWORK */

CREATE TABLE IF NOT EXISTS SYS_M_SOCIAL_NETWORK (
ID_SOCIAL_NETWORK BIGINT AUTO_INCREMENT PRIMARY KEY,
ID_USER BIGINT DEFAULT NULL,
ID_SOCIAL_NETWORK_TYPE BIGINT DEFAULT NULL,
SOCIAL_PROFILE_ID VARCHAR(255) DEFAULT NULL,
SOCIAL_OAUTH_TOKEN VARCHAR(255) DEFAULT NULL,
SOCIAL_OAUTH_SECRET_TOKEN VARCHAR(400) DEFAULT NULL,
SOCIAL_ACCOUNT_NAME VARCHAR(255) DEFAULT NULL,
EMAIL_SOCIAL_ACCOUNT VARCHAR(255) DEFAULT NULL,
STATUS CHAR(1) DEFAULT 'A',
CREATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
CREATE_USER VARCHAR(255) DEFAULT NULL,
UPDATE_DATE TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
UPDATE_USER VARCHAR(255) DEFAULT NULL
);

/*Table structure for table SYS_M_SOCIAL_NETWORK_TYPE */

CREATE TABLE IF NOT EXISTS SYS_M_SOCIAL_NETWORK_TYPE (
ID_SOCIAL_NETWORK_TYPE BIGINT AUTO_INCREMENT PRIMARY KEY,
SEQ SMALLINT(6) DEFAULT NULL,
NAME VARCHAR(255) DEFAULT NULL,
IMAGE_LOGO_PATH VARCHAR(255) DEFAULT NULL,
TIP VARCHAR(255) DEFAULT NULL,
STATUS CHAR(1) DEFAULT 'A',
CREATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
CREATE_USER VARCHAR(255) DEFAULT NULL,
UPDATE_DATE TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
UPDATE_USER VARCHAR(255) DEFAULT NULL
);

/*Table structure for table SYS_M_SOCIAL_NETWORK_TYPE_LANG */

CREATE TABLE IF NOT EXISTS SYS_M_SOCIAL_NETWORK_TYPE_LANG (
ID_SOCIAL_NETWORK_TYPE_LANG BIGINT AUTO_INCREMENT PRIMARY KEY,
ID_SOCIAL_NETWORK_TYPE BIGINT DEFAULT NULL,
LANG_CODE3 VARCHAR(3) NOT NULL,
NAME VARCHAR(255) DEFAULT NULL,
TIP VARCHAR(255) DEFAULT NULL,
STATUS CHAR(1) DEFAULT 'A',
CREATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
CREATE_USER VARCHAR(255) DEFAULT NULL,
UPDATE_DATE TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
UPDATE_USER VARCHAR(255) DEFAULT NULL
);

/*Table structure for table SYS_M_USER */

CREATE TABLE IF NOT EXISTS SYS_M_USER (
ID_USER BIGINT AUTO_INCREMENT PRIMARY KEY,
USERNAME VARCHAR(255) NOT NULL,
PASSWORD VARCHAR(32) DEFAULT NULL,
ID_PREFIX_NAME BIGINT DEFAULT NULL,
FIRST_NAME VARCHAR(255) DEFAULT NULL,
MID_NAME VARCHAR(255) DEFAULT NULL,
LAST_NAME VARCHAR(255) DEFAULT NULL,
MAIDEN_SURNAME VARCHAR(255) DEFAULT NULL,
NICK_NAME VARCHAR(255) DEFAULT NULL,
STATUS CHAR(1) DEFAULT 'A',
CREATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
CREATE_USER VARCHAR(255) DEFAULT NULL,
UPDATE_DATE TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
UPDATE_USER VARCHAR(255) DEFAULT NULL
);

/*Table structure for table SYS_M_USER_CRITERIA */

CREATE TABLE IF NOT EXISTS SYS_M_USER_CRITERIA (
ID_USER_CRITERIA BIGINT AUTO_INCREMENT PRIMARY KEY,
ID_USER BIGINT DEFAULT NULL,
KEY_ID BIGINT DEFAULT NULL
);

/*Table structure for table SYS_M_USER_DEFAULT */

CREATE TABLE IF NOT EXISTS SYS_M_USER_DEFAULT (
ID_USER BIGINT NOT NULL PRIMARY KEY,
MODULE VARCHAR(255) NOT NULL,
KEY_FUNCTION VARCHAR(32) NOT NULL,
STATUS CHAR(1) DEFAULT 'A',
CREATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
CREATE_USER VARCHAR(255) DEFAULT NULL,
UPDATE_DATE TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
UPDATE_USER VARCHAR(255) DEFAULT NULL
);

/*Table structure for table SYS_M_USER_LANG */

CREATE TABLE IF NOT EXISTS SYS_M_USER_LANG (
ID_USER_LANG BIGINT AUTO_INCREMENT PRIMARY KEY,
ID_USER BIGINT DEFAULT NULL,
LANG_CODE3 VARCHAR(3) NOT NULL,
ID_PREFIX_NAME BIGINT DEFAULT NULL,
FIRST_NAME VARCHAR(255) DEFAULT NULL,
MID_NAME VARCHAR(255) DEFAULT NULL,
LAST_NAME VARCHAR(255) DEFAULT NULL,
MAIDEN_SURNAME VARCHAR(255) DEFAULT NULL,
NICK_NAME VARCHAR(255) DEFAULT NULL,
STATUS CHAR(1) DEFAULT 'A',
CREATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
CREATE_USER VARCHAR(255) DEFAULT NULL,
UPDATE_DATE TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
UPDATE_USER VARCHAR(255) DEFAULT NULL
);

/*Table structure for table SYS_M_USER_MAP_ADDRESS */

CREATE TABLE IF NOT EXISTS SYS_M_USER_MAP_ADDRESS (
ID_USER_MAP_ADDRESS BIGINT AUTO_INCREMENT PRIMARY KEY,
ID_USER BIGINT NOT NULL,
ID_ADDRESS BIGINT NOT NULL,
STATUS CHAR(1) DEFAULT 'A',
CREATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
CREATE_USER VARCHAR(255) DEFAULT NULL,
UPDATE_DATE TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
UPDATE_USER VARCHAR(255) DEFAULT NULL
);

/*Table structure for table SYS_M_USER_MAP_PHONE */

CREATE TABLE IF NOT EXISTS SYS_M_USER_MAP_PHONE (
ID_USER_MAP_PHONE BIGINT AUTO_INCREMENT PRIMARY KEY,
ID_USER BIGINT NOT NULL,
ID_PHONE BIGINT NOT NULL,
STATUS CHAR(1) DEFAULT 'A',
CREATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
CREATE_USER VARCHAR(255) DEFAULT NULL,
UPDATE_DATE TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
UPDATE_USER VARCHAR(255) DEFAULT NULL
);

/*Table structure for table SYS_M_USER_MAP_ROLE */

CREATE TABLE IF NOT EXISTS SYS_M_USER_MAP_ROLE (
ID_USER BIGINT NOT NULL,
ID_ROLE BIGINT NOT NULL,
STATUS CHAR(1) DEFAULT 'A',
CREATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
CREATE_USER VARCHAR(255) DEFAULT NULL,
UPDATE_DATE TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
UPDATE_USER VARCHAR(255) DEFAULT NULL,
PRIMARY KEY (ID_USER,ID_ROLE)
);

/*Table structure for table SYS_M_USER_MAP_SOCIAL_NETWORK */

CREATE TABLE IF NOT EXISTS SYS_M_USER_MAP_SOCIAL_NETWORK (
ID_USER_MAP_SOCIAL_NETWORK BIGINT AUTO_INCREMENT PRIMARY KEY,
ID_USER BIGINT NOT NULL,
ID_SOCIAL_NETWORK BIGINT NOT NULL,
STATUS CHAR(1) DEFAULT 'A',
CREATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
CREATE_USER VARCHAR(255) DEFAULT NULL,
UPDATE_DATE TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
UPDATE_USER VARCHAR(255) DEFAULT NULL
);